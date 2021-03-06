package com.LettersGame.LettersGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_demo=(Button)findViewById(R.id.btn_Demo);
        b_demo.setOnClickListener(new View.OnClickListener() {

                                      public void onClick(View v) {
                                          Intent intent = new Intent(MainActivity.this, AlphabetFilm.class);
                                          startActivity(intent);
                                      }
                                  }

        );

        Button b_game=(Button)findViewById(R.id.btn_game);
        b_game.setOnClickListener(new View.OnClickListener()
            {
               public void onClick(View v)
                  {
                   Intent intent = new Intent(MainActivity.this, AndroidLauncher.class);
                  startActivity(intent);
                  }
            }

        );

    }

    @Override
    public void onBackPressed()
    {
        finish();

    }

}
