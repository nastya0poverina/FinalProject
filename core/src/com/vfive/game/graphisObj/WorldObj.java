package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;

public class WorldObj {

    private Rectangle boundsObj;
    private float x, y;
    private float width, height;
    private Texture img;

    public WorldObj(Texture texture, Point2D position, float rectWidth, float rectHeight) {
        img = texture;
        x = position.getX();
        y = position.getY();
        width = rectWidth;
        height = rectHeight;
        boundsObj = new Rectangle(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }

    public void draw(SpriteBatch batch) {
        //вычитаем radius т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины обьекта
        batch.draw(img, x - width / 2, y - height / 2, width, height);
    }

    public boolean collides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player
        if ((player.position.getX() < worldObj.x + worldObj.width)
                && (worldObj.x < player.position.getX() + player.widthPlayer / 3)
                && (player.position.getY() < worldObj.y + worldObj.height)
                && (worldObj.y < player.position.getY() + player.heightPlayer))
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