package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemInventory extends Actor {
    String name;
    boolean equipped = false;
    Texture img;
    int index;

    public ItemInventory(String name, boolean equipped, Texture img) {
        this.name = name;
        this.equipped = equipped;
        this.img = img;
    }
}
