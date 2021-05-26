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
        direction = new Point2D(0.0F, 0.0F);
        idle = Main.player;
    }

    public void updatePlayer(float dt) {
        animation.update(dt);

        //ставим проверку что бы обьект не вылетал за экран, а только касался его границ
        if (position.getX() + Main.WIDTH  / 13.25f + widthPlayer / 4 > Main.WIDTH) // право
            position.setX(Main.WIDTH - Main.WIDTH  / 13.25f - widthPlayer / 4);

        if (position.getX() - Main.WIDTH  / 13.25f < 0) // лево
            position.setX(Main.WIDTH  / 13.25f);

        if (position.getY() + Main.HEIGHT / 4.7f + widthPlayer / 4   > Main.HEIGHT) // верх
            position.setY(Main.HEIGHT - Main.HEIGHT / 4.7f - widthPlayer  / 4 );

        if (position.getY() - Main.HEIGHT / 13f < 0)  // низ
            position.setY(Main.HEIGHT / 13f);

        position.add(direction.getX() * speed, direction.getY() * speed);
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

    public float getWidthPlayer() {
        return widthPlayer;
    }
}
