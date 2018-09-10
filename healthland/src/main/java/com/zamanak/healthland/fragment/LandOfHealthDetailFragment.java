package com.zamanak.healthland.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zamanak.healthland.activity.AdvancedWebViewActivity;
import com.zamanak.healthland.adapter.HealthLandDetailProductCommentAdapter;
import com.zamanak.healthland.adapter.HealthLandDetailProductPicAdapter;
import com.zamanak.healthland.api.ApiErrorCB;
import com.zamanak.healthland.api.ApiSuccessCB;
import com.zamanak.healthland.api.BaseApi;
import com.zamanak.healthland.api.object.GetAppDetailedRequest;
import com.zamanak.healthland.api.object.PostAppIdRequest;
import com.zamanak.healthland.api.object.PostInsertComment;
import com.zamanak.healthland.api.object.PostInsertStar;
import com.zamanak.healthland.api.request.RequestGetAppDetailed;
import com.zamanak.healthland.api.request.RequestPostIncreaseView;
import com.zamanak.healthland.api.request.RequestPostInsertComment;
import com.zamanak.healthland.api.request.RequestPostInsertStar;
import com.zamanak.healthland.api.result.AppDetailedComments;
import com.zamanak.healthland.api.result.ResultAppDetailed;
import com.zamanak.healthland.api.result.ResultIncreaseView;
import com.zamanak.healthland.api.result.ResultInsertComment;
import com.zamanak.healthland.api.result.ResultInsertStar;
import com.zamanak.healthland.custom.CustomProgressDialog;
import com.zamanak.healthland.dialog.CommentDialog;
import com.zamanak.healthland.interfaces.CommentDialogListener;
import com.zamanak.healthland.utils.FirebaseLogUtils;
import com.zamanak.healthland.utils.StringUtils;
import com.zamanak.landofhealth.R;

import java.util.ArrayList;
import im.delight.android.webview.AdvancedWebView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by zamanak on 4/28/2018.
 */

public class LandOfHealthDetailFragment extends BaseFragmentNew implements
        HealthLandDetailProductPicAdapter.OnItemClickListener, View.OnTouchListener,AdvancedWebView.Listener {

    private ProgressBar pbProductPic, pbComment;
    private AppCompatButton btnAppRun;
    private AppCompatTextView tvAppName, tvAppDescription, tvAppVotes, tvAppViews,
            tvEmptyProductPic, tvEmptyComment, tvCommetLoadMore;
    private AppCompatImageView ivAppIcon;
    private MaterialRatingBar ratingBarAppRated, ratingBarUserRating;
    private RecyclerView rvProductPic, rvAppComment;
    private LinearLayout llMainLayout;
    private ArrayList<String> gallery;
    private ArrayList<AppDetailedComments> commentList;
    private LinearLayoutManager llm;
    private String appId;
    private CommentDialog dialog;
    private HealthLandDetailProductCommentAdapter commentAdapter;
    private ResultAppDetailed result;

    @Override
    protected void setListener() {
    }

    @Override
    protected int getLayoutResource() {
        if (mActivity.isNetworkConnected()) {
            return R.layout.fragment_land_health_detail;
        } else {
            return R.layout.fragment_no_connection;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mActivity.initCustomToolbar();
        mActivity.initToolbar(mActivity.getString(R.string.product_detail), 0, true);
        btnAppRun = getViewById(R.id.btn_app_run);
        tvAppName = getViewById(R.id.tv_app_name);
        tvAppDescription = getViewById(R.id.tv_app_description);
        tvAppVotes = getViewById(R.id.tv_app_votes);
        tvAppViews = getViewById(R.id.tv_app_views);
        tvEmptyProductPic = getViewById(R.id.tv_empty_product_pic);
        tvEmptyComment = getViewById(R.id.tv_empty_comment);
        tvCommetLoadMore = getViewById(R.id.tv_commet_load_more);
        ivAppIcon = getViewById(R.id.iv_app_icon);
        ratingBarAppRated = getViewById(R.id.rating_bar_app_rated);
        ratingBarAppRated.getProgressDrawable().setColorFilter(Color.parseColor("#777777"),
                PorterDuff.Mode.SRC_ATOP); // for set background color in progress ratings
        ratingBarAppRated.setEnabled(false);
        ratingBarUserRating = getViewById(R.id.rating_bar_user_rating);
        ratingBarUserRating.getProgressDrawable().setColorFilter(Color.parseColor("#009986"),
                PorterDuff.Mode.SRC_ATOP); // for set background color in progress ratings
        rvProductPic = getViewById(R.id.rv_health_land_product_pic);
        rvAppComment = getViewById(R.id.rv_health_land_detail_comment);
        llMainLayout = getViewById(R.id.llMainLayout);
        gallery = new ArrayList<>();
        commentList = new ArrayList<>();
        pbProductPic = getViewById(R.id.progress_bar_product_pic);
        pbComment = getViewById(R.id.progress_bar_comment);
        //ratingBarUserRating.setOnTouchListener(this);
        ratingBarUserRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (ratingBar.getRating()!=0){
                    callInsertStarApi(new PostInsertStar(appId, ratingBar.getRating()));
                }
            }
        });
        tvCommetLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("appId", appId);
                mActivity.addFragment(AppDetailCommentFragment.class, bundle, R.id.fragment, true);
            }
        });



        btnAppRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result != null) {
                    runIncreaseViewApi(new PostAppIdRequest(appId));
                    //Intent intent = new Intent(getContext(), RunAppActivity.class);
                    /*Intent intent = new Intent(getContext(), AdvancedWebViewActivity.class);
                    intent.putExtra("link", result.getResult().getApp().getLink());
                    intent.putExtra("title", result.getResult().getApp().getTitle());
                    intent.putExtra("shareText", result.getResult().getApp().getShareMessage());
                    startActivity(intent);*/
                    //new FinestWebView.Builder(mActivity).show(result.getResult().getApp().getLink());
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                    builder.setStartAnimations(getContext(), R.anim.slide_in_right, R.anim.slide_out_left);
                    builder.setExitAnimations(getContext(), R.anim.slide_in_left, R.anim.slide_out_right);
                    customTabsIntent.launchUrl(getContext(), Uri.parse(result.getResult().getApp().getLink()));
                }
            }
        });
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    protected void processLogic(Bundle savedInstanceState) {

        //initRVProductPic();
        getBundle();
        llMainLayout.setVisibility(View.GONE);
        if (!StringUtils.isNullOrEmpty(appId)) {
            getAppDetailedFromServer(new GetAppDetailedRequest(appId));
        } else {
            onError(R.string.error);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.initToolbar(mActivity.getString(R.string.health_land),
                0,
                true);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                float star = ratingBarUserRating.getRating();
                callInsertStarApi(new PostInsertStar(appId, star));
                break;


        }
        return false;
    }

    private void runIncreaseViewApi(PostAppIdRequest postAppIdRequest) {
        new RequestPostIncreaseView(getContext(), new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                ResultIncreaseView result = new Gson().fromJson(service.resJson.toString(), ResultIncreaseView.class);
                Log.d("onSuccessIncreaseView: ", service.resJson.toString());
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {
                Log.d("onErrorINcreaseView: ", e.getMessage());
            }
        }, postAppIdRequest).execute();
    }

    private void callInsertStarApi(PostInsertStar postInsertStar) {
        new RequestPostInsertStar(getContext(), new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                try {
                    ResultInsertStar result = new Gson().fromJson(service.resJson.toString(), ResultInsertStar.class);
                    if (result.getCode() == 0 && result.getError() == null) {
                        openCommentDialog(result);
                    } else {
                        Toast.makeText(mActivity, result.getResult(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mActivity.onError(R.string.error_connection);
                }
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {
                mActivity.onError(R.string.error_connection);
            }
        }, postInsertStar).execute();
        FirebaseLogUtils.logEvent(mActivity, "sarzamin_itemRated");
    }

    private void openCommentDialog(ResultInsertStar result) {
        dialog = new CommentDialog(getContext());
        dialog.setListener(new CommentDialogListener() {
            @Override
            public void onCommentSuccess(String commentText) {
                callInsertCommentApi(new PostInsertComment(appId, commentText));
            }

            @Override
            public void onCommentFail() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void callInsertCommentApi(PostInsertComment postInsertComment) {
        new RequestPostInsertComment(getContext(), new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                ResultInsertComment result = new Gson().fromJson(service.resJson.toString(), ResultInsertComment.class);
                if (result.getCode() == 0 && result.getError() == null) {
                    Toast.makeText(mActivity, result.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    commentList.add(0, result.getResult().getComment());
                    if (commentList.size() < 3) {
                        llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        rvAppComment.setLayoutManager(llm);
                        rvAppComment.setHasFixedSize(true);
                        rvAppComment.setNestedScrollingEnabled(false);
                        commentAdapter = new HealthLandDetailProductCommentAdapter(getContext(), commentList);
                        rvAppComment.setAdapter(commentAdapter);
                        rvAppComment.setVisibility(View.VISIBLE);
                    } else {
                        for (int j = commentList.size() - 1; j > 2; j--) {
                            commentList.remove(j);
                        }
                        llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        rvAppComment.setLayoutManager(llm);
                        rvAppComment.setHasFixedSize(true);
                        rvAppComment.setNestedScrollingEnabled(false);
                        rvAppComment.setAdapter(new HealthLandDetailProductCommentAdapter(getContext(), commentList));
                        rvAppComment.setVisibility(View.VISIBLE);
                        tvCommetLoadMore.setVisibility(View.VISIBLE);
                    }
                }
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {
                Log.d("onError: ", e.getMessage());
            }
        }, postInsertComment).execute();
        FirebaseLogUtils.logEvent(mActivity, "sarzamin_itemCommented");
    }

    private void getBundle() {
        try {
            appId = getArguments().getString("appItemID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAppDetailedFromServer(GetAppDetailedRequest getAppDetailedRequest) {

        new RequestGetAppDetailed(getContext(), new ApiSuccessCB() {
            @Override
            public void onSuccess(BaseApi service) {
                Log.v("getAppDetailed", service.resJson.toString());
                ResultAppDetailed result = new Gson().fromJson(service.resJson.toString(), ResultAppDetailed.class);
                initDataViews(result);
            }
        }, new ApiErrorCB() {
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                mActivity.onError(R.string.error_connection);
                Log.d("onError: ", e.getMessage());
            }
        }, getAppDetailedRequest).execute();
    }

    private void initDataViews(ResultAppDetailed result) {
        this.result = result;
        llMainLayout.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(result.getResult().getApp().getIcon()).into(ivAppIcon);
        tvAppName.setText(result.getResult().getApp().getTitle());
        tvAppVotes.setText("از" + " " + result.getResult().getApp().getVotes() + " " + "کاربر");
        tvAppViews.setText(result.getResult().getApp().getViews() + " " + "بار اجرا شده");
        ratingBarAppRated.setRating(result.getResult().getApp().getStars());
        tvAppDescription.setText(result.getResult().getApp().getDescription());

        initRVProductPic(result);
        initRVAppComment(result);
    }

    private void initRVProductPic(final ResultAppDetailed result) {
        //rvProductPic.setVisibility(View.GONE);
        //com.zamanak.shamimsalamat.tools.view.custom.CustomProgressDialog.showProgressDialog(pbProductPic);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (result.getResult().getApp().getGallery().size() > 0) {
                    CustomProgressDialog.finishProgressDialog(pbProductPic);
                    for (int i = 0; i < result.getResult().getApp().getGallery().size(); i++) {
                        gallery.add(result.getResult().getApp().getGallery().get(i));
                    }
                    llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rvProductPic.setLayoutManager(llm);
                    rvProductPic.setHasFixedSize(true);
                    rvProductPic.setAdapter(new HealthLandDetailProductPicAdapter(getContext(), gallery, LandOfHealthDetailFragment.this, 1));
                    //rvProductPic.setVisibility(View.VISIBLE);
                } else {
                    // handle to show text that the images for this product is not available
                    //com.zamanak.shamimsalamat.tools.view.custom.CustomProgressDialog.finishProgressDialog(pbProductPic);
                    tvEmptyProductPic.setVisibility(View.VISIBLE);
                }
            }
        }, 2000);

    }

    private void initRVAppComment(final ResultAppDetailed result) {
        //rvAppComment.setVisibility(View.GONE);
        //com.zamanak.shamimsalamat.tools.view.custom.CustomProgressDialog.showProgressDialog(pbComment);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (result.getResult().getComments().size() > 0) {
                    CustomProgressDialog.finishProgressDialog(pbComment);
                    for (int i = 0; i < result.getResult().getComments().size(); i++) {
                        commentList.add(result.getResult().getComments().get(i));
                    }
                    if (commentList.size() < 3) {
                        llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        rvAppComment.setLayoutManager(llm);
                        rvAppComment.setHasFixedSize(true);
                        commentAdapter = new HealthLandDetailProductCommentAdapter(getContext(), commentList);
                        rvAppComment.setAdapter(commentAdapter);
                        rvAppComment.setVisibility(View.VISIBLE);
                    } else {
                        for (int j = commentList.size() - 1; j > 2; j--) {
                            commentList.remove(j);
                        }
                        llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        rvAppComment.setLayoutManager(llm);
                        rvAppComment.setHasFixedSize(true);
                        rvAppComment.setAdapter(new HealthLandDetailProductCommentAdapter(getContext(), commentList));
                        //rvAppComment.setVisibility(View.VISIBLE);
                        tvCommetLoadMore.setVisibility(View.VISIBLE);
                    }

                } else {
                    // handle to show text that the comments for this product is not available
                    //com.zamanak.shamimsalamat.tools.view.custom.CustomProgressDialog.finishProgressDialog(pbComment);
                    tvEmptyComment.setVisibility(View.VISIBLE);
                }
            }
        }, 0);

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("gallery", gallery);
        bundle.putInt("position", position);
        mActivity.addFragment(AppDetailGalleryFragment.class, bundle, R.id.fragment, true);
    }


    // start of methods of advanced web view
    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
    // end of methods of advanced web view

}
