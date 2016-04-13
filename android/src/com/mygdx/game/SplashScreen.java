package com.mygdx.game;

import android.app.Activity;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer;
import android.widget.VideoView;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
/**
 * Created by magda on 2016-04-13.
 */
public class SplashScreen extends Activity implements OnCompletionListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        String fileName = "android.resource://"+  getPackageName() +"/"+R.raw.literki;

        VideoView vv = (VideoView) this.findViewById(R.id.surface);
        vv.setVideoURI(Uri.parse(fileName));
        vv.setOnCompletionListener(this);
        vv.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, AndroidLauncher.class);
        startActivity(intent);
        finish();
    }
}