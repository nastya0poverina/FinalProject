package com.vfive.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.buttons.BtnStart;
import com.vfive.game.graphisObj.WorldObj;

import javax.xml.soap.Text;

public class WinSc implements Screen {

    private Main game;
    private WorldObj win;

    public WinSc(Main game) {
        this.game = game;
        win = new WorldObj(Main.win, new Point2D(Main.WIDTH / 2f - Main.win.getWidth() / 2f, Main.HEIGHT / 2f + Main.win.getHeight() / 2f), Main.win.getWidth() * 3, Main.win.getHeight() * 3 );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        win.draw(Main.batch);
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
}
