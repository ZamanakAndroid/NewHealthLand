package com.zamanak.healthland;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.zamanak.healthland.activity.HealthLandActivity;


/**
 * Created by zamanak on 6/18/2018.
 */

public class LandOfHealthSDK {


    private String appName = "LandOfHealth";
    private static final String TAG = "LandOfHealthSDK";
    private Application application;

    private static String BASE_API_KEY;
    private static String TOKEN;
    private static String BASE_URL;


    private static String GET_App_DETAILED = "/sarzamin-salamati/get-app-detailed";
    private static String GET_MAIN_PAGE = "/sarzamin-salamati/get-main-page";
    private static String Insert_Star = "/sarzamin-salamati/insert-star";
    private static String INSERT_COMMENT = "/sarzamin-salamati/insert-comment";
    private static String INCREASE_VIEW = "/sarzamin-salamati/increase-view";
    private static String LOAD_MORE_APPS = "/sarzamin-salamati/load-more-apps";
    private static String LOAD_MORE_COMMENTs = "/sarzamin-salamati/load-more-comments";

    private static volatile LandOfHealthSDK sdk = new LandOfHealthSDK();

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        LandOfHealthSDK.TOKEN = TOKEN;
    }

    public static LandOfHealthSDK getSdk() {
        return sdk;
    }

    public static String getBaseApiKey() {
        return BASE_API_KEY;
    }

    public static void setSdk(LandOfHealthSDK sdk) {
        LandOfHealthSDK.sdk = sdk;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }


    public void setApiAppKey(String apiAppKey) {
        this.BASE_API_KEY = apiAppKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public static String getGET_App_DETAILED() {
        return GET_App_DETAILED;
    }

    public void setGET_App_DETAILED(String GET_App_DETAILED) {
        this.GET_App_DETAILED = GET_App_DETAILED;
    }

    public static String getGET_MAIN_PAGE() {
        return GET_MAIN_PAGE;
    }

    public void setGET_MAIN_PAGE(String GET_MAIN_PAGE) {
        this.GET_MAIN_PAGE = GET_MAIN_PAGE;
    }

    public static String getLOAD_MORE_APPS() {
        return LOAD_MORE_APPS;
    }

    public void setLOAD_MORE_APPS(String LOAD_MORE_APPS) {
        this.LOAD_MORE_APPS = LOAD_MORE_APPS;
    }

    public static String getInsert_Star() {
        return Insert_Star;
    }

    public void setInsert_Star(String insert_Star) {
        Insert_Star = insert_Star;
    }

    public static String getINSERT_COMMENT() {
        return INSERT_COMMENT;
    }

    public void setINSERT_COMMENT(String INSERT_COMMENT) {
        this.INSERT_COMMENT = INSERT_COMMENT;
    }

    public static String getLOAD_MORE_COMMENTs() {
        return LOAD_MORE_COMMENTs;
    }

    public void setLOAD_MORE_COMMENTs(String LOAD_MORE_COMMENTs) {
        this.LOAD_MORE_COMMENTs = LOAD_MORE_COMMENTs;
    }

    public static String getINCREASE_VIEW() {
        return INCREASE_VIEW;
    }

    public void setINCREASE_VIEW(String INCREASE_VIEW) {
        this.INCREASE_VIEW = INCREASE_VIEW;
    }

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    //constructor
    public LandOfHealthSDK() {

    }

    public void createLandOfHealth(final Application application) {
        this.application = application;
    }

    public static LandOfHealthSDK sharedLandOfHealth() {
        return sdk;
    }

    public void startLandOfHealthActivity(Activity ctx, String token, String baseApiKey, String baseURL) {
        setTOKEN(token);
        setApiAppKey(baseApiKey);
        setBASE_URL(baseURL);
        Intent intent = new Intent(ctx, HealthLandActivity.class);
        ctx.startActivity(intent);
    }
}
