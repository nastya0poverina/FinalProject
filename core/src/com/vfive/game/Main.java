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
	public static Texture circle, actor, btnPlay, background, human, box, scrap_paper, glue, btnCheck;


	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle = new Texture("circle.png");
		actor = new Texture("circle.png");
		btnPlay = new Texture("start.png");
		background = new Texture("room.jpg");
		human = new Texture("sprite.png");
		box = new Texture("boxCrate_single.png");
		scrap_paper = new Texture("scrap_paper.png");
		glue = new Texture("glue.jpg");
		btnCheck = new Texture("check_btn.png");

		setScreen(new MenuSc(this));
	}

	public static float getObjectRadius(Texture object){
		float radius = 0;
		if (object.getWidth() > object.getHeight())
			radius = object.getWidth();
		if (object.getWidth() < object.getHeight())
			radius = object.getHeight();
		return radius;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
