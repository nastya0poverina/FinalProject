package com.vfive.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.vfive.game.Main;

public class MenuSc extends Game implements Screen {

    public Game game;

    public MenuSc(Game game) {
        this.game = game;
    }

    @Override
    public void create() {
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(Main.btnPlay, Main.WIDTH / 2 - Main.btnPlay.getWidth() / 2, Main.HEIGHT / 2 - Main.btnPlay.getHeight() / 2);
        Main.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameSc(this));
        }
    }

    @Override
    public void hide() {

    }
}