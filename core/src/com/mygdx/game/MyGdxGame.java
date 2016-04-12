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

public class MyGdxGame extends ApplicationAdapter {

	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Integer x,y;
	private Array<Rectangle> raindrops;
	private Array<String> filenames;
	Array<Texture> dropImages;
	private int i;
	private Rectangle bucket;
	private long lastDropTime;
	//private String[] filenames;

	@Override
	public void create() {
		// load the images for the droplet and the bucket, 64x64 pixels each
//		dropImage = new Texture(Gdx.files.internal("droplet.png"));

		String[] filenames={"a.jpg","b.jpg","c.jpg","d.jpg","e.jpg","f.jpg","g.jpg","h.jpg","i.jpg","j.jpg","k.jpg","l.jpg","m.jpg","n.jpg","o.jpg","p.jpg","q.jpg","r.jpg","s.jpg","t.jpg","u.jpg","w.jpg","z.jpg"};


		dropImages=new Array<Texture>();

Integer z;

		for (z=0;z<23;z++) {
			dropImages.add(new Texture(Gdx.files.internal(filenames[z])));
	//Gdx.app.log("MG",dropImages.get(z).toString());
			}

		bucketImage = new Texture("bucket.png");

		raindrops = new Array<Rectangle>();
		spawnRaindrop();

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
	//	rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		x=368;
		y=20;

	}


	@Override
	public void render() {


		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(bucketImage,x,y);

		for(Rectangle raindrop: raindrops) {
			if (raindrop.y == 480) {
				Random generator = new Random();
				i = generator.nextInt(23);
			}

			batch.draw(dropImages.get(i), raindrop.x, raindrop.y);
			Gdx.app.log("MG", dropImages.get(i).toString());
		}

		batch.end();

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		Iterator<Rectangle> iter = raindrops.iterator();
		while(iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) iter.remove();
			if(raindrop.overlaps(bucket)) {
				dropSound.play();
				iter.remove();
			}
		}

		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			x = (int)touchPos.x - 64 / 2;
			bucket.x=x;
		}
	}

	private void spawnRaindrop() {


		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
}
