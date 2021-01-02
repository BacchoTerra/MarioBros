package com.bacchoterra.mariobros;

import com.bacchoterra.mariobros.screens.PlayScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jogo extends Game {


	public SpriteBatch batch;

	public static final float V_WIDTH = 400;
	public static final float V_HEIGHT = 208;
	public static final float PPM = 100;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
