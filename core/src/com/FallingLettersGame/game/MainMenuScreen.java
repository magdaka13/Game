package com.FallingLettersGame.game;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.math.Vector3;

        import javax.xml.soap.Text;

public class MainMenuScreen implements Screen {

    final MainGame game;
    private Texture gameImg;
    private Texture resultsImg;
    private  OrthographicCamera camera;

    public MainMenuScreen(final MainGame gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
//game.font.setColor(0,0,0,1);

        gameImg = new Texture("game.jpg");
        resultsImg= new Texture("results.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(gameImg,100,150);
        game.batch.draw(resultsImg,400,150);


        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

    //        Gdx.app.log("MG", touchPos.x + "");
            //GRA
            if (((touchPos.x >=100)&&(touchPos.x<=228))&&((touchPos.y >=160)&&(touchPos.y<=260))) {
                Gdx.app.log("MG", touchPos.x + "");

                game.setScreen(new FallingLettersGame(game));
                dispose();
            }

            //WYNIKI
            if (((touchPos.x >=400)&&(touchPos.x<=628))&&((touchPos.y >=160)&&(touchPos.y<=282))) {
                //Gdx.app.log("MG", touchPos.x + "");

                game.setScreen(new ScoresScreen(game,0));
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
       gameImg.dispose();
        resultsImg.dispose();
    }
}