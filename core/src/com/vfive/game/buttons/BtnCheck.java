package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.graphisObj.ItemInventory;
import com.vfive.game.graphisObj.WorldObj;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.InventorySc;
import com.vfive.game.screens.ScInventorySc;
import com.vfive.game.screens.SecondFloorSc;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.rmi.runtime.Log;


public class BtnCheck extends Actor {
    private Texture img;
    private WorldObj box1, box2, box3, box4, obj;
    private ItemInventory item;
    private Player player;
    private SecondFloorSc floorSc;
    private ScInventorySc inventorySc;

    public BtnCheck(Texture img, Main game, Point2D point2D, float height, float width, WorldObj box1, WorldObj box2, WorldObj box3, WorldObj box4, ItemInventory item, Player player) {
        addListener(new BtnCheckListener(game));
        this.img = img;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX());
        setY(point2D.getY());
        this.box1 = box1;
        this.box2 = box2;
        this.box3 = box3;
        this.box4 = box4;
        this.item = item;
        this.player = player;
    }

    public BtnCheck(Texture img, Main game, Point2D point2D, float height, float width, WorldObj obj, Player player, SecondFloorSc screen, ScInventorySc inventory) {
        addListener(new BtnCheckScListener(game));
        this.img = img;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX());
        setY(point2D.getY());
        floorSc = screen;
        this.obj = obj;
        this.player = player;
        this.inventorySc = inventory;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    private class BtnCheckListener extends InputListener {

        private Main game;
        private boolean isTouch = false;

        public BtnCheckListener(Main game) {
            this.game = game;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            // проверяет осмотрели ли мы коробки, нужно что бы переключить InputProcessor на джостике
            isTouch = true;

            if (box1.isCheck(player, box1)) GameSc.box1Islooting = true;
            if (box2.isCheck(player, box2)) GameSc.box2Islooting = true;
            if (box3.isCheck(player, box3)) GameSc.box3Islooting = true;
            if (box4.isCheck(player, box4)) GameSc.box4Islooting = true;
            if (item.isCheckItem(player, item )) {
                GameSc.pictureIslooting = true;
            }
            return true;
        }
    }

    public class BtnCheckScListener extends InputListener {

        private Main game;

        public BtnCheckScListener(Main game) {
            this.game = game;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (floorSc.hanger.isCheck(player, floorSc.hanger)) {
                SecondFloorSc.hangIsCheck = true;
                Main.moneySum = Main.moneySum + 1;
            }
            if (floorSc.key.isCheck(player, floorSc.key)) {
                SecondFloorSc.keyIsCheck = true;
                inventorySc.key.setEquipped(true);
            }
            if (floorSc.safe.isCheck(player, floorSc.safe)) {
                SecondFloorSc.safeIsCheck = true;
                floorSc.safe.setTexture(Main.safeOpen);
                inventorySc.key.setEquipped(false);
                inventorySc.mathes.setEquipped(true);
                Main.moneySum = Main.moneySum + 2;
            }
            if (floorSc.cupboard.isCheck(player, floorSc.cupboard)) {
                SecondFloorSc.cupIsCheck = true;
                inventorySc.hammer.setEquipped(true);
            }
            if (floorSc.table.isCheck(player, floorSc.table) && inventorySc.hammer.getEquipped()) {
                inventorySc.hammer.setEquipped(false);
                SecondFloorSc.laptopCrushIsCheck = true;
                Main.moneySum = Main.moneySum + 1;
            }
            if (floorSc.table.isCheck(player, floorSc.table) && inventorySc.mathes.getEquipped()) {
                inventorySc.mathes.setEquipped(false);
                SecondFloorSc.candleMeltIsCheck = true;
                Main.moneySum = Main.moneySum + 1;
            }
            return true;
        }
    }
}

