package com.LettersGame.LettersGame;

import android.os.Bundle;

import com.FallingLettersGame.game.MainGame;
import com.FeedReaderDBHelper.FeedReader.SQLiteHelper;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication {
	private SQLiteHelper sQLiteHelper;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		sQLiteHelper = new SQLiteHelper(this);

		initialize(new MainGame(sQLiteHelper), config);
	}
}
