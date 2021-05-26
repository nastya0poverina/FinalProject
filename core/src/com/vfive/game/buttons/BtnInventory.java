package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.InventorySc;

import java.util.logging.Logger;

public class BtnInventory extends Actor {

    private Texture btnImage;

    public BtnInventory(Texture btnImage, Main game, Point2D point2D, float height, float width) {
        this.btnImage = btnImage;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX() - this.getWidth() / 2);
        setY(point2D.getY() - this.getHeight() / 2);
    }

    public void draw(Batch batch) {
        batch.draw(btnImage, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}