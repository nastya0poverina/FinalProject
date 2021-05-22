package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.screens.SecondFloorSc;

public class BtnNextFloor extends Actor {

    private Texture texture;
    private Player player;

    public BtnNextFloor(Texture texture, Main game, Point2D point2D, float height, float width, Player player) {
        this.texture = texture;
        this.player = player;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX());
        setY(point2D.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX() , this.getY() , this.getWidth(), this.getHeight());
    }

    public void btnDraw(Batch batch){
        batch.draw(texture, this.getX(), this.getY() , this.getWidth(), this.getHeight());
    }
}
