package com.vfive.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vfive.game.Main;
import com.vfive.game.Tools.Joystick;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.buttons.BtnInventory;
import com.vfive.game.graphisObj.WorldObj;

public class SecondFloorSc implements Screen {

    Main game;
    Joystick joystick;
    Player player;
    BtnInventory btnInventory;
    ScInventorySc inventorySc;
    WorldObj money, cupboard, table;

    public SecondFloorSc(Main game) {
        this.game = game;
        loadActor();
    }

    public void loadActor(){

        joystick = new Joystick(Main.circle, Main.actor, new Point2D(Main.WIDTH / 10 * 9, Main.HEIGHT / 10 * 2), Main.HEIGHT / 3);
        player = new Player(Main.human, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 4), 5, Main.human.getWidth(), Main.human.getHeight());

        btnInventory = new BtnInventory(Main.btnInventory, game, new Point2D(Main.WIDTH / 10 * 9 - 100, Main.HEIGHT / 10 * 9 + 40), Main.btnInventory.getHeight() * 3, Main.btnInventory.getWidth() * 3);

        inventorySc = new ScInventorySc(game, this);

        money = new WorldObj(Main.money_0, new Point2D(Main.WIDTH / 10 * 8 - 50, Main.HEIGHT / 10 * 9 + 50), Main.money_0.getWidth() * 2, Main.money_0.getHeight() * 2);
        cupboard = new WorldObj(Main.cupboard, new Point2D(Main.WIDTH / 10 * 3, Main.HEIGHT / 10 * 4), Main.cupboard.getWidth() * 3, Main.cupboard.getHeight() * 3);
        table = new WorldObj(Main.table, new Point2D(Main.WIDTH / 10 * 7, Main.HEIGHT / 10 * 6), Main.table.getWidth() * 3, Main.table.getHeight() * 3);


    }

    public void gameRender(SpriteBatch batch){

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        money.draw(batch);
        btnInventory.draw(batch);
        joystick.draw(batch);
        cupboard.draw(batch);
        table.draw(batch);

        if (joystick.getDir().getX() == 0 && joystick.getDir().getY() == 0) {
            batch.draw(player.getIdle(), player.position.getX(), player.position.getY());
        } else {
            batch.draw(player.getPlayer(), player.position.getX(), player.position.getY());
        }

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

        cupboard.collides(player, cupboard);
        table.collides(player, table);
    }

    public void touch(float x, float y, boolean isTouch, int pointer) {
        for (int i = 0; i < 5; i++) {
            joystick.update(x, y, isTouch, pointer);
            if (x > btnInventory.getX() && x < btnInventory.getX() + btnInventory.getWidth()
                    && y > btnInventory.getY() && y < btnInventory.getY() + btnInventory.getHeight()) {
                game.setScreen(inventorySc);
            }
        }
    }

    public void gameUpdate(float dt) {
        // устанавливает на player джостик
        player.setDirection(joystick.getDir());
        player.updatePlayer(dt);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameUpdate(delta);
        Main.batch.begin();
        Main.batch.draw(Main.backgroundRoom2, 0, 0, Main.WIDTH, Main.HEIGHT);
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
}
