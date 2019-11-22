package com.tnta.iuwebpage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by theanh on 10/25/15.
 */
public class BlackBoard extends Activity{

    private Preferences preference;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        webView = (WebView)findViewById(R.id.webView);

        preference = new Preferences(getApplication());

        String url = "http://blackboard.hcmiu.edu.vn/";
        final String bb_username = preference.getID();
        final String bb_password = preference.getBBUserPassword();
        final WebView webView = (WebView) this.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView v, final String url) {
                MainActivity.netCheck = MainActivity.isNetworkAvaliable(getApplicationContext());
                preference.setTab("1");
                if (MainActivity.netCheck == false){
                    final ImageView imageView = (ImageView)findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.refresh);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.netCheck = MainActivity.isNetworkAvaliable(getApplicationContext());
                            if(MainActivity.netCheck==false) {
                                Toast.makeText(getApplicationContext(), "Please turn on the Internet", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                imageView.setImageDrawable(null);
                                webView.loadUrl(url);
                            }
                        }
                    });
                }
                else {
                    if (bb_username != null && bb_password != null) {
                        v.loadUrl("javascript: var user='" + bb_username + "';var pass='" + bb_password + "';if(document.getElementById('loginErrorMessage')==null){document.getElementById('user_id').value=user;document.getElementById('password').value=pass;document.getElementById('entry-login').click();}");
                    }
                    if (url.endsWith(".pdf")|| url.endsWith(".doc")||url.endsWith(".docx")||url.endsWith(".ptt")||url.endsWith(".pttx")||url.endsWith(".xls")||url.endsWith(".xlsx")) {
                        v.setDownloadListener(new DownloadListener() {
                            public void onDownloadStart(String url, String userAgent,
                                                        String contentDisposition, String mimetype,
                                                        long contentLength) {
                                Uri uri = Uri.parse(url);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openOptionsMenu();
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        return true;
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
