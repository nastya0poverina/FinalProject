package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.SecondFloorSc;

import java.util.logging.Logger;

public class BtnScBack extends Actor {

    private Texture btnImage;
    private SecondFloorSc screen;

    public BtnScBack(Texture btnImage, Main game, Point2D point2D, float height, float width, SecondFloorSc screen) {
        this.btnImage = btnImage;
        this.screen = screen;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX());
        setY(point2D.getY());
        addListener(new BtnBackListener(game, screen));
    }

    public void draw(Batch batch) {
        batch.draw(btnImage, this.getX(), this.getY() , this.getWidth(), this.getHeight());
    }

    private class BtnBackListener extends InputListener {
        private Main game;
        private SecondFloorSc screen;

        public BtnBackListener(Main game, SecondFloorSc screen) {
            this.game = game;
            this.screen = screen;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(screen);
            return true;
        }
    }
}
