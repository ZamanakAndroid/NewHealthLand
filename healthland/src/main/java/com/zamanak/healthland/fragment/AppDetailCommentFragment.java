package com.zamanak.healthland.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.gson.Gson;
import com.zamanak.healthland.adapter.HealthLandDetailProductCommentAdapter;
import com.zamanak.healthland.adapter.HealthLandDetailProductPicAdapter;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.object.AppDetailLoadMoreComments;
import com.zamanak.healthland.api.request.RequestPostLoadMoreComments;
import com.zamanak.healthland.api.result.ResultLoadMoreComments;
import com.zamanak.healthland.utils.FirebaseLogUtils;
import com.zamanak.landofhealth.R;

/**
 * Created by zamanak on 4/30/2018.
 */

public class AppDetailCommentFragment extends BaseFragmentNew implements HealthLandDetailProductPicAdapter.OnItemClickListener {

    private RecyclerView rvAppDetailComment;
    private String appId;

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_app_detail_comment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rvAppDetailComment = getViewById(R.id.rv_app_detail_comment);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getBundle();
        callLoadMoreCommentsApi(new AppDetailLoadMoreComments(appId, 0, 0));
        FirebaseLogUtils.logEvent(mActivity, "sarzamin_itemShowAllComments");
    }

    private void callLoadMoreCommentsApi(AppDetailLoadMoreComments appDetailLoadMoreComments) {
        new RequestPostLoadMoreComments(getContext(), new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                ResultLoadMoreComments result = new Gson().fromJson(service.resJson.toString(), ResultLoadMoreComments.class);
                if (result.getCode() == 0 && result.getError() == null) {
                    initRVLoadMoreComments(result);
                }
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {

            }
        }, appDetailLoadMoreComments).execute();
    }

    private void initRVLoadMoreComments(ResultLoadMoreComments result) {
        if (result.getResult().size() > 0) {
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rvAppDetailComment.setLayoutManager(llm);
            rvAppDetailComment.setHasFixedSize(true);
            rvAppDetailComment.setAdapter(new HealthLandDetailProductCommentAdapter(getContext(), result.getResult()));
        }
    }

    private void getBundle() {
        appId = getArguments().getString("appId");
    }

    @Override
    public void onItemClick(int item) {

    }



}
