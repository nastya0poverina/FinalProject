package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;

public class WorldObj {

    private Rectangle boundsObj;
    private float x, y;
    private float width, height;
    private Texture img;

    public WorldObj(Texture texture, Point2D position) {
        img = texture;
        x = position.getX();
        y = position.getY();
        width = texture.getWidth();
        height = texture.getHeight();
        boundsObj = new Rectangle(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }

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

    public boolean isCollides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player

        if (player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&          // правая стенка ящика
                player.position.getX() > worldObj.x - worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.y + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.y - worldObj.getHeight() / 2 ){
            player.position.setX(worldObj.x + worldObj.getHeight() / 2);
            return true;
        }

        if(player.position.getX() > worldObj.x - worldObj.getWidth() &&              //левая стенка ящика
                player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.y + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.y - worldObj.getHeight() / 2){
            player.position.setX(worldObj.x - worldObj.getWidth());
            return true;
        }

        if (player.position.getY() + player.heightPlayer / 2 > worldObj.y - worldObj.getHeight() / 2 &&    // нижняя стенка ящика
                player.position.getX() > worldObj.x - worldObj.getWidth() &&
                player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.y + worldObj.getHeight() / 2 - 5){
            player.position.setY(worldObj.y - worldObj.getHeight() / 2 - player.heightPlayer / 2);
            return true;
        }

        if (player.position.getY() < worldObj.y + worldObj.height / 2 &&                   //верхняя стенка ящика
                player.position.getX() > worldObj.x - worldObj.getWidth() &&
                player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&
                player.position.getY() > worldObj.y - worldObj.getHeight() / 2){
            player.position.setY(worldObj.y + worldObj.height / 2);
            return true;
        }
        return false;
    }

    public void collides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player
        if (player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&          // правая стенка ящика
                player.position.getX() > worldObj.x - worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.y + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.y - worldObj.getHeight() / 2 )
            player.position.setX(worldObj.x + worldObj.getHeight() / 2);

        if(player.position.getX() > worldObj.x - worldObj.getWidth() &&              //левая стенка ящика
                player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.y + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.y - worldObj.getHeight() / 2)
            player.position.setX(worldObj.x - worldObj.getWidth());

        if (player.position.getY() + player.heightPlayer / 2 > worldObj.y - worldObj.getHeight() / 2 &&    // нижняя стенка ящика
                player.position.getX() > worldObj.x - worldObj.getWidth() &&
                player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.y + worldObj.getHeight() / 2 - 5)
            player.position.setY(worldObj.y - worldObj.getHeight() / 2 - player.heightPlayer / 2);

        if (player.position.getY() < worldObj.y + worldObj.height / 2 &&                   //верхняя стенка ящика
                player.position.getX() > worldObj.x - worldObj.getWidth() &&
                player.position.getX() < worldObj.x + worldObj.getWidth() / 2 &&
                player.position.getY() > worldObj.y - worldObj.getHeight() / 2)
            player.position.setY(worldObj.y + worldObj.height / 2);
    }

    public boolean isCheck( Player player, WorldObj worldObj ){
        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() - 50 &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 + 50 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() - 50 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 + 50)
            return true;
        return false;
    }

    public boolean isTouch(WorldObj worldObj){
        return false;
    }

    public Rectangle getBoundsObj() {
        return boundsObj;
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