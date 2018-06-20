package com.zamanak.healthland.adapter;

import android.content.Context;
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
 * Created by PIRI on 4/28/2018.
 */
public class RowHealthLandAdapter extends RecyclerView.Adapter<RowHealthLandViewHolder> {

    Context context;
    List<Apps> list;
    private OnHealthLandItemClick onHealthLandItemClick;
    //private RecyclerView.RecycledViewPool viewPool;

    public RowHealthLandAdapter(Context context,
                                List<Apps> list,
                                OnHealthLandItemClick onHealthLandItemClick) {
        this.context = context;
        this.list = list;
        //viewPool = new RecyclerView.RecycledViewPool();
        this.onHealthLandItemClick = onHealthLandItemClick;
    }

    @Override
    public RowHealthLandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_health_land_row,
                parent, false);
        return new RowHealthLandViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RowHealthLandViewHolder holder, final int position) {
        try {
            holder.itemTitleTextView.setText(list.get(position).getTitle());
            Glide.with(context)
                    .load(list.get(position).getIcon())
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(holder.itemImageView);

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onHealthLandItemClick.onClick(list.get(position));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

