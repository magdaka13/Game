package com.FallingLettersGame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class ScoresScreenChooseUser implements Screen {

    final MainGame game;
    private int score;

    private OrthographicCamera camera;
private Texture user1,user2,user3,user4;

    public ScoresScreenChooseUser(final MainGame gam, int s) {
        game = gam;
        score=s;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        user1 = new Texture("user1.jpg");
        user2 = new Texture("user2.jpg");
        user3 = new Texture("user3.jpg");
        user4 = new Texture("user4.jpg");

    }

    @Override
    public void render(float delta) {
        String[] strings;
        int i;
        strings=new String[4];

        Gdx.gl.glClearColor(1, 1, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

     //   strings=game.db.SelectAll();

        game.batch.begin();

       // for (i=0;i<4;i++) {
        //    game.font.draw(game.batch, strings[i], 200, 450-i*50);
       // }

        //game.font.draw(game.batch,  score + " literek", 200, 150);
        game.font.draw(game.batch, "Kim jestes? ", 10, 450);
        game.batch.draw(user1,10,50);
        game.batch.draw(user2,158,50);
        game.batch.draw(user3,296,50);
        game.batch.draw(user4,425,50);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            //user1
            if (((touchPos.x >=10)&&(touchPos.x<=148))&&((touchPos.y >=50)&&(touchPos.y<=120))) {

                game.db.UpdateRec(score, "Filip");
                game.setScreen(new ScoresScreen(game,score));
                dispose();
            }

            //user2
            if (((touchPos.x >=158)&&(touchPos.x<=286))&&((touchPos.y >=50)&&(touchPos.y<=120))) {

                game.db.UpdateRec(score, "Borys");
                game.setScreen(new ScoresScreen(game,score));
                dispose();
            }

            //user3
            if (((touchPos.x >=296)&&(touchPos.x<=415))&&((touchPos.y >=50)&&(touchPos.y<=120))) {

                game.db.UpdateRec(score, "Magda");
                game.setScreen(new ScoresScreen(game,score));
                dispose();
            }

            //user4
            if (((touchPos.x >=425)&&(touchPos.x<=500))&&((touchPos.y >=50)&&(touchPos.y<=120))) {

                game.db.UpdateRec(score, "Szymon");
                game.setScreen(new ScoresScreen(game,score));
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
user1.dispose();
        user2.dispose();
        user3.dispose();
        user4.dispose();
    }
}