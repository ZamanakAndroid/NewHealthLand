package com.zamanak.healthland.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.zamanak.healthland.api.result.Categories;
import com.zamanak.healthland.api.result.Featured;
import com.zamanak.healthland.interfaces.OnFeatureListItemClick;
import com.zamanak.healthland.interfaces.OnHealthLandItemClick;
import com.zamanak.healthland.interfaces.OnShowAllItemClickListener;
import com.zamanak.landofhealth.R;

import java.util.List;

/**
 * Created by PIRI on 4/28/2018.
 */
public class MainHealthLandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Categories> itemList;
    private List<Featured> featuredList;
    private OnHealthLandItemClick onHealthLandItemClick;
    private OnFeatureListItemClick onFeatureListItemClick;
    private OnShowAllItemClickListener onShowAllItemClickListener;
    private RowHealthLandAdapter rowHealthLandAdapter;
    private RecyclerView.RecycledViewPool viewPool;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 0;
    private boolean isFirstVisit = false;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public MainHealthLandAdapter(Context context,
                                 List<Categories> itemList,
                                 List<Featured> featuredList,
                                 OnHealthLandItemClick onHealthLandItemClick,
                                 OnFeatureListItemClick onFeatureListItemClick,
                                 OnShowAllItemClickListener onShowAllItemClickListener) {
        this.context = context;
        this.itemList = itemList;
        this.featuredList = featuredList;
        this.onHealthLandItemClick = onHealthLandItemClick;
        this.onFeatureListItemClick = onFeatureListItemClick;
        this.onShowAllItemClickListener = onShowAllItemClickListener;
        this.viewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_health_land,
                        parent, false);
                return new MainHealthLandViewHolder(v);
            } else if (viewType == TYPE_HEADER) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_slider_health_land,
                        parent, false);
                return new SliderHealthLandViewHolder(v);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            if (holder instanceof MainHealthLandViewHolder) {

                MainHealthLandViewHolder viewHolder = (MainHealthLandViewHolder) holder;
                viewHolder.titleTextView.setText(itemList.get(position).getCategory().getName_Fa());
                viewHolder.innerRecyclerView.setRecycledViewPool(viewPool);
                rowHealthLandAdapter = new RowHealthLandAdapter(context, itemList.get(position).getApps(),
                        onHealthLandItemClick);
                viewHolder.innerRecyclerView.setAdapter(rowHealthLandAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL, false);
                layoutManager.setReverseLayout(false);
                viewHolder.innerRecyclerView.setLayoutManager(layoutManager);
                viewHolder.showAllTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onShowAllItemClickListener.onClick(itemList.get(position).getCategory().getName_En(),
                                itemList.get(position).getCategory().getName_Fa());
                    }
                });
            } else if (holder instanceof SliderHealthLandViewHolder && !isFirstVisit) {
                isFirstVisit = true;
                SliderHealthLandViewHolder viewHolder = (SliderHealthLandViewHolder) holder;
                for (final Featured f : featuredList) {
                    TextSliderView textSliderView = new TextSliderView(context);
                    textSliderView.description(" "/* + f.getTitle()*/)
                            .image(f.getFeaturedIcon())
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    onFeatureListItemClick.onFeaturedListItemClick(f);
                                }
                            });
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra", f.getTitle());
                    viewHolder.slider.addSlider(textSliderView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
