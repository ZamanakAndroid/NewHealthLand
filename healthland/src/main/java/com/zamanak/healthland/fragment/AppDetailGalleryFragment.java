package com.zamanak.healthland.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.zamanak.healthland.adapter.HealthLandDetailProductPicAdapter;
import com.zamanak.landofhealth.R;

import java.util.ArrayList;

/**
 * Created by zamanak on 4/30/2018.
 */

public class AppDetailGalleryFragment extends BaseFragmentNew implements HealthLandDetailProductPicAdapter.OnItemClickListener {

    private ArrayList<String> gallery;
    private RecyclerView rvAppDetailGallery;
    private int position;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void setListener() {
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_app_detail_gallery;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        viewPager = getViewById(R.id.viewpager);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getBundle();

        myViewPagerAdapter = new MyViewPagerAdapter(mActivity, gallery);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(position);

    }


    private void getBundle() {
        gallery = (ArrayList<String>) getArguments().getSerializable("gallery");
        position = getArguments().getInt("position");
    }

    @Override
    public void onItemClick(int item) {

    }

    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        private ArrayList<String> gallery;
        private Context context;

        public MyViewPagerAdapter(Context context, ArrayList<String> gallery) {
            this.gallery = gallery;
            this.context = context;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return gallery.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {

            return view == ((LinearLayout) obj);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = layoutInflater.inflate(R.layout.row_land_health_detail_product_gallery, container, false);
            final ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_app_images);
            String images = gallery.get(position);

            Glide.with(AppDetailGalleryFragment.this).load(images).into(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
