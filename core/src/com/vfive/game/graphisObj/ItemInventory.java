package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Tools.Point2D;

public class ItemInventory extends Actor {
    String name;
    boolean equipped;
    Texture img;
    Point2D pos;

    public ItemInventory(String name, boolean equipped, Texture img, WorldObj box) {
        this.name = name;
        this.equipped = equipped;
        this.img = img;
        this.setWidth(box.getWidth() - box.getWidth() / 10f * 2);
        this.setHeight(box.getHeight() - box.getHeight() / 10f * 2);
        this.setX(box.getX() - box.getWidth() / 2 + box.getWidth() / 10f);
        this.setY(box.getY() - box.getHeight() / 2 + box.getHeight() / 10f );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public boolean getEquipped() {
        return equipped;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

    public class ItemListener extends InputListener {
        ItemInventory item;
        WorldObj box;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


            return true;
        }
    }
}
