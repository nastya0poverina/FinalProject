package com.vfive.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;

public class Player extends Actor {

    private Rectangle bounds;
    private float widthPlayer, heightPlayer;

    public Player(Texture img, Point2D position, float speed, float widthAct, float heightAct) {
        super(img, position, speed, widthAct, heightAct);
        widthPlayer = widthAct;
        heightPlayer = heightAct;
        bounds = new Rectangle(position.getX(), position.getY(), widthPlayer, heightPlayer);
    }

    @Override
    public void draw(SpriteBatch batch) {
        //вычитаем высоту и ширину т.к. отрисовка начинается с левого нижнего угла, а нам надо с середины человека
        batch.draw(img, position.getX() - widthPlayer, position.getY() - heightPlayer, widthPlayer, heightPlayer);
    }

    @Override
    public void update() {
        //ставим проверку что бы обьект не вылетал за экран, а только касался его границ
        if (position.getX() + widthPlayer > Main.WIDTH) // право
            position.setX(Main.WIDTH - widthPlayer);

        if (position.getX() - widthPlayer < 0) // лево
            position.setX(widthPlayer);

        if (position.getY() + heightPlayer > Main.HEIGHT) // верх
            position.setY(Main.HEIGHT - heightPlayer);

        if (position.getY() - heightPlayer < 0)  // низ
            position.setY(heightPlayer);

        position.add(direction.getX() * speed, direction.getY() * speed);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
