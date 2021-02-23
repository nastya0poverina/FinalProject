package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vfive.game.Main;
import com.vfive.game.Tools.Circle;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.graphisObj.GraphicsObj;

public class WorldObj extends GraphicsObj {

    private Rectangle boundsObj;
    private float x, y;
    private float width, height;

    public WorldObj(Texture img, Point2D position, float rectWidth, float rectHeight) {
        super(img);
        x = position.getX();
        y = position.getY();
        width = rectWidth;
        height = rectHeight;
        boundsObj = new Rectangle(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }

    @Override
    public void draw(SpriteBatch batch) {
        //вычитаем radius т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины обьекта
        batch.draw(img, x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void update() {
    }

    public boolean collides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player
        if ((player.position.getX() < worldObj.x + worldObj.width)
                && (worldObj.x < player.position.getX() + player.width)
                && (player.position.getY() < worldObj.y + worldObj.height)
                && (worldObj.y < player.position.getY() + player.height))
            return true;
        return false;
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
