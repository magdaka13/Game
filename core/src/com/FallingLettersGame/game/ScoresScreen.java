package com.FallingLettersGame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.*;

public class ScoresScreen implements Screen {

    final MainGame game;
    private long score;

    private OrthographicCamera camera;
private Texture gameImg;

    public ScoresScreen(final MainGame gam,long s) {
        game = gam;
        score=s;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        gameImg = new Texture("game.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch,  score + " literek", 200, 150);
        //game.font.draw(game.batch, "Gram ", 10, 50);
        game.batch.draw(gameImg,10,50);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (((touchPos.x >=10)&&(touchPos.x<=138))&&((touchPos.y >=50)&&(touchPos.y<=150))) {

                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
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

    }
}