package com.indraoctamaindra.sgoticket;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.util.Log;
import android.webkit.ClientCertRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.indraoctamaindra.library.DefinedDialog;
import com.indraoctamaindra.library.ParamConst;
import com.indraoctamaindra.library.TinyDB;


public class PaymentwebActivity extends Activity {

    WebView ibweb;
    ProgressDialog out;
    Boolean isDisconnected;
    String UrlAbsolute,key,paymentId,commCode,bankCode,productCode;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentweb);

        context = getBaseContext();

        final TinyDB tinydb = new TinyDB(context);
        isDisconnected = !isOnline();
        ibweb = (WebView) findViewById(R.id.ibweb);
        out = DefinedDialog.CreateProgressDialog(PaymentwebActivity.this,null,"Loading Internet Banking...");

        /*
         * URL
         */
        key = tinydb.getString("api_key");
        paymentId = tinydb.getString("tx_id");
        commCode = tinydb.getString("comm_code");
        bankCode = tinydb.getString("bank_code");
        productCode = tinydb.getString("product_code");

        //URL ABSOLUTE
        UrlAbsolute = ParamConst.IBWebUrl+"key="+key+"&paymentId="+paymentId+"&commCode="+commCode+"&bankCode="+bankCode+"&productCode="+productCode+"&mobile=1";

        WebSettings webSettings = ibweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        final Activity activity = this;

        if (android.os.Build.VERSION.SDK_INT<=11) {
            webSettings.setAppCacheMaxSize(1024 * 1024 * 8);

        }

        ibweb.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int progress){
                //setSupportProgress(progress * 100);
                out.show();
                //activity.setProgress(progress * 100);
                if(progress == 100) out.dismiss();
            }
        });

        ibweb.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if (url.contains("isclose=1")){

                    Intent intent = new Intent(PaymentwebActivity.this,MainActivity.class);
                           startActivity(intent);
                }else {

                    Log.d("isi url tombol-tombolnya", url);
                    view.loadUrl(url);

                }

                return true;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                isDisconnected = true;
                invalidateOptionsMenu();

            }

            @Override
            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                super.onReceivedClientCertRequest(view, request);
            }



        });
        ibweb.loadUrl(UrlAbsolute);



    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
