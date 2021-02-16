package com.vfive.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.vfive.game.Tools.Circle;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.graphisObj.GraphicsObj;

abstract public class Actor extends GraphicsObj {

    public Point2D position;
    public float speed, width, height;
    public Point2D direction;

    public Actor(Texture img, Point2D position, float speed, float widthAct, float heightAct) {
        super(img);
        this.position = new Point2D(position);
        this.speed = speed;
        this.width = widthAct;
        this.height = heightAct;
        direction = new Point2D(0, 0);
    }

    public void setDirection(Point2D dir) {
        direction = dir;
    }
}
