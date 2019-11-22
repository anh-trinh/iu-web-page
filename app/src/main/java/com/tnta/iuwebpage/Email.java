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
public class Email extends Activity{

    private Preferences preference;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        webView = (WebView)findViewById(R.id.webView);

        preference = new Preferences(getApplication());

        String url = "https://login.microsoftonline.com/login.srf?wa=wsignin1.0&rpsnv=4&ct=1445764103&rver=6.6.6556.0&wp=MBI_SSL&wreply=https%3a%2f%2foutlook.office365.com%2fowa%2f&id=260563&CBCXT=out&msafed=0";
        final String m_username = preference.getID()+"@student.hcmiu.edu.vn";
        final String m_password = preference.getMUserPassword();
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
                preference.setTab("2");
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
                    if (m_username != null && m_password != null) {
                        v.loadUrl("javascript: var user='" + m_username + "';var pass='" + m_password + "';document.getElementById('cred_userid_inputtext').value=user;document.getElementById('cred_password_inputtext').value=pass;document.getElementById('cred_keep_me_signed_in_checkbox').click();document.getElementById('cred_sign_in_button').click();");
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
