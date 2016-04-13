package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by magda on 2016-04-13.
 */
public final class Letter {

    private Texture letter;
    private Sound sound;

    public Texture getLetter() {
        return letter;
    }

    public Sound getSound() {
        return sound;
    }

    public Letter(int i) { // private constructor
        String[] filenames = {"a.jpg", "b.jpg", "c.jpg", "d.jpg", "e.jpg", "f.jpg", "g.jpg", "h.jpg", "i.jpg", "j.jpg", "k.jpg", "l.jpg", "m.jpg", "n.jpg", "o.jpg", "p.jpg", "q.jpg", "r.jpg", "s.jpg", "t.jpg", "u.jpg", "w.jpg", "v.jpg", "y.jpg", "x.jpg", "z.jpg"};
        String[] sounds = {"a.mp3", "b.mp3", "c.mp3", "d.mp3", "e.mp3", "f.mp3", "g.mp3", "h.mp3", "i.mp3", "j.mp3", "k.mp3", "l.mp3", "m.mp3", "n.mp3", "o.mp3", "p.mp3", "q.mp3", "r.mp3", "s.mp3", "t.mp3", "u.mp3", "w.mp3", "v.mp3", "y.mp3", "x.mp3", "z.mp3"};

        letter = new Texture(Gdx.files.internal(filenames[i]));
        sound = new Gdx().audio.newSound(Gdx.files.internal(sounds[i]));

    }


}
