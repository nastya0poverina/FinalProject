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

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;

public class GameSc implements Screen {

    MenuSc menu;
    Joystick joystick;
    Player player;
    WorldObj box1, box2, glue, paper, btnCheck;

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
        player = new Player(Main.human, new Point2D(Main.WIDTH / 6, Main.HEIGHT / 6), 5, Main.human.getWidth(), Main.human.getHeight());
        btnCheck = new WorldObj(Main.btnCheck, new Point2D(Main.WIDTH / 10 * 9, Main.HEIGHT / 10 * 4 + 50), 83f * 5, 26f * 5);
        box1 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10 * 3, Main.HEIGHT / 10 * 7));
        box2 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10 * 8, Main.HEIGHT / 10 * 5));
        paper = new WorldObj(Main.scrap_paper, new Point2D(Main.WIDTH / 10 * 8 - box2.getWidth() + 50, Main.HEIGHT / 10 * 5), Main.scrap_paper.getWidth() / 7, Main.scrap_paper.getHeight() / 7 );
        glue = new WorldObj(Main.glue, new Point2D(Main.WIDTH / 10 * 3 + box1.getWidth() + 50, Main.HEIGHT / 10 * 7));
    }

    public void gameUpdate(float dt) {
        // устанавливает на player джостик
        player.setDirection(joystick.getDir());
        player.updatePlayer(dt);
    }

    public void gameRender(SpriteBatch batch) {
        // отрисовывает всех
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        joystick.draw(batch);
        box1.draw(batch);
        box2.draw(batch);

        if (box1.isCheck(player, box1)) {
            btnCheck.draw(Main.batch);
            box1.collides(player, box1);
        }
        if (box2.isCheck(player, box2)){
            btnCheck.draw(Main.batch);
            box2.collides(player, box2);
        }
    }

    @Override
    public void render(float delta) {
        gameUpdate(delta);
        Main.batch.begin();
        Main.batch.draw(Main.background, 0, 0, Main.WIDTH, Main.HEIGHT);
        gameRender(Main.batch);
        Main.batch.draw(player.getPlayer(), player.position.getX(), player.position.getY());
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