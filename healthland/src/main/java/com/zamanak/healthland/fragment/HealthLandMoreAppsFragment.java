package com.zamanak.healthland.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.google.gson.Gson;
import com.zamanak.healthland.adapter.BaseRvAdapter;
import com.zamanak.healthland.adapter.HealthLandMoreAppsAdapter;
import com.zamanak.healthland.adapter.LoadMorRvAdapter;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.request.RequestGetMoreApps;
import com.zamanak.healthland.api.result.Apps;
import com.zamanak.healthland.api.result.LoadMoreApps;
import com.zamanak.healthland.interfaces.EndlessRvScrollListener;
import com.zamanak.healthland.interfaces.OnHealthLandItemClick;
import com.zamanak.healthland.utils.FirebaseLogUtils;
import com.zamanak.healthland.utils.StringUtils;
import com.zamanak.landofhealth.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PIRI on 4/30/2018.
 */
public class HealthLandMoreAppsFragment extends BaseFragmentNew implements OnHealthLandItemClick,
        BaseRvAdapter.ItemClickListener,
        LoadMorRvAdapter.RetryLoadMoreListener {

    private RecyclerView recyclerView;
    private HealthLandMoreAppsAdapter healthLandMoreAppsAdapter;
    private List<Apps> itemList;
    private String catName_En;
    private String catName_Fa;
    private int limit = 10;
    private int offset = 0;

    @Override
    protected void setListener() {
    }

    @Override
    protected int getLayoutResource() {
        if (mActivity.isNetworkConnected()) {
            return R.layout.fragment_health_land_more_apps;
        } else {
            return R.layout.fragment_no_connection;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerView = getViewById(R.id.recyclerView);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        getBundle();
        initCustomToolbar();
        initRV();
        getMoreAppsFromServer();
    }

    private void initCustomToolbar() {
        mActivity.initCustomToolbar();
        String toolbarTitle = ((StringUtils.isNullOrEmpty(catName_Fa)) ? "" : catName_Fa);
        mActivity.initToolbar(toolbarTitle,
                0,
                true);
    }

    private void getBundle() {
        try {
            catName_En = getArguments().getString("catName_En");
            catName_Fa = getArguments().getString("catName_Fa");
            FirebaseLogUtils.logHealthLandCatEvent(mActivity, "sarzamin_itemsCategory", catName_Fa);
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
    public void onDestroy() {
        super.onDestroy();
        mActivity.initToolbar(mActivity.getString(R.string.health_land),
                0,
                true);
    }

    private void getMoreAppsFromServer() {

        new RequestGetMoreApps(mActivity, new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                Log.v("getMoreApps", service.resJson.toString());
                Log.v("getMoreAppsOffset", String.valueOf(offset));
                LoadMoreApps loadMoreApps = new Gson().fromJson(service.resJson.toString(), LoadMoreApps.class);
                if (loadMoreApps.getResult() == null || loadMoreApps.getResult().isEmpty()) {
                    healthLandMoreAppsAdapter.onReachEnd();
                } else {
                    itemList.addAll(loadMoreApps.getResult());
                    healthLandMoreAppsAdapter.notifyDataSetChanged();
                }
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {
                healthLandMoreAppsAdapter.onLoadMoreFailed();
                Log.e("getMoreAppsFromServer", e.getMessage());
                mActivity.onError(R.string.error_connection);
            }
        }, catName_En, limit, offset).execute();
    }

    private void initRV() {

        itemList = new ArrayList<>();
        healthLandMoreAppsAdapter = new HealthLandMoreAppsAdapter(mActivity, itemList,
                this, this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(healthLandMoreAppsAdapter);
        EndlessRvScrollListener scrollListener = new EndlessRvScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page) {
                loadMore(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void onClick(Apps app) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appItemID", app.getId());
            mActivity.addFragment(LandOfHealthDetailFragment.class, bundle, R.id.fragment, true);
        } catch (Exception e) {
            e.printStackTrace();
            mActivity.onError(R.string.error);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    private void loadMore(final int page) {

        healthLandMoreAppsAdapter.startLoadMore();
        offset += limit;
        getMoreAppsFromServer();
    }

    @Override
    public void onRetryLoadMore() {

        getMoreAppsFromServer();
    }
}
