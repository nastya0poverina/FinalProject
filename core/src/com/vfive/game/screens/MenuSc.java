package com.vfive.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vfive.game.Main;
import com.vfive.game.buttons.BtnStart;

public class MenuSc implements Screen {

    public Game game;
    private BtnStart btnStart;
    private Stage stage;

    public MenuSc(Main game) {
        this.game = game;
        btnStart = new BtnStart(Main.btnPlay, game);
        stage = new Stage();
        stage.addActor(btnStart);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        stage.draw();
        Main.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
/*
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameSc(this));
        }
*/
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
}