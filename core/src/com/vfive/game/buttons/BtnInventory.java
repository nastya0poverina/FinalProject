package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.screens.InventorySc;

import java.util.logging.Logger;

public class BtnInventory extends Actor {
    private static Logger log = Logger.getLogger(BtnCheck.class.getName());

    private Texture btnImage;

    public BtnInventory(Texture btnImage, Main game, Point2D point2D, float height, float width) {
        this.btnImage = btnImage;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX());
        setY(point2D.getY());
        addListener(new BtnInventoryListener(game));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(btnImage, this.getX(), this.getY() , this.getWidth(), this.getHeight());
    }

    private class BtnInventoryListener extends InputListener{
        private Main game;

        public BtnInventoryListener(Main game) {
            this.game = game;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new InventorySc(game));
            log.info("est kasanie");
            return true;
        }
    }

}

