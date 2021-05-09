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
import com.vfive.game.graphisObj.ItemInventory;
import com.vfive.game.graphisObj.WorldObj;

import java.util.logging.Logger;

public class GameSc implements Screen {

    Main menu;
    Joystick joystick;
    Player player;
    WorldObj box1, box2, glue, paper, money;
    BtnCheck btnCheck;
    Stage stage;
    public static ItemInventory glue1, paper1;
    BtnInventory btnInventory;

    public static boolean box1Islooting = false;
    public static boolean box2Islooting = false;

    private static Logger logger = Logger.getLogger(Game.class.getName());

    public GameSc(Main menu) {
        this.menu = menu;
    }

    @Override
    public void show() {
        loadActor();
    }

    public void loadActor() {
        // привязывает изображения из assets к обьектам
        joystick = new Joystick(Main.circle, Main.actor, new Point2D(Main.WIDTH / 10 * 9, Main.HEIGHT / 10 * 2), Main.HEIGHT / 3);
        player = new Player(Main.human, new Point2D(Main.WIDTH / 6, Main.HEIGHT / 6), 5, Main.human.getWidth(), Main.human.getHeight());

        box1 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10 * 3, Main.HEIGHT / 10 * 6), Main.box.getWidth() * 2, Main.box.getHeight() * 2);
        box2 = new WorldObj(Main.box, new Point2D(Main.WIDTH / 10 * 6, Main.HEIGHT / 10 * 5), Main.box.getWidth() * 2, Main.box.getHeight() * 2);

        btnCheck = new BtnCheck(Main.btnCheck, menu, new Point2D(Main.WIDTH / 10 * 8 + Main.box.getWidth()/2f, Main.HEIGHT / 10 * 4 + 50), 26f * 5, joystick.getSize(),box1,box2,player);
        btnInventory = new BtnInventory(Main.btnInventory, menu, new Point2D(Main.WIDTH / 10 * 9 - 100, Main.HEIGHT / 10 * 9 + 40), Main.btnInventory.getHeight() * 3, Main.btnInventory.getWidth() * 3);

        //paper = new WorldObj(Main.scrap_paper, new Point2D(Main.WIDTH / 10 * 8 - box2.getWidth() + 50, Main.HEIGHT / 10 * 5), Main.scrap_paper.getWidth() / 7, Main.scrap_paper.getHeight() / 7);
        //glue = new WorldObj(Main.glue, new Point2D(Main.WIDTH / 10 * 3 + box1.getWidth() + 50, Main.HEIGHT / 10 * 7));

        glue1 = new ItemInventory("GLUE", false, Main.glue);
        paper1 = new ItemInventory("PAPER", false, Main.scrap_paper);

        money = new WorldObj(Main.money_0, new Point2D(Main.WIDTH / 10 * 8 - 50, Main.HEIGHT / 10 * 9 + 50), Main.money_0.getWidth()  * 2, Main.money_0.getHeight() * 2);

        stage = new Stage();
        stage.addActor(btnCheck);
        stage.addActor(btnInventory);
    }

    public void gameUpdate(float dt) {
        // устанавливает на player джостик
        player.setDirection(joystick.getDir());
        player.updatePlayer(dt);
    }

    public void gameRender(SpriteBatch batch) {
        // отрисовывает всех, обновляется каждую секунду, делает проверки

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        joystick.draw(batch);
        box1.draw(batch);
        box2.draw(batch);
        money.draw(batch);

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

         //ставит процессор на кнопку в радиусе isCheck и отпукает после нажатия на btnCheck
        if (box1.isCheck(player, box1)) {
            if (box1Islooting == false) {
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                glue1.setEquipped(true);
                joystick.returnStick();
            }
            box1.collides(player, box1);
        }

        if (box2.isCheck(player, box2)) {
            if(box2Islooting == false) {
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                paper1.setEquipped(true);
                joystick.returnStick();
            }
            box2.collides(player, box2);
        }

        addItemInInventory(glue1, glue1.getEquipped());
        addItemInInventory(paper1, paper1.getEquipped());

        if (Main.inventory.isEmpty(0))
            logger.info("инвентарь пуст");
    }

    public void addItemInInventory (ItemInventory item, boolean isEquipped){
        if (isEquipped == true) {
            Main.inventory.addItem(item);

        }
    }

    @Override
    public void render(float delta) {
        gameUpdate(delta);
        Main.batch.begin();
        Main.batch.draw(Main.backgroundRoom, 0, 0, Main.WIDTH, Main.HEIGHT);
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