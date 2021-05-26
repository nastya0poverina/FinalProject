package com.vfive.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.buttons.BtnBack;
import com.vfive.game.buttons.BtnScBack;
import com.vfive.game.graphisObj.ItemInventory;
import com.vfive.game.graphisObj.WorldObj;

public class ScInventorySc implements Screen {

    private Main game;
    public WorldObj boxCentre, boxRight1, boxRight2, boxLeft1, boxLeft2;
    public WorldObj boxInterFirst, boxInterSecond, boxInterResult;
    private float boxWidth, boxHeight;
    private Point2D p1, p2, p3, p4, p5, p6, p7, p8, p9;
    private BtnScBack btnBack;
    private Stage stage;
    private SecondFloorSc sc;
    public ItemInventory key, hammer, mathes;

    public ScInventorySc(Main game, SecondFloorSc screen) {
        this.game = game;
        this.sc = screen;
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
        boxInterFirst = new WorldObj(Main.boxItem, p7, boxWidth, boxHeight);
        boxInterSecond = new WorldObj(Main.boxItem, p8, boxWidth, boxHeight);
        boxInterResult = new WorldObj(Main.boxItem, p9, boxWidth, boxHeight);

        btnBack = new BtnScBack(Main.btnBack, game, p6, Main.HEIGHT / 13.5f, Main.WIDTH / 6.8f, sc);

        key = new ItemInventory(false, Main.key, boxLeft2);
        hammer = new ItemInventory(false, Main.hammer, boxCentre);
        mathes = new ItemInventory(false, Main.mathes, boxLeft2);

        stage.addActor(btnBack);

        boxCentre.setEmpty(true);
        boxRight1.setEmpty(true);
        boxRight2.setEmpty(true);
        boxLeft1.setEmpty(true);
        boxLeft2.setEmpty(true);
        boxInterFirst.setEmpty(true);
        boxInterSecond.setEmpty(true);
        boxInterResult.setEmpty(true);
    }

    public void loadPos() {

        boxWidth = Main.WIDTH / 10f;
        boxHeight = boxWidth;

        p1 = new Point2D(Main.WIDTH / 2f, Main.HEIGHT / 10f * 7.5f);
        p2 = new Point2D(Main.WIDTH / 2f + boxWidth * 1.5f, Main.HEIGHT / 10f * 7.5f);
        p3 = new Point2D(Main.WIDTH / 2f + boxWidth * 3, Main.HEIGHT / 10f * 7.5f);
        p4 = new Point2D(Main.WIDTH / 2f - boxWidth * 1.5f, Main.HEIGHT / 10f * 7.5f);
        p5 = new Point2D(Main.WIDTH / 2f - boxWidth * 3, Main.HEIGHT / 10f * 7.5f);
        p6 = new Point2D(Main.WIDTH / 10f * 2f, Main.HEIGHT / 10f * 1f);
        p7 = new Point2D(Main.WIDTH / 2f - boxWidth * 2f, Main.HEIGHT / 10f * 4);
        p8 = new Point2D(Main.WIDTH / 2f, Main.HEIGHT / 10f * 4);
        p9 = new Point2D(Main.WIDTH / 2f + boxWidth * 2f, Main.HEIGHT / 10f * 4);
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

        if (key.getEquipped()) stage.addActor(key);
        if (hammer.getEquipped()) stage.addActor(hammer);
        if (mathes.getEquipped()) stage.addActor(mathes);

        if (SecondFloorSc.safeIsCheck) {
            stage.clear();
            stage.addActor(btnBack);
            if (hammer.getEquipped()) stage.addActor(hammer);
            if (mathes.getEquipped()) stage.addActor(mathes);
        }
        if (SecondFloorSc.laptopCrushIsCheck) {
            stage.clear();
            stage.addActor(btnBack);
            if (key.getEquipped()) stage.addActor(key);
            if (mathes.getEquipped()) stage.addActor(mathes);
        }
        if (SecondFloorSc.candleMeltIsCheck) {
            stage.clear();
            stage.addActor(btnBack);
            if (key.getEquipped()) stage.addActor(key);
            if (hammer.getEquipped()) stage.addActor(hammer);
        }
        stage.draw();
        Gdx.input.setInputProcessor(stage);
    }
}
