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

    Texture texture;
    Player player;

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
        batch.draw(texture, this.getX() + this.getWidth(), this.getY() + this.getHeight(), this.getWidth(), this.getHeight());
    }

    public void btnDraw(Batch batch){
        batch.draw(texture, this.getX(), this.getY() , this.getWidth(), this.getHeight());
    }

    public class NextListener extends InputListener{
        Main game;

        public NextListener(Main game) {
            this.game = game;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new SecondFloorSc(game));
            return true;
        }
    }
}
