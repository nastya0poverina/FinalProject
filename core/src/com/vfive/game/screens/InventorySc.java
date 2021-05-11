package com.vfive.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.buttons.BtnBack;
import com.vfive.game.buttons.BtnInventory;
import com.vfive.game.graphisObj.ItemInventory;
import com.vfive.game.graphisObj.WorldObj;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InventorySc implements Screen {

    private static Logger logger = Logger.getLogger(Game.class.getName());

    Main game;
    public WorldObj boxCentre, boxRight1, boxRight2, boxLeft1, boxLeft2;
    WorldObj boxInterFirst, boxInterSecond, boxInterResult;
    float boxWidth, boxHeight;
    Point2D p1, p2, p3, p4, p5, p6, p7, p8, p9;
    BtnBack btnBack;
    Stage stage;
    GameSc screen;

    public InventorySc(Main game, GameSc screen) {
        this.game = game;
        this.screen = screen;
        loadPos();
        loadObj();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Main.batch.begin();
        Main.batch.draw(Main.backgroundInventory, 0, 0, Main.WIDTH, Main.HEIGHT);
        renderDraw(Main.batch);
        Main.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
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

        stage = new Stage();

        boxCentre = new WorldObj(Main.boxItem, p1, boxWidth, boxHeight);
        boxRight1 = new WorldObj(Main.boxItem, p2, boxWidth, boxHeight);
        boxRight2 = new WorldObj(Main.boxItem, p3, boxWidth, boxHeight);
        boxLeft1 = new WorldObj(Main.boxItem, p4, boxWidth, boxHeight);
        boxLeft2 = new WorldObj(Main.boxItem, p5, boxWidth, boxHeight);
        boxLeft2 = new WorldObj(Main.boxItem, p5, boxWidth, boxHeight);
        boxInterFirst = new WorldObj(Main.boxItem, p7, boxWidth, boxHeight);
        boxInterSecond = new WorldObj(Main.boxItem, p8, boxWidth, boxHeight);
        boxInterResult = new WorldObj(Main.boxItem, p9, boxWidth, boxHeight);

        btnBack = new BtnBack(Main.btnBack, game, p6, Main.btnBack.getHeight() * 5, Main.btnBack.getWidth() * 5, screen);

        stage.addActor(btnBack);


        boxCentre.setEmpty(true);
        boxRight1.setEmpty(true);
        boxRight2.setEmpty(true);
        boxLeft1.setEmpty(true);
        boxLeft2.setEmpty(true);
    }

    public void renderDraw(SpriteBatch batch) {
        boxCentre.draw(batch);
        boxRight1.draw(batch);
        boxRight2.draw(batch);
        boxLeft1.draw(batch);
        boxLeft2.draw(batch);
        boxInterFirst.draw(batch);
        boxInterSecond.draw(batch);
        boxInterResult.draw(batch);

        btnBack.draw(batch);

        if (GameSc.glue1.getEquipped() == true) {
            stage.addActor(GameSc.glue1);
        }
        if (GameSc.paper1.getEquipped() == true) {
            stage.addActor(GameSc.paper1);
        }
        stage.draw();
        Gdx.input.setInputProcessor(stage);

    }

    public WorldObj getFreeBox() {
        if (boxLeft2.getEmpty() == true){
            boxLeft2.setEmpty(false);
            return boxLeft2;
        }
        if (boxLeft1.getEmpty() == true){
            boxLeft1.setEmpty(false);
            return boxLeft1;
        }
        if (boxCentre.getEmpty() == true){
            boxCentre.setEmpty(false);
            return boxCentre;
        }
        if (boxRight1.getEmpty() == true){
            boxRight1.setEmpty(false);
            return boxRight1;
        }
        if (boxRight2.getEmpty() == true){
            boxRight2.setEmpty(false);
            return boxRight2;
        }
        return null;
    }

    public void loadPos() {

        boxWidth = Main.boxItem.getWidth() * 3;
        boxHeight = Main.boxItem.getHeight() * 3;

        p1 = new Point2D(Main.WIDTH / 2f, Main.HEIGHT / 10f * 7.5f);
        p2 = new Point2D(Main.WIDTH / 2f + boxWidth * 1.5f, Main.HEIGHT / 10f * 7.5f);
        p3 = new Point2D(Main.WIDTH / 2f + boxWidth * 3, Main.HEIGHT / 10f * 7.5f);
        p4 = new Point2D(Main.WIDTH / 2f - boxWidth * 1.5f, Main.HEIGHT / 10f * 7.5f);
        p5 = new Point2D(Main.WIDTH / 2f - boxWidth * 3, Main.HEIGHT / 10f * 7.5f);
        p6 = new Point2D(Main.WIDTH / 10f * 1f, Main.HEIGHT / 10f * 1f);
        p7 = new Point2D(Main.WIDTH / 2f - boxWidth * 2.5f, Main.HEIGHT / 10f * 4);
        p8 = new Point2D(Main.WIDTH / 2f, Main.HEIGHT / 10f * 4);
        p9 = new Point2D(Main.WIDTH / 2f + boxWidth * 2.5f, Main.HEIGHT / 10f * 4);
    }
}
