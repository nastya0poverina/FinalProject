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
import java.util.logging.Logger;

public class Main extends Game  {

	public static Logger logger = Logger.getLogger(Game.class.getName());

	public static SpriteBatch batch;
	public static int WIDTH;
	public static int HEIGHT;
	public static int moneySum = 0;
	public static Texture circle, actor, btnPlay, backgroundRoom, human, box, scrap_paper, glue,
			btnCheck, backgroundInventory, boxItem, btnInventory, player, btnBack, frame,
			framePicture, glass, picture, prompt, backgroundRoom2, cupboard, table, key, safeClose, hanger, btnNext, safeOpen;
	public static Texture money_0, money_1, money_2, money_3, money_4, money_5 ;
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
		scrap_paper = new Texture("paper.png");
		glue = new Texture("glue.png");
		btnCheck = new Texture("check_btn.png");
		backgroundInventory = new Texture("backgroundInventory.jpg");
		boxItem = new Texture("boxitem.png");
		btnInventory = new Texture("button_inventory.png");
		money_0 = new Texture("money_0.png");
		money_1 = new Texture("money_ 1.png");
		money_2 = new Texture("money_ 2.png");
		money_3 = new Texture("money_ 3.png");
		money_4 = new Texture("money_ 4.png");
		money_5 = new Texture("money_ 5.png");
		player = new Texture("player_idle.png");
		btnBack = new Texture("btn_back.png");
		frame = new Texture("frame.png");
		framePicture = new Texture("frame_picture.png");
		glass = new Texture("glass.png");
		picture = new Texture("picture.png");
		prompt = new Texture("prompt.png");
		backgroundRoom2 = new Texture("background_floor_2.png");
		cupboard = new Texture("Сupboard.png");
		table = new Texture("table.png");
		key = new Texture("key.png");
		safeClose = new Texture("safe_close.png");
		hanger = new Texture("hanger.png");
		btnNext = new Texture("button_next.png");
		safeOpen = new Texture("safe_open.png");

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
