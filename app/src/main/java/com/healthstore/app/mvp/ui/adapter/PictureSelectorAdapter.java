package com.healthstore.app.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.healthstore.app.ImageLoader;
import com.healthstore.app.R;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.model.entity.Picture;
import com.healthstore.app.mvp.presenter.PicturePresenter;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AppFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@FragmentScope
public class PictureSelectorAdapter extends RecyclerView.Adapter<PictureSelectorAdapter.ViewHolder> {

    @Inject
    AppActivity appActivity;
    @Inject
    PicturePresenter presenter;
    @Inject
    ImageLoader imageLoader;

    List<Picture> pictures = new ArrayList<>();

    public List<Picture> getPictures() {
        return pictures;
    }

    @Inject
    public PictureSelectorAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = appActivity.getLayoutInflater().inflate(R.layout.view_agenda_image, null);
        return new ViewHolder(view){};
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        imageLoader.loadPicture(appActivity, picture.getPicUrl(), holder.image);
        holder.itemView.setOnClickListener(v->presenter.selectPicture(picture));
    }

    @Override
    public int getItemCount() {
        return getPictures().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

}
