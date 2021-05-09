package com.vfive.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.graphisObj.ItemInventory;
import com.vfive.game.graphisObj.WorldObj;

import java.util.ArrayList;
import java.util.List;

public class InventorySc implements Screen {

    Main game;
    WorldObj boxCentre, boxRight1, boxRight2, boxLeft1, boxLeft2;
    float boxWidth, boxHeight;
    Point2D p1, p2, p3, p4, p5;


    public InventorySc(Main game) {
        this.game = game;
    }

    @Override
    public void show() {

        boxWidth = Main.boxItem.getWidth() * 4;
        boxHeight = Main.boxItem.getHeight() * 4;

        p1 = new Point2D(Main.WIDTH / 2, Main.HEIGHT / 10 * 7);
        p2 = new Point2D(Main.WIDTH / 2 + boxWidth * 2, Main.HEIGHT / 10 * 7);
        p3 = new Point2D(Main.WIDTH / 2 + boxWidth * 4, Main.HEIGHT / 10 * 7);
        p4 = new Point2D(Main.WIDTH / 2 - boxWidth * 2, Main.HEIGHT / 10 * 7);
        p5 = new Point2D(Main.WIDTH / 2 - boxWidth * 4, Main.HEIGHT / 10 * 7);

        loadObj();

        boxCentre.setEmpty(false);
        boxRight1.setEmpty(false);
        boxRight2.setEmpty(false);
        boxLeft1.setEmpty(false);
        boxLeft2.setEmpty(false);
    }

    @Override
    public void render(float delta) {
        Main.batch.begin();
        Main.batch.draw(Main.backgroundInventory, 0, 0, Main.WIDTH, Main.HEIGHT);
        renderDraw(Main.batch);
        Main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void loadObj() {
        boxCentre = new WorldObj(Main.boxItem, p1, boxWidth, boxHeight);
        boxRight1 = new WorldObj(Main.boxItem, p2, boxWidth, boxHeight);
        boxRight2 = new WorldObj(Main.boxItem, p3, boxWidth, boxHeight);
        boxLeft1 = new WorldObj(Main.boxItem, p4, boxWidth, boxHeight);
        boxLeft2 = new WorldObj(Main.boxItem, p5, boxWidth, boxHeight);
    }

    public void renderDraw(SpriteBatch batch) {
        boxCentre.draw(batch);
        boxRight1.draw(batch);
        boxRight2.draw(batch);
        boxLeft1.draw(batch);
        boxLeft2.draw(batch);

    }
}
