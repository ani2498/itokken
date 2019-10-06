package com.itokken.itokken;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Short.TYPE;

public class iTOkkenMain extends AppCompatActivity implements View.OnClickListener {



    WebView webView;
    ProgressBar bar;
    private FloatingActionButton fab,fab1,fab2,fab3;
    private Boolean isFabClick = false;
    private Animation fab1open,fab1close,fab2open,fab2close,fab3open,fab3close;
    private boolean doubleBackToExitPressedOnce = false;


    String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_tokken_main);
        webView = (WebView) findViewById(R.id.webView);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab_3);


        fab1open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_1show);
        fab1close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_1hide);
        fab2open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_2show);
        fab2close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_2hide);
        fab3open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_3show);
        fab3close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_3hide);




        fab.setOnClickListener((View.OnClickListener) this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        bar=(ProgressBar) findViewById(R.id.progressBar2);
        webView.setWebViewClient(new myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        String newUA= "Chrome/43.0.2357.65 ";
        webView.getSettings().setUserAgentString(newUA);
        webView.loadUrl("http://www.itokken.com");

        webUrl=webView.getUrl();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.fab:
                animateFab();
                break;
            case R.id.fab_1:
                webView.loadUrl("http://itokken.com/wishlist");
                bar.setVisibility(ProgressBar.VISIBLE);
                animateFab();
                break;
            case R.id.fab_2:
                webView.loadUrl("http://itokken.com/cart");
                bar.setVisibility(ProgressBar.VISIBLE);
                animateFab();
                break;
            case R.id.fab_3:
                webView.loadUrl("http://itokken.com/recentlyviewedproducts");
                bar.setVisibility(ProgressBar.VISIBLE);
                animateFab();
                break;
        }
    }

    public void animateFab(){
        if (isFabClick){

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams.rightMargin -= (int) (fab1.getWidth() * 2.3);
            layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
            fab1.setLayoutParams(layoutParams);
            fab1.startAnimation(fab1close);
            fab1.setClickable(false);

            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
            layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.7);
            layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
            fab2.setLayoutParams(layoutParams2);
            fab2.startAnimation(fab2close);
            fab2.setClickable(false);

            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
            layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.4);
            layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 2.3);
            fab3.setLayoutParams(layoutParams3);
            fab3.startAnimation(fab3close);
            fab3.setClickable(false);

            isFabClick = false;

        }
        else{
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams.rightMargin += (int) (fab1.getWidth() * 2.3);
            layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
            fab1.setLayoutParams(layoutParams);
            fab1.startAnimation(fab1open);
            fab1.setClickable(true);

            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
            layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.7);
            layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
            fab2.setLayoutParams(layoutParams2);
            fab2.startAnimation(fab2open);
            fab2.setClickable(true);

            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
            layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.4);
            layoutParams3.bottomMargin += (int) (fab3.getHeight() * 2.3);
            fab3.setLayoutParams(layoutParams3);
            fab3.startAnimation(fab3open);
            fab3.setClickable(true);

            isFabClick = true;
        }
    }

    public class myWebclient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            bar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {


            super.onPageStarted(view, url, favicon);
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {


        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            try {
                webView.stopLoading();
            } catch (Exception e) {
            }

            if (webView.canGoBack()) {
                webView.goBack();
            }

                webUrl=webView.getUrl();




            AlertDialog alertDialog = new AlertDialog.Builder(iTOkkenMain.this).create();
            alertDialog.setTitle("Network Connection Error");
            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.dialog_image, null);
            alertDialog.setView(dialoglayout);
            alertDialog.setMessage("Check your internet connection and try again.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    webView.loadUrl(webUrl);



                }
            });

            alertDialog.show();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

    }

    @Override
    protected void onStart() {
        if(isInternetOn())
        {

            webView.loadUrl(webUrl);

        }
        else
        {

            webView.loadUrl("about: blank");
            AlertDialog alertDialog = new AlertDialog.Builder(iTOkkenMain.this).create();
            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.dialog_image, null);
            alertDialog.setView(dialoglayout);
            alertDialog.setTitle("Network Connection Error");

            alertDialog.setMessage("Turn on your mobile data or WIFI.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Go To Setting", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);




                }
            });

            alertDialog.show();
        }
        super.onStart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode== KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet


            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {


            return false;
        }
        return false;
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.dialog_exit, null);
            new AlertDialog.Builder(this)
                    .setView(dialoglayout)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            iTOkkenMain.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }



}
