package com.vfive.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.vfive.game.Main;
import com.vfive.game.Tools.Joystick;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.graphisObj.WorldObj;

public class GameSc implements Screen {

    MenuSc menu;
    Joystick joystick;
    Player player;
    WorldObj book, rectText;

    public GameSc(MenuSc menu) {
        this.menu = menu;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                touch(screenX, screenY, true, pointer);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                touch(screenX, screenY, false, pointer);
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screenY = Main.HEIGHT - screenY;
                touch(screenX, screenY, true, pointer);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        });
        loadActor();

    }

    public void loadActor() {
        // привязывает изображения из assets к обьектам
        joystick = new Joystick(Main.circle, Main.actor, new Point2D(Main.WIDTH / 10 * 9, Main.HEIGHT / 10 * 2), Main.HEIGHT / 3);
        player = new Player(Main.human, new Point2D(Main.WIDTH / 6, Main.HEIGHT / 6), 7, Main.human.getWidth() / 10, Main.human.getHeight() / 10);
        book = new WorldObj(Main.book, new Point2D(Main.WIDTH / 10 * 8, Main.HEIGHT / 10 * 7), Main.book.getWidth(), Main.book.getHeight());
        rectText = new WorldObj(Main.rectText, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 3.5f), Main.WIDTH - Main.WIDTH / 10, Main.HEIGHT / 2);
    }

    public void gameUpdate() {
        // устанавливает на player джостик
        player.setDirection(joystick.getDir());
        player.update();
    }

    public void gameRender(SpriteBatch batch) {
        // отрисовывает всех
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.draw(batch);
        joystick.draw(batch);
        book.draw(batch);

        //проверка пересечения
        if (book.collides(player, book)) {
            rectText.draw(Main.batch);
        }
    }

    @Override
    public void render(float delta) {
        gameUpdate();
        Main.batch.begin();
        Main.batch.draw(Main.background, 0, 0, Main.WIDTH, Main.HEIGHT);
        gameRender(Main.batch);
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

    public void touch(float x, float y, boolean isTouch, int pointer) {
        for (int i = 0; i < 5; i++) {
            joystick.update(x, y, isTouch, pointer);
        }
    }
}