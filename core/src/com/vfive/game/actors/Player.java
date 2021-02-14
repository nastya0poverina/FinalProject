package com.vfive.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;

public class Player extends Actor {

    private Rectangle bounds;

    public Player(Texture img, Point2D position, float speed, float radius) {
        super(img, position, speed, radius);
        bounds = new Rectangle(position.getX(), position.getY(), radius, radius);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //вычитаем radius т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины человека
        batch.draw(img, position.getX() - radius, position.getY() - radius);
    }

    @Override
    public void update() {
        //ставим проверку что бы обьект не вылетал за экран, а только касался его границ
        if (position.getX() - radius > Main.WIDTH) { // правая ширина
            position.setX(Main.WIDTH - radius);
        }
        if (position.getX() - radius < 0){ // левая ширина
            position.setX(radius);
        }
        if (position.getY() - radius > Main.HEIGHT ){ // верхняя длина
            position.setY(Main.HEIGHT - radius);
        }
        if (position.getY() - radius < 0) { // нижняя длина
            position.setY(radius);
        }
        position.add(direction.getX() * speed, direction.getY() * speed);
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
