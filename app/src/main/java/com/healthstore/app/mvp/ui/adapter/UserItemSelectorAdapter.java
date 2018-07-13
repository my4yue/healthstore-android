package com.healthstore.app.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthstore.app.AppManager;
import com.healthstore.app.R;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.model.cache.ItemCache;
import com.healthstore.app.mvp.model.entity.Item;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AppFragment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@FragmentScope
public class UserItemSelectorAdapter extends RecyclerView.Adapter<UserItemSelectorAdapter.ViewHolder> {

    @Inject
    ItemCache itemCache;
    @Inject
    AppManager appManager;
    @Inject
    AppActivity appActivity;
    @Inject
    AppFragment appFragment;

    Set<String> selectedItemId = new HashSet<>();

    @Inject
    public UserItemSelectorAdapter() { }

    public Set<String> getSelectedItemId(){
        return selectedItemId;
    }

    public void setSelectedItemId(Set<String> selectedIds){
        this.selectedItemId.clear();
        this.selectedItemId.addAll(selectedIds);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(appActivity).inflate(R.layout.item_view_item_selector, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemCache.getItemList().get(position);
        holder.item = item;
        holder.text.setText(item.getName());
        holder.image.setVisibility(selectedItemId.contains(item.getItemId()) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return itemCache.getItemList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.checked)
        ImageView image;
        Item item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v ->{
                if (selectedItemId.remove(item.getItemId())) {
                    image.setVisibility(View.INVISIBLE);
                } else {
                    selectedItemId.add(item.getItemId());
                    image.setVisibility(View.VISIBLE);
                }
            });
        }
    }

}
