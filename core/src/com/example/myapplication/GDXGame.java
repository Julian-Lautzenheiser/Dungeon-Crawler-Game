package com.example.myapplication;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GDXGame extends ApplicationAdapter {

	private TiledMap map;
	private TiledMapRenderer renderer;
	private float unitScale = 1 / 32f;
	private OrthographicCamera camera;
	private Texture sprite;

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 13, 10);
		camera.update();

		map = new TmxMapLoader().load("room1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, unitScale);


	}

	@Override
	public void render () {
		ScreenUtils.clear(0.55f, 0.55f, 0.55f, 1f);
		camera.update();
		renderer.setView(camera);
		renderer.render();


	}
	
	@Override
	public void dispose () {
		map.dispose();
	}
}
