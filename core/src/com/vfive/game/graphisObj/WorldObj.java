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

public class WorldObj extends Actor {

    private Rectangle boundsObj;
    //private float x, y;
    //private float width, height;
    private Texture img;
    public boolean isEmpty;
    public ItemInventory itemInventory;

    public WorldObj(Texture texture, Point2D position) {
        img = texture;
        this.setX(position.getX());
        this.setY(position.getY());
        this.setWidth(texture.getWidth());
        this.setHeight(texture.getHeight());
        boundsObj = new Rectangle(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }

    public WorldObj(Texture texture, Point2D position, float rectWidth, float rectHeight) {
        img = texture;
        this.setX(position.getX());
        this.setY(position.getY());
        this.setWidth(rectWidth);
        this.setHeight(rectHeight);
        boundsObj = new Rectangle(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }

    public void draw(SpriteBatch batch) {
        //вычитаем radius т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины обьекта
        batch.draw(img, this.getX() - this.getWidth() / 2, this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }


    public boolean isCollides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player

        if (player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&          // правая стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2) {
            player.position.setX(worldObj.getX() + worldObj.getHeight() / 2);
            return true;
        }

        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() &&              //левая стенка ящика
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2) {
            player.position.setX(worldObj.getX() - worldObj.getWidth());
            return true;
        }

        if (player.position.getY() + player.heightPlayer / 2 > worldObj.getY() - worldObj.getHeight() / 2 &&    // нижняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5) {
            player.position.setY(worldObj.getY() - worldObj.getHeight() / 2 - player.heightPlayer / 2);
            return true;
        }

        if (player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 &&                   //верхняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2) {
            player.position.setY(worldObj.getY() + worldObj.getHeight() / 2);
            return true;
        }
        return false;
    }

    public void collides(Player player, WorldObj worldObj) {
        //проверяет столкновения обьекта с player
        if (player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&          // правая стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setX(worldObj.getX() + worldObj.getHeight() / 2);

        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() &&              //левая стенка ящика
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setX(worldObj.getX() - worldObj.getWidth());

        if (player.position.getY() + player.heightPlayer / 2 > worldObj.getY() - worldObj.getHeight() / 2 &&    // нижняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 - 5)
            player.position.setY(worldObj.getY() - worldObj.getHeight() / 2 - player.heightPlayer / 2);

        if (player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 &&                   //верхняя стенка ящика
                player.position.getX() > worldObj.getX() - worldObj.getWidth() &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() / 2)
            player.position.setY(worldObj.getY() + worldObj.getHeight() / 2);
    }

    public boolean isCheck(Player player, WorldObj worldObj) {
        if (player.position.getX() > worldObj.getX() - worldObj.getWidth() - 50 &&
                player.position.getX() < worldObj.getX() + worldObj.getWidth() / 2 + 50 &&
                player.position.getY() > worldObj.getY() - worldObj.getHeight() - 50 &&
                player.position.getY() < worldObj.getY() + worldObj.getHeight() / 2 + 50)
            return true;
        return false;
    }

/*    public boolean isTouch(WorldObj worldObj) {
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
    }*/

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    //true - коробка пустая ; false - в в коробке что то есть

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

    public static class BoxListener extends InputListener {

        WorldObj box;
        ItemInventory item;

        public BoxListener(WorldObj box, ItemInventory item) {
            Main.logger.info("листенер создался");
            this.box = box;
            this.item = item;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Main.logger.info("мы в дауне");
            if (box.getEmpty() == false && item.equipped == false && item.inInterBox == true) {
                Main.logger.info("все круто, нажатие в 1 коробке сложения есть");
                item.setEquipped(true);
                Point2D point2D = new Point2D(box.getX() - box.getWidth() / 2 + box.getWidth() / 10f,
                        box.getY() - box.getHeight() / 2 + box.getHeight() / 10f);
                item.setPos(point2D);
                box.setEmpty(true);
                item.inInterBox = false;
                box.setEmpty(false);
            }
            return true;
        }
    }
}