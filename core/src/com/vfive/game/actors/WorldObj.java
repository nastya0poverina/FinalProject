package com.vfive.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vfive.game.Main;
import com.vfive.game.Tools.Circle;
import com.vfive.game.Tools.Point2D;

public class WorldObj extends Actor {

    private Rectangle boundsObj;
    private float x, y;
    private float width, height;

    public WorldObj(Texture img, Point2D position, float rectWidth, float rectHeight) {
        super(img, position, rectWidth, rectHeight);
        this.x = position.getX();
        this.y = position.getY();
        this.width = rectWidth;
        this.height = rectHeight;
        boundsObj = new Rectangle(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }

    @Override
    public void draw(SpriteBatch batch) {
        //вычитаем radius т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины обьекта
        batch.draw(img, position.getX() - radius, position.getY() - radius);
    }

    @Override
    public void update() {
    }

    public boolean collides(Rectangle player){
        //проверяет столкновения обьекта с player
        return player.overlaps(boundsObj);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
