package com.zamanak.healthland.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zamanak.healthland.api.result.AppDetailedComments;
import com.zamanak.landofhealth.R;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class HealthLandDetailProductCommentAdapter extends RecyclerView.Adapter<HealthLandDetailProductCommentAdapter.myViewHolder> {


    private ArrayList<AppDetailedComments> items;
    private Context context;
    //private OnItemClickListener listener;
    private AppDetailedComments obj;

    public HealthLandDetailProductCommentAdapter(Context context, ArrayList<AppDetailedComments> item) {
        this.items = item;
        this.context = context;
        //this.listener = listener;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }


    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        obj = items.get(position);
        String userName = obj.getUserName();
        if (userName.isEmpty()) {
            holder.tvCommentorName.setText("بی نام");
        } else {
            holder.tvCommentorName.setText(obj.getUserName());
        }
        holder.tvCommentorDescription.setText(obj.getCommentText());
        holder.tvCommentorDate.setText(obj.getShamsiDate());
        holder.ratingBarCommentor.getProgressDrawable().setColorFilter(Color.parseColor("#FFAB00"), PorterDuff.Mode.SRC_ATOP); // for set background color in progress ratings
        holder.ratingBarCommentor.setEnabled(false);
        if (obj.getUserStar()!=null){
            holder.ratingBarCommentor.setRating(obj.getUserStar().getStar());
        }else{
            //holder.ratingBarCommentor.setRating(0);
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_land_health_detail_comments, viewGroup, false);

        return new myViewHolder(itemView);

    }


    static class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvCommentorName;
        MaterialRatingBar ratingBarCommentor;
        AppCompatTextView tvCommentorDate;
        AppCompatTextView tvCommentorDescription;

        myViewHolder(View convertView) {
            super(convertView);
            tvCommentorName = convertView.findViewById(R.id.tv_commentor_name);
            ratingBarCommentor = convertView.findViewById(R.id.rating_bar_commentor);
            tvCommentorDate = convertView.findViewById(R.id.tv_commentor_date);
            tvCommentorDescription = convertView.findViewById(R.id.tv_commentor_description);
            //ButterKnife.bind(this, convertView);

        }


    }



}