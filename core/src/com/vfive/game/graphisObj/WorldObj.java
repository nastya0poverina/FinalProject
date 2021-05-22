package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;

public class WorldObj {

    private float x, y;
    private float width, height;
    private Texture img;
    public boolean isEmpty;
    public ItemInventory itemInventory;

    public WorldObj(Texture texture, Point2D position) {
        img = texture;
        this.setX(position.getX());
        this.setY(position.getY());
        this.setWidth(texture.getWidth());
        this.setHeight(texture.getHeight());
    }

    public WorldObj(Texture texture, Point2D position, float rectWidth, float rectHeight) {
        img = texture;
        this.setX(position.getX());
        this.setY(position.getY());
        this.setWidth(rectWidth);
        this.setHeight(rectHeight);
    }

    public void draw(SpriteBatch batch) {
        //вычитаем radius т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины обьекта
        batch.draw(img, this.getX() - this.getWidth() / 2, this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }

    public void collides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player
        if (player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 + 5 &&          // правая стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setX(worldObj.getX() + worldObj.getHeight() / 2 + 5);

        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() &&              //левая стенка ящика
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setX(worldObj.getX() - worldObj.getWidth());

        if (player.position.getY() + player.heightPlayer / 2 > worldObj.getY() - worldObj.getHeight() / 2 &&    // нижняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth()  &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 - 5)
            player.position.setY(worldObj.getY() - worldObj.getHeight() / 2 - player.heightPlayer / 2);

        if (player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 &&                   //верхняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setY(worldObj.getY() + worldObj.getHeight() / 2);
    }

    public void cup_coll(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player
        if (player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 + 5 &&          // правая стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 + 5 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2){
            player.position.setX(worldObj.getX() + worldObj.getWidth() / 2);
            worldObj.setPosition(worldObj.getX() - 1, worldObj.getY());
            if (worldObj.getX() - worldObj.getWidth() < 0) {
                worldObj.setX( worldObj.getWidth() );
            }
        }

        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 - player.getWidthPlayer() / 3 &&              //левая стенка ящика
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2){
            player.position.setX(worldObj.getX() - worldObj.getWidth() / 2 - player.getWidthPlayer() / 3);
            worldObj.setPosition(worldObj.getX() + 1, worldObj.getY());
            if (worldObj.getX() > Main.WIDTH - worldObj.getWidth() * 4){
                worldObj.setX( Main.WIDTH - worldObj.getWidth() * 4);
            }
        }

        if (player.position.getY() + player.heightPlayer / 2 > worldObj.getY() - worldObj.getHeight() / 2 &&    // нижняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 + 5  &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 - 5)
            player.position.setY(worldObj.getY() - worldObj.getHeight() / 2 - player.heightPlayer / 2);

        if (player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 &&                   //верхняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 + 5 &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setY(worldObj.getY() + worldObj.getHeight() / 2);
    }

    public boolean isCheck(Player player, WorldObj worldObj) {
        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() - 25 &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 + 25 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() - 25 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 + 25)
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean getEmpty() {
        return isEmpty;
    }

    public Texture getTexture() {
        return img;
    }

    public void setTexture(Texture img) {
        this.img = img;
    }

    public Point2D getPos() {
        Point2D point = new Point2D(this.getX(), this.getY());
        return point;
    }

    public void setPosition(float x, float y){
        this.setX(x);
        this.setY(y);
    }
}