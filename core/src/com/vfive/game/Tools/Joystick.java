package com.vfive.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Joystick {

    Texture circleImg, stickImg;
    Circle circleBound, stickBound, circleBound1;
    float circleRadius, stickRadius;
    Point2D direction;
    private int pointer = -1;
    float size;


    public Joystick(Texture cImg, Texture sImg, Point2D point2D, float size) {
        circleImg = cImg;
        stickImg = sImg;
        circleRadius = size / 2;
        stickRadius = circleRadius / 2;
        circleBound = new Circle(point2D, circleRadius);
        circleBound1 = new Circle(point2D, circleRadius * 1.5f);
        stickBound = new Circle(point2D, stickRadius);
        direction = new Point2D(0, 0);
        this.size = size;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(circleImg,
                circleBound.pos.getX() - circleRadius,
                circleBound.pos.getY() - circleRadius,
                circleRadius * 2,
                circleRadius * 2);

        batch.draw(stickImg,
                stickBound.pos.getX() - stickRadius,
                stickBound.pos.getY() - stickRadius,
                stickRadius * 2,
                stickRadius * 2);
    }


    public void update(float x, float y, boolean isTouch, int pointer) {
        // расположение stickImg в circleImg
        Point2D touch = new Point2D(x, y);
        // когда мы не касались джостика
        if (circleBound1.isContains(touch) && isTouch && this.pointer == -1) {
            this.pointer = pointer;
        }
        // когда мы касаемся джостика
        if (circleBound1.overlaps(stickBound) && isTouch && this.pointer == this.pointer) {
            work(new Point2D(x, y));
        }
        // stickImg выходит за границы circleImg
        if ((!isTouch && pointer == this.pointer)  || (isTouch && this.pointer == this.pointer && !circleBound1.isContains(touch))) {
            returnStick();
        }
    }

    public void work(Point2D point2D) {
        // описывает движение stickImg
        stickBound.pos.setPoint(point2D);
        float dx = circleBound1.pos.getX() - stickBound.pos.getX();
        float dy = circleBound1.pos.getY() - stickBound.pos.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        direction.setPoint(-(dx / distance), -(dy / distance));
    }

    public void returnStick() {
        // вызывается если Stick вышел за пределы окружности
        stickBound.pos.setPoint(circleBound1.pos);
        direction.setPoint(0, 0);
        pointer = -1;
    }

    public Point2D getDir() {
        return direction;
    }

    public float getSize() {
        return size;
    }
}
