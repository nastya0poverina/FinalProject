package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.InventorySc;
import com.vfive.game.screens.ScInventorySc;

public class ItemInventory extends Actor {

    public boolean equipped, inInterBox;
    private Texture img;
    private InventorySc inventorySc;


    public ItemInventory(boolean equipped, Texture img, WorldObj box, InventorySc inventorySc) {
        this.inventorySc = inventorySc;
        this.equipped = equipped;
        this.img = img;
        this.setWidth(box.getWidth() - box.getWidth() / 10f * 2);
        this.setHeight(box.getHeight() - box.getHeight() / 10f * 2);
        this.setX(box.getX() - box.getWidth() / 2 + box.getWidth() / 10f);
        this.setY(box.getY() - box.getHeight() / 2 + box.getHeight() / 10f);
        addListener(new ItemListener(this, box));
    }
    public ItemInventory( boolean equipped, Texture img, WorldObj box) {
        this.equipped = equipped;
        this.img = img;
        this.setWidth(box.getWidth() - box.getWidth() / 10f * 2);
        this.setHeight(box.getHeight() - box.getHeight() / 10f * 2);
        this.setX(box.getX() - box.getWidth() / 2 + box.getWidth() / 10f);
        this.setY(box.getY() - box.getHeight() / 2 + box.getHeight() / 10f);
        addListener(new ItemSecondListener(this, box));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void drawPicture(Batch batch){
        batch.draw(img, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public boolean getEquipped() {
        return equipped;
    }

    public void setPos(Point2D pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

    public boolean isCheckItem(Player player, ItemInventory itemInventory) {
        if (player.position.getX() > itemInventory.getX() - itemInventory.getWidth() - 50 &&
                player.position.getX() < itemInventory.getX() + itemInventory.getWidth() / 2 + 150 &&
                player.position.getY() > itemInventory.getY() - itemInventory.getHeight() - 50 &&
                player.position.getY() < itemInventory.getY() + itemInventory.getHeight() / 2 + 50)
            return true;
        return false;
    }

    public class ItemListener extends InputListener {
        ItemInventory item;
        WorldObj box;

        public ItemListener(ItemInventory item, WorldObj box) {
            this.item = item;
            this.box = box;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            //перемещает обьект в 1 Inter box
            if (!item.inInterBox && inventorySc.getBoxInterFirst().getEmpty()) {
                Point2D point2D = new Point2D(inventorySc.getBoxInterFirst().getX() - inventorySc.getBoxInterFirst().getWidth() / 2 + inventorySc.getBoxInterFirst().getWidth() / 10f,
                        inventorySc.getBoxInterFirst().getY() - inventorySc.getBoxInterFirst().getHeight() / 2 + inventorySc.getBoxInterFirst().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterFirst().setEmpty(false);
                item.inInterBox = true;
                box.setEmpty(true);
                inventorySc.getBoxInterFirst().itemInventory = item;
            }

            //перемещает обьект в 2 Inter box
            if (!item.inInterBox && inventorySc.getBoxInterSecond().getEmpty()) {
                Point2D point2D = new Point2D(inventorySc.getBoxInterSecond().getX() - inventorySc.getBoxInterSecond().getWidth() / 2 + inventorySc.getBoxInterSecond().getWidth() / 10f,
                        inventorySc.getBoxInterSecond().getY() - inventorySc.getBoxInterSecond().getHeight() / 2 + inventorySc.getBoxInterSecond().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterSecond().setEmpty(false);
                item.inInterBox = true;
                box.setEmpty(true);
            }

            //обрабатывает коробку с результатом
            if (item.equipped && !inventorySc.getBoxInterResult().getEmpty()) {
                Point2D point2D = new Point2D(inventorySc.getFreeBox().getX() - inventorySc.getFreeBox().getWidth() / 2 + inventorySc.getFreeBox().getWidth() / 10f,
                        inventorySc.getFreeBox().getY() - inventorySc.getFreeBox().getHeight() / 2 + inventorySc.getFreeBox().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterResult().setEmpty(true);
                inventorySc.getFreeBox().setEmpty(false);
                item.inInterBox = false;
                inventorySc.getBoxInterFirst().setEmpty(true);
                inventorySc.getBoxInterSecond().setEmpty(true);
            }

            //обрабатывает нажатие на 1 inter box
            if (!inventorySc.getBoxInterFirst().getEmpty() && !item.equipped && item.inInterBox) {
                item.setEquipped(true);
                Point2D point2D = new Point2D(inventorySc.getFreeBox().getX() - inventorySc.getFreeBox().getWidth() / 2 + inventorySc.getFreeBox().getWidth() / 10f,
                        inventorySc.getFreeBox().getY() - inventorySc.getFreeBox().getHeight() / 2 + inventorySc.getFreeBox().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterFirst().setEmpty(true);
                item.inInterBox = false;
                inventorySc.getFreeBox().setEmpty(false);
            }

            //обрабатывает нажатие на 2 inter box
            if (!inventorySc.getBoxInterSecond().getEmpty() && !item.equipped && item.inInterBox) {
                item.setEquipped(true);
                Point2D point2D = new Point2D(inventorySc.getFreeBox().getX() - inventorySc.getFreeBox().getWidth() / 2 + inventorySc.getFreeBox().getWidth() / 10f,
                        inventorySc.getFreeBox().getY() - inventorySc.getFreeBox().getHeight() / 2 + inventorySc.getFreeBox().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterSecond().setEmpty(true);
                item.inInterBox = false;
                inventorySc.getFreeBox().setEmpty(false);
            }

            //ставит картину на стену
            if (item == inventorySc.picture && item.inInterBox) {
                item.setEquipped(true);
                GameSc.isPictureOnWall = true;
            }
            return true;
        }
    }

    public class ItemSecondListener extends InputListener {
        ItemInventory item;
        WorldObj box;

        public ItemSecondListener(ItemInventory item, WorldObj box) {
            this.item = item;
            this.box = box;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Main.logger.info("а нажатий то нету");
            return true;
        }
    }
}
