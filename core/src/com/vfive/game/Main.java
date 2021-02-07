package com.vfive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.MenuSc;

public class Main extends Game  {
	public static SpriteBatch batch;
	public static int WIDTH;
	public static int HEIGHT;
	public static Texture circle, actor, btnPlay, background, human;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle = new Texture("circle.png");
		actor = new Texture("circle.png");
		btnPlay = new Texture("btn_play2.jpg");
		background = new Texture("room.jpg");
		human = new Texture("human3.png");
		setScreen(new MenuSc(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
