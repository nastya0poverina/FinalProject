package com.vfive.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vfive.game.Main;
import com.vfive.game.Tools.Animation;
import com.vfive.game.Tools.Point2D;

public class Player extends Actor {

    public Point2D position;
    private Rectangle bounds;
    public float widthPlayer, heightPlayer;
    private float speed;
    private Animation animation;
    private Point2D direction;
    private Texture idle;


    public Player(Texture img, Point2D pos, float speedPlayer, float widthAct, float heightAct) {
        speed = speedPlayer;
        position = pos;
        widthPlayer = widthAct;
        heightPlayer = heightAct;
        animation = new Animation(new TextureRegion(img), 3, 0.5f);
        bounds = new Rectangle(position.getX(), position.getY(), img.getWidth() / 3f, img.getHeight());
        direction = new Point2D(0.0F, 0.0F);
        idle = Main.player;
    }

    public void updatePlayer(float dt) {
        animation.update(dt);

        //ставим проверку что бы обьект не вылетал за экран, а только касался его границ
        if (position.getX() + widthPlayer > Main.WIDTH) // право
            position.setX(Main.WIDTH - widthPlayer);

        if (position.getX() - widthPlayer / 2 < 0) // лево
            position.setX(widthPlayer / 2);

        if (position.getY() + heightPlayer * 3 > Main.HEIGHT) // верх
            position.setY(Main.HEIGHT - heightPlayer * 3);

        if (position.getY() - heightPlayer < 0)  // низ
            position.setY(heightPlayer);

        position.add(direction.getX() * speed, direction.getY() * speed);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Texture getIdle() {
        return idle;
    }

    public TextureRegion getPlayer() {
        return animation.getFrame();
    }

    public void setDirection(Point2D dir) {
        this.direction = dir;
    }
}
