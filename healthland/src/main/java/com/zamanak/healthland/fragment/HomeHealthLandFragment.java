package com.zamanak.healthland.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.gson.Gson;
import com.zamanak.healthland.adapter.MainHealthLandAdapter;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.request.RequestGetMainHealthLand;
import com.zamanak.healthland.api.result.Apps;
import com.zamanak.healthland.api.result.Featured;
import com.zamanak.healthland.api.result.MainHealthLand;
import com.zamanak.healthland.custom.DividerItemDecoration;
import com.zamanak.healthland.interfaces.OnFeatureListItemClick;
import com.zamanak.healthland.interfaces.OnHealthLandItemClick;
import com.zamanak.healthland.interfaces.OnShowAllItemClickListener;
import com.zamanak.healthland.utils.FirebaseLogUtils;
import com.zamanak.landofhealth.R;

import java.util.ArrayList;

/**
 * Created by PIRI on 4/25/2018.
 */
public class HomeHealthLandFragment extends BaseFragmentNew implements OnHealthLandItemClick,
        OnFeatureListItemClick, OnShowAllItemClickListener {

    private RecyclerView mainRecyclerView;
    private MainHealthLandAdapter mainHealthLandAdapter;
    private MainHealthLand mainHealthLand;
    private ArrayList items = new ArrayList();
    private ArrayList featuredList = new ArrayList();

    @Override
    protected void setListener() {
    }

    @Override
    protected int getLayoutResource() {
        if (mActivity.isNetworkConnected()) {
            return R.layout.fragment_home_health_land;
        } else {
            return R.layout.fragment_no_connection;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mActivity.initCustomToolbar();
        mActivity.initToolbar(mActivity.getString(R.string.health_land),
                0,
                true);
        mainRecyclerView = getViewById(R.id.mainRecyclerView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        FirebaseLogUtils.logEvent(mActivity, "sarzamin_visited");
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        items = new ArrayList();
        featuredList = new ArrayList();
        getMainHealthLandItemsFromServer();
    }

    private void getMainHealthLandItemsFromServer() {

        new RequestGetMainHealthLand(mActivity, new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                try {
                    Log.v("mainHealthLand", service.resJson.toString());
                    mainHealthLand = new Gson().fromJson(service.resJson.toString(), MainHealthLand.class);
                    initRV();
                } catch (Exception e) {
                    e.printStackTrace();
                    mActivity.onError(R.string.error_connection);
                }
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                mActivity.onError(R.string.error_connection);
                Log.e("mainHealthLand", e.getMessage());
            }
        }).execute();
    }

    private void initRV() {
        try {
            items.add(null);
            items.addAll(mainHealthLand.getResult().getCategories());
            featuredList.addAll(mainHealthLand.getResult().getFeatured());
            mainHealthLandAdapter = new MainHealthLandAdapter(mActivity, items,
                    featuredList, this,
                    this, this);
            mainRecyclerView.setAdapter(mainHealthLandAdapter);
            mainRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            mainRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,
                    DividerItemDecoration.VERTICAL_LIST));
            mainRecyclerView.setHasFixedSize(true);
            mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mActivity.getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                mActivity.onBackPressed();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onClick(Apps app) {
        runHealthLandItemFragment(app.getId());
        FirebaseLogUtils.logHealthLandNameEvent(mActivity, "sarzamin_itemPageDetail", app.getTitle());
    }

    void runHealthLandItemFragment(String id) {

        Bundle bundle = new Bundle();
        bundle.putString("appItemID", id);
        mActivity.addFragment(LandOfHealthDetailFragment.class, bundle, R.id.fragment, true);
    }

    @Override
    public void onFeaturedListItemClick(Featured featured) {
        runHealthLandItemFragment(featured.getId());
        FirebaseLogUtils.logHealthLandNameEvent(mActivity, "sarzamin_featuredSelected", featured.getTitle());
    }

    @Override
    public void onClick(String catName_En, String cateName_Fa) {

        Bundle bundle = new Bundle();
        bundle.putString("catName_En", catName_En);
        bundle.putString("catName_Fa", cateName_Fa);
        mActivity.addFragment(HealthLandMoreAppsFragment.class, bundle, R.id.fragment, true);
    }
}
