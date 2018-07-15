package com.healthstore.app.mvp.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.healthstore.app.R;
import com.healthstore.app.di.component.AppComponent;
import com.healthstore.app.di.component.DaggerUserComponent;
import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.entity.User;
import com.healthstore.app.mvp.presenter.UserPresenter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSettingsFragment extends AppFragment<UserPresenter> implements UserContract.View {

    @BindView(R.id.top_bar) QMUITopBarLayout topBar;
    @BindView(R.id.user_settings) QMUIGroupListView userSettings;

    @Override int layoutResId() {
        return R.layout.fragment_user_settings;
    }

    @Override void setUpComponent(AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this, getActivity()))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btn_logout)
    public void onClickLogout(){

    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topBar.setTitle("设置");
        topBar.addLeftBackImageButton().setOnClickListener(v -> mActivityManager.popupFragment());

        QMUICommonListItemView agendaBack = userSettings.createItemView("日程背景图");
        agendaBack.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView itemSetting = userSettings.createItemView("项目设置");
        itemSetting.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView activityNotify = userSettings.createItemView("接收活动通知");
        activityNotify.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);

        QMUICommonListItemView instruction = userSettings.createItemView("使用说明");
        instruction.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView clearCache = userSettings.createItemView("清除缓存");
        clearCache.setDetailText("100K");
        clearCache.getDetailTextView().setTextColor(Color.GRAY);

        QMUICommonListItemView version = userSettings.createItemView("版本号");
        version.setDetailText("1.0.0");
        version.getDetailTextView().setTextColor(Color.GRAY);

        BottomSheetDialog bottomSheet = new BottomSheetDialog(getContext());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_agenda_bg_actions, null);
        ButterKnife.bind(new BottomSheetActionList() {
            @Override
            BottomSheetDialog getBottomDialog() {
                return bottomSheet;
            }
        }, inflate);
        bottomSheet.setCanceledOnTouchOutside(true);
        bottomSheet.setContentView(inflate);
        bottomSheet.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);

        QMUIGroupListView.newSection(this.getContext())
                .addItemView(agendaBack, v -> {bottomSheet.show();})
                .addItemView(itemSetting, v -> mActivityManager.replaceFragment(getContainerId(), new UserItemSelectorFragment()))
                .addItemView(activityNotify, v -> {})
                .addItemView(instruction, v -> { mActivityManager.replaceFragment(getContainerId(), new AppInstructionFragment()); })
                .addItemView(clearCache, v -> {})
                .addItemView(version, v -> {})
                .addTo(userSettings);

        activityNotify.getSwitch().setChecked(mAppManager.getMainUser().getValue().isReceiveActivityTrailer());
        activityNotify.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
            mPresenter.updateMainUser(new User() {
                @Override public Boolean isReceiveActivityTrailer() {
                    return isChecked;
                }
            });
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult - " + requestCode + " - " + resultCode);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
//            headImageView.setImageURI(Uri.fromFile(file));
//
//            Bitmap bitmap = data.getParcelableExtra("data");
            //在手机相册中显示刚拍摄的图片
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File imageFile = new File(Environment.getExternalStorageDirectory() + "/images" + "/health.jpeg");
            // 获取图片所在位置的Uri路径    *****这里为什么这么做参考问题2*****
            /*imageUri = Uri.fromFile(new File(mTempPhotoPath));*/
            Uri imageUri = FileProvider.getUriForFile(getContext(),
                    getActivity().getApplicationContext().getPackageName() +".fp",
                    imageFile);
            mediaScanIntent.setData(imageUri);
            getActivity().sendBroadcast(mediaScanIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult - " + requestCode + " - " + permissions[0]);
        Log.d(TAG, "onRequestPermissionsResult - " + requestCode + " - " + grantResults[0]);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
//            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }

    }

    void useCamera() {
        // 跳转到系统的拍照界面
        Intent intentToTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定照片存储位置为sd卡本目录下
        // 这里设置为固定名字 这样就只会只有一张temp图 如果要所有中间图片都保存可以通过时间或者加其他东西设置图片的名称
        // File.separator为系统自带的分隔符 是一个固定的常量
        File imageFile = new File(Environment.getExternalStorageDirectory() + "/images" + "/health.jpeg");
        // 获取图片所在位置的Uri路径    *****这里为什么这么做参考问题2*****
        /*imageUri = Uri.fromFile(new File(mTempPhotoPath));*/
        Uri imageUri = FileProvider.getUriForFile(getContext(),
                getActivity().getApplicationContext().getPackageName() +".fp",
                imageFile);
        //下面这句指定调用相机拍照后的照片存储的路径
        intentToTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intentToTakePhoto, 1);
    }

    abstract class BottomSheetActionList{
        @OnClick(R.id.btn_server)
        void OnClickBtnServer(){
            getBottomDialog().dismiss();
            mActivityManager.replaceFragment(getContainerId(), new PictureSelectorFragment());
        }

        @OnClick(R.id.btn_local)
        void OnClickBtnLocal(){
            System.out.println("click local");
        }

        @OnClick(R.id.btn_take_photo)
        void OnClickBtnTakePhoto(){
            int granted = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (granted == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

        }

        abstract BottomSheetDialog getBottomDialog();
    }

}
