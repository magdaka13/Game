package com.FallingLettersGame.game;

import com.HighScoreDB.HighScoresDB;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.HighscoresDB.SQLiteHelper;
/**
 * Created by magda on 2016-04-17.
 */
public class MainGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public HighScoresDB db;

    public void create() {
        db=new HighScoresDB();
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        font.getData().setScale(3.0f,3.0f);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        db.dispose();
    }

}