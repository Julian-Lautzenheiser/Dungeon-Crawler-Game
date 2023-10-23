package com.example.myapplication;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture img;
    @Override
    public void create() {
        setBatch(new SpriteBatch());
        setImg(new Texture("badlogic.jpg"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        getBatch().begin();
        getBatch().draw(getImg(), 0, 0);
        getBatch().end();
    }
    @Override
    public void dispose() {
        getBatch().dispose();
        getImg().dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }
}