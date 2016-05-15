package com.LettersGame.LettersGame;

import android.app.Activity;
import android.webkit.WebView;
import android.os.Bundle;


/**
 * Created by magda on 2016-04-13.
 */
public class AlphabetFilm extends Activity {
    //    private FrameLayout mContentView,mCustomViewContainer;
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        String frameVideo = "<html><body>Youtube video .. <br> <iframe width='320' height='315' src='https://www.youtube.com/watch?v=TlP_sF3lZ_0&feature=player_embedded' frameborder='0' allowfullscreen></iframe></body></html>";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet);

        webView = (WebView) findViewById(R.id.webview1);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        webView.loadUrl("https://www.youtube.com/watch?v=TlP_sF3lZ_0");

    }



}