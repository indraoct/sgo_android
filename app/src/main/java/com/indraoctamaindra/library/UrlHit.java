package com.indraoctamaindra.library;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Indra Octama on 7/16/2015.
 */
public class UrlHit {

    private static final String AIRLINES_URL = ParamConst.airlinesMainUrl;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get( RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(ParamConst.setTimeout);
        client.get(AIRLINES_URL, params, responseHandler);
    }

    public static void post(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(ParamConst.setTimeout);
        client.post(context,getAbsoluteUrl(url), params, responseHandler);
    }


    private static String getAbsoluteUrl(String relativeUrl) {
        return AIRLINES_URL + relativeUrl;
    }

}
