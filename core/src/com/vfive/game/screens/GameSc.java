package com.vfive.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vfive.game.Main;
import com.vfive.game.Tools.Joystick;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.buttons.BtnCheck;
import com.vfive.game.buttons.BtnInventory;
import com.vfive.game.buttons.BtnNextFloor;
import com.vfive.game.graphisObj.ItemInventory;
import com.vfive.game.graphisObj.WorldObj;

import java.util.logging.Logger;

public class GameSc implements Screen {

    private Main menu;
    private Joystick joystick;
    private Player player;
    private WorldObj box1, box2, box3, box4, money;
    private BtnCheck btnCheck;
    private BtnNextFloor btnNext;
    private Stage stage;
    public static ItemInventory glue1, paper1, frame1, glass1;
    private BtnInventory btnInventory;
    private InventorySc inventorySc;

    public static boolean box1Islooting = false;
    public static boolean box2Islooting = false;
    public static boolean box3Islooting = false;
    public static boolean box4Islooting = false;
    public static boolean pictureIslooting = false;
    public static boolean isPictureOnWall = false;

    public GameSc(Main menu) {
        this.menu = menu;
        loadActor();
    }

    @Override
    public void show() {

    }

    public void loadActor() {
        // привязывает изображения из assets к обьектам

        inventorySc = new InventorySc(menu, this);

        joystick = new Joystick(Main.circle, Main.actor, new Point2D(Main.WIDTH / 10f * 9, Main.HEIGHT / 10f * 2), Main.HEIGHT / 3f);
        player = new Player(Main.human, new Point2D(Main.WIDTH / 2f, Main.HEIGHT / 4f), 5, Main.human.getWidth(), Main.human.getHeight());

        box1 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10f * 3, Main.HEIGHT / 10f * 6), Main.box.getWidth() * 2, Main.box.getHeight() * 2);
        box2 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10f * 6, Main.HEIGHT / 10f * 5), Main.box.getWidth() * 2, Main.box.getHeight() * 2);
        box3 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10f * 2, Main.HEIGHT / 10f * 3), Main.box.getWidth() * 2, Main.box.getHeight() * 2);
        box4 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10f * 8, Main.HEIGHT / 10f * 7), Main.box.getWidth() * 2, Main.box.getHeight() * 2);

        btnCheck = new BtnCheck(Main.btnCheck, menu, new Point2D(Main.WIDTH / 10f * 8 + Main.box.getWidth() / 2f, Main.HEIGHT / 10f * 4 + 50), 26f * 5, joystick.getSize(), box1, box2, box3, box4, inventorySc.picture, player);
        btnInventory = new BtnInventory(Main.btnInventory, menu, new Point2D(Main.WIDTH / 10f * 9 - 100, Main.HEIGHT / 10f * 9 + 40), Main.btnInventory.getHeight() * 3, Main.btnInventory.getWidth() * 3);
        btnNext = new BtnNextFloor(Main.btnNext, menu, new Point2D(Main.WIDTH / 2f - Main.btnNext.getWidth()  * 2, Main.HEIGHT / 10f * 9), 26f * 5, joystick.getSize(), player);

        glue1 = new ItemInventory( false, Main.glue, inventorySc.getBoxCentre(), inventorySc);
        inventorySc.getBoxCentre().setEmpty(false);
        paper1 = new ItemInventory( false, Main.scrap_paper, inventorySc.getBoxLeft1(), inventorySc);
        inventorySc.getBoxLeft1().setEmpty(false);
        frame1 = new ItemInventory( false, Main.frame, inventorySc.getBoxRight1(), inventorySc);
        inventorySc.getBoxRight1().setEmpty(false);
        glass1 = new ItemInventory( false, Main.glass, inventorySc.getBoxLeft2(), inventorySc);
        inventorySc.getBoxLeft2().setEmpty(false);

        money = new WorldObj(Main.money_0, new Point2D(Main.WIDTH / 10f * 8 - 50, Main.HEIGHT / 10f * 9 + 70), Main.money_0.getWidth() * 3, Main.money_0.getHeight() * 3);

        stage = new Stage();
        stage.addActor(btnCheck);
        //stage.addActor(btnInventory);
    }

    public void gameUpdate(float dt) {
        // устанавливает на player джостик
        player.setDirection(joystick.getDir());
        player.updatePlayer(dt);
    }

    public void gameRender(SpriteBatch batch) {
        // отрисовывает всех, обновляется каждую секунду, делает проверки

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        box1.draw(batch);
        box2.draw(batch);
        box3.draw(batch);
        box4.draw(batch);
        money.draw(batch);

        //отрисовка анимации спокойствияБ относительно положению стика в джостике
        if (joystick.getDir().getX() == 0 && joystick.getDir().getY() == 0) {
            batch.draw(player.getIdle(), player.position.getX(), player.position.getY());
        } else {
            batch.draw(player.getPlayer(), player.position.getX(), player.position.getY());
        }

        joystick.draw(batch);

        // ставит процессор джойстика, если мы не рядом с коробками
        // if(!box1.isCheck(player,box1) && !box2.isCheck(player, box2) )
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

        boxActions(batch);

        addItemInInventory(glue1, glue1.getEquipped());
        addItemInInventory(paper1, paper1.getEquipped());
        addItemInInventory(frame1, frame1.getEquipped());
        addItemInInventory(glass1, glass1.getEquipped());

        btnInventory.draw(batch);
    }

    public void boxActions(SpriteBatch batch) {
        //ставит процессор на кнопку в радиусе isCheck и отпукает после нажатия на btnCheck
        if (box1.isCheck(player, box1)) {
            if (!box1Islooting) {
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                glue1.setEquipped(true);
                joystick.returnStick();
            }
            box1.collides(player, box1);
        }

        if (box2.isCheck(player, box2)) {
            if (!box2Islooting) {
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                paper1.setEquipped(true);
                joystick.returnStick();
            }
            box2.collides(player, box2);
        }

        if (box3.isCheck(player, box3)) {
            if (!box3Islooting) {
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                frame1.setEquipped(true);
                joystick.returnStick();
            }
            box3.collides(player, box3);
        }

        if (box4.isCheck(player, box4)) {
            if (!box4Islooting) {
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                glass1.setEquipped(true);
                joystick.returnStick();
            }
            box4.collides(player, box4);
        }

        if (isPictureOnWall) {
            inventorySc.picture.setPos(new Point2D(Main.WIDTH / 100f, Main.HEIGHT / 5f * 3));
            inventorySc.picture.drawPicture(batch);
            if (inventorySc.picture.isCheckItem(player, inventorySc.picture)) {
                Main.logger.info("мы можем подойти");
                if (pictureIslooting == false) {
                    Main.logger.info("чет не нажимается");
                    stage.draw();
                    Gdx.input.setInputProcessor(stage);
                    joystick.returnStick();
                    money.setTexture(Main.money_1);
                }
            }
            if (nextFloor() && money.getTexture() == Main.money_1) {
                btnNext.btnDraw(batch);
            }
        }
    }

    public boolean nextFloor() {
        if (player.position.getY() + player.heightPlayer * 4 > Main.HEIGHT && player.position.getX() > Main.WIDTH / 2f - 200 && player.position.getX() < Main.WIDTH / 2f+ 100) {
            return true;
        }
        return false;
    }

    public void addItemInInventory(ItemInventory item, boolean isEquipped) {
        if (isEquipped) {
            Main.inventory.addItem(item);
        }
    }

    @Override
    public void render(float delta) {
        gameUpdate(delta);
        Main.batch.begin();
        Main.batch.draw(Main.backgroundRoom, 0, 0, Main.WIDTH, Main.HEIGHT);
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
            if (x > btnInventory.getX() && x < btnInventory.getX() + btnInventory.getWidth()
                    && y > btnInventory.getY() && y < btnInventory.getY() + btnInventory.getHeight()) {
                menu.setScreen(inventorySc);
            }
            if (x > btnNext.getX() && x < btnNext.getX() + btnNext.getWidth()
                    && y > btnNext.getY() && y < btnNext.getY() + btnNext.getHeight() && nextFloor() && money.getTexture() == Main.money_1) {
                menu.setScreen(new SecondFloorSc(menu));
            }
        }
    }
}