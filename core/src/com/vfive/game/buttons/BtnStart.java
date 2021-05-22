package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.InventorySc;

public class BtnStart extends Actor {

    private Texture img;

    public BtnStart(Texture img, Main game ) {
        addListener(new BtnListener(game));
        this.img = img;
        setHeight(Main.btnPlay.getHeight() * 4);
        setWidth(Main.btnPlay.getWidth() * 4);
        setX(Main.WIDTH / 2f - this.getWidth() / 2f);
        setY(Main.HEIGHT / 2f - this.getHeight() / 2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, this.getX(), this.getY() , this.getWidth(), this.getHeight());
    }

    private class BtnListener extends InputListener {

        private Main game;

        public BtnListener( Main game) {
            this.game = game;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new GameSc(game));
            return true;
        }
    }
}
