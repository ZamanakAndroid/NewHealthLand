package com.zamanak.healthland.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.zamanak.healthland.api.result.Apps;
import com.zamanak.healthland.interfaces.OnHealthLandItemClick;
import com.zamanak.landofhealth.R;

import java.util.List;

/**
 * Created by PIRI on 4/30/2018.
 */
public class HealthLandMoreAppsAdapter extends LoadMorRvAdapter<Apps> {

    private Context context;
    private ItemClickListener itemClickListener;
    private RetryLoadMoreListener retryLoadMoreListener;
    private OnHealthLandItemClick onHealthLandItemClick;
    private static final int TYPE_ITEM = 1;

    public HealthLandMoreAppsAdapter(@NonNull Context context,
                                     List<Apps> apps,
                                     ItemClickListener itemClickListener,
                                     @NonNull RetryLoadMoreListener retryLoadMoreListener,
                                     OnHealthLandItemClick onHealthLandItemClick) {

        super(context, itemClickListener, retryLoadMoreListener);
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.retryLoadMoreListener = retryLoadMoreListener;
        this.onHealthLandItemClick = onHealthLandItemClick;
        this.mDataList = apps;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_health_land_row_match_parent,
                    parent, false);
            return new RowHealthLandViewHolder(v);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof RowHealthLandViewHolder) {
            final Apps item = mDataList.get(position);
            try {
                RowHealthLandViewHolder viewHolder = ((RowHealthLandViewHolder) holder);
                viewHolder.itemTitleTextView.setText(item.getTitle());
                viewHolder.layout.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // itemClickListener.onItemClick(v, position);
onHealthLandItemClick.onClick(item);                            }
                        });
                Glide.with(context)
                        .load(item.getIcon())
                        .into((viewHolder.itemImageView));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected int getCustomItemViewType(int position) {
        return TYPE_ITEM;
    }

}
