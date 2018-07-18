package com.healthstore.app;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import com.gengqiquan.result.RxActivityResult;
import com.soundcloud.android.crop.Crop;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class PhotoManager {

    @Inject AppManager appManager;

    @Inject public PhotoManager() {
    }

    public File createNewPhotoFile() {return createNewPhotoFile(null);}
    public File createNewPhotoFile(String imageName) {
        if (imageName == null)
            imageName = UUID.randomUUID().toString() + ".png";
        File imageFile = new File(Environment.getExternalStorageDirectory() + "/images/" + imageName);
        if (!imageFile.getParentFile().exists()) imageFile.getParentFile().mkdirs();
        return imageFile;
    }

    public final Observable<File> startCamera(Fragment fragment) {

        File imageFile = createNewPhotoFile();
        Uri uri = FileProvider.getUriForFile(fragment.getContext(),
                fragment.getActivity().getApplicationContext().getPackageName() + ".file-provider",
                imageFile);

        return new RxPermissions(fragment)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .map(granted -> {
                    if (!granted) appManager.showToast("未授权不能拍照");
                    return granted;
                })
                .filter(granted -> granted == true)
                .flatMap(granted -> {
                    Bundle data = new Bundle();
                    data.putParcelable(MediaStore.EXTRA_OUTPUT, uri);
                    return RxActivityResult.with(fragment.getActivity())
                            .putAll(data)
                            .startActivityWithResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));

                })
                .filter(result -> result.isOK())
                .flatMap(result -> {
                    Crop crop = Crop.of(uri, uri).withAspect(5, 3);
                    return RxActivityResult.with(fragment.getActivity())
                            .startActivityWithResult(crop.getIntent(fragment.getActivity()));
                })
                .filter(result -> result.isOK())
                .map(result -> createNewPhotoFile(imageFile.getName()));
    }

    public final Observable<File> startPhotoPicker(Fragment fragment) {

        File imageFile = createNewPhotoFile();
        Uri uri = FileProvider.getUriForFile(fragment.getContext(),
                fragment.getActivity().getApplicationContext().getPackageName() + ".file-provider",
                imageFile);

        return RxActivityResult.with(fragment.getActivity())
                .startActivityWithResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"))
                .filter(result -> result.isOK())
                .flatMap(result -> {
                    Crop crop = Crop.of(result.data.getData(), uri).withAspect(5, 3);
                    return RxActivityResult.with(fragment.getActivity())
                            .startActivityWithResult(crop.getIntent(fragment.getActivity()));
                })
                .filter(result -> result.isOK())
                .map(result -> createNewPhotoFile(imageFile.getName()));
    }

}
