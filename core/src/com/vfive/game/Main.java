package com.vfive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vfive.game.graphisObj.Inventory;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.MenuSc;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main extends Game  {

	public static SpriteBatch batch;
	public static int WIDTH;
	public static int HEIGHT;
	public static Texture circle, actor, btnPlay, backgroundRoom, human, box, scrap_paper, glue, btnCheck, backgroundInventory, boxItem, btnInventory;
	public static Texture money_0;
	public static Inventory inventory;


	@Override
	public void create () {

		batch = new SpriteBatch();
		inventory = new Inventory();

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle = new Texture("circle.png");
		actor = new Texture("circle.png");
		btnPlay = new Texture("start.png");
		backgroundRoom = new Texture("background_room.jpg");
		human = new Texture("sprite.png");
		box = new Texture("box1.png");
		scrap_paper = new Texture("scrap_paper.png");
		glue = new Texture("glue.jpg");
		btnCheck = new Texture("check_btn.png");
		backgroundInventory = new Texture("background_inventory.jpg");
		boxItem = new Texture("boxitem.png");
		btnInventory = new Texture("button_inventory.png");
		money_0 = new Texture("money_0.png");

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
