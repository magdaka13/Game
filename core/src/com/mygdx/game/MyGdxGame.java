package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.*;

import java.util.*;

import com.mygdx.game.Letter;

public class MyGdxGame extends ApplicationAdapter {

    private Texture bucketImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Integer x, y;
    private Array<Rectangle> lettersRetangle;

    private Array<Letter> letter;
    private Rectangle bucket;
    private long lastDropTime;


    @Override
    public void create() {

        bucketImage = new Texture("bucket.png");

        lettersRetangle = new Array<Rectangle>();
        letter = new Array<Letter>();

        spawnLetter();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        x = 368;
        y = 20;


    }


    @Override
    public void render() {


        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bucketImage, x, y);
        int z = 0;
        for (Rectangle raindrop : lettersRetangle) {

            batch.draw(letter.get(z).getLetter(), raindrop.x, raindrop.y);
            z++;

        }

        batch.end();

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnLetter();

        z = 0;
        Iterator<Rectangle> iter = lettersRetangle.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0) {
                iter.remove();
                letter.removeIndex(z);
            }
            if (raindrop.overlaps(bucket)) {
                letter.get(z).getSound().play();
                iter.remove();
                letter.removeIndex(z);
            }
            z++;
        }

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            x = (int) touchPos.x - 64 / 2;
            bucket.x = x;
        }
    }

    private void spawnLetter() {
        Rectangle letterRec = new Rectangle();
        letterRec.x = MathUtils.random(0, 800 - 64);
        letterRec.y = 480;
        letterRec.width = 64;
        letterRec.height = 64;
        lettersRetangle.add(letterRec);
        lastDropTime = TimeUtils.nanoTime();
        int i = MathUtils.random(0, 26);
        letter.add(new Letter(i));
        Gdx.app.log("MG", i + "");

        //	j=j+1;

    }
}
