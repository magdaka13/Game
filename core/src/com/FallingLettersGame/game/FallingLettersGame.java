package com.FallingLettersGame.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.*;

import java.util.*;

public class FallingLettersGame implements Screen {

    final MainGame game;
    private Texture bucketImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Integer x, y;
    private Array<Rectangle> lettersRetangle;

    private Array<com.FallingLettersGame.game.Letter> letter;
    private Rectangle bucket;
    private long lastDropTime;
    private long LettersCatched;
    private long maxLetterNo;
    private long generatedLetters;

    public FallingLettersGame(final MainGame gam) {

        maxLetterNo=50;
        this.game=gam;
        bucketImage = new Texture("owl.png");

        lettersRetangle = new Array<Rectangle>();
        letter = new Array<com.FallingLettersGame.game.Letter>();

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
        LettersCatched=0;


    }


    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.setColor(0, 0, 0, 1);
        game.font.draw(game.batch, "" + LettersCatched, 0, 480);
        game.batch.draw(bucketImage, x, y);
        int z = 0;
        for (Rectangle letterRec : lettersRetangle) {

            game.batch.draw(letter.get(z).getLetter(), letterRec.x, letterRec.y);
            z++;

        }

        game.batch.end();

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnLetter();

        z = 0;
        Iterator<Rectangle> iter = lettersRetangle.iterator();
        while (iter.hasNext()) {
            Rectangle letterRec = iter.next();
            letterRec.y -= 200 * Gdx.graphics.getDeltaTime();
            if (letterRec.y + 64 < 0) {
                iter.remove();
                letter.removeIndex(z);
            }
            if (letterRec.overlaps(bucket)) {
                LettersCatched++;
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
        int i = MathUtils.random(0, 25);
        letter.add(new com.FallingLettersGame.game.Letter(i));

        generatedLetters++;
        //Gdx.app.log("MG", generatedLetters + "");

        if ((generatedLetters>maxLetterNo) || (LettersCatched>maxLetterNo))
        {
            game.setScreen(new ScoresScreen(game,LettersCatched));
            dispose();
        }

      }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        // dispose of all the native resources

        bucketImage.dispose();
        //batch.dispose();

        //dispose tables of letters (img and sound)

        for (Letter l : letter)
                 { l.freeMem(); }

        Iterator<Rectangle> iter = lettersRetangle.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }

        }
}
