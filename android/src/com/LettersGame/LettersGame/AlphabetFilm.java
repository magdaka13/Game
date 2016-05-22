package com.LettersGame.LettersGame;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebViewClient;
import android.view.View;
import android.widget.*;
import android.os.Handler;
import java.lang.reflect.*;

public class AlphabetFilm extends Activity {
    //    private FrameLayout mContentView,mCustomViewContainer;
    private WebView webView;
    final Activity activity = this;
public Toast T,Msg;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        T=new Toast(this);
        Msg=T.makeText(AlphabetFilm.this, "Czekaj",Toast.LENGTH_SHORT );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet);

        webView = (WebView) findViewById(R.id.webview1);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);


        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {

            public CustomViewCallback mCustomViewCallback;

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                if (view instanceof FrameLayout) {
                    FrameLayout customViewContainer = (FrameLayout) view;
                    mCustomViewCallback = callback;
                    if (customViewContainer.getFocusedChild() instanceof VideoView) {
                        VideoView customVideoView = (VideoView) customViewContainer.getFocusedChild();
                        try {
                            Field mUriField = VideoView.class.getDeclaredField("mUri");
                            mUriField.setAccessible(true);
                            Uri uri = (Uri) mUriField.get(customVideoView);
                            Log.w("uri", "" + uri);
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(uri, "video/*");
                            startActivity(intent);
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    mCustomViewCallback.onCustomViewHidden();
                                }
                            });
                        } catch (Exception e) {

                        }
                    }
                }
            }

            public void onProgressChanged(WebView view, int progress) {

                activity.setProgress(progress * 100);
                if (progress == 100) {
                    activity.setTitle("Piosenka");
                    Msg.cancel();
                }

                if (progress<100)
                 {
                    Msg.show();

                }
            }
        });


        webView.loadUrl("https://www.youtube.com/watch?v=TlP_sF3lZ_0");

        Button b_menu=(Button) findViewById(R.id.btn_back);
        b_menu.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Msg.cancel();

                                          Intent intent = new Intent(AlphabetFilm.this, MainActivity.class);
                                          startActivity(intent);
                                      }
                                  }

        );
      }

@Override
public void onBackPressed()
    {
        Msg.cancel();

    }


}