package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.transform.Templates;

public abstract class GraphicsObj {

    public Texture img;

    public GraphicsObj(Texture img){
        this.img = img;
    }

    public abstract void update();
}
