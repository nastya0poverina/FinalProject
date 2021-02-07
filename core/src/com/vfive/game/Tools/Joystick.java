package com.vfive.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Joystick {

    Texture circleImg, stickImg;
    Circle circleBound, stickBound;
    float circleRadius, stickRadius;
    Point2D direction;
    private int pointer = -1;


    public Joystick(Texture cImg, Texture sImg, Point2D point2D, float size) {
        circleImg = cImg;
        stickImg = sImg;
        circleRadius = size / 2;
        stickRadius = circleRadius / 2;
        circleBound = new Circle(point2D, circleRadius);
        stickBound = new Circle(point2D, stickRadius);
        direction = new Point2D(0, 0);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(circleImg,
                circleBound.pos.getX() - circleRadius,
                circleBound.pos.getY() - circleRadius,
                circleRadius * 2,
                circleRadius * 2 );

        batch.draw(stickImg,
                stickBound.pos.getX() - stickRadius,
                stickBound.pos.getY() - stickRadius,
                stickRadius * 2,
                stickRadius * 2);
    }

    public void update(float x, float y , boolean isTouch, int pointer){
        // расположение stickImg в circleImg
        Point2D touch = new Point2D(x, y);
        // когда мы не касались джостика
        if (circleBound.isContains(touch) && isTouch && this.pointer == -1){
            this.pointer = pointer;
        }
        // когда мы касаемся джостика
        if (circleBound.overlaps(stickBound) && isTouch && this.pointer == this.pointer){
            work(new Point2D(x, y));
        }
        // stickImg выходит за границы circleImg
        if ((!isTouch && pointer == this.pointer) || (isTouch && this.pointer == this.pointer && !circleBound.isContains(touch))){
            returnStick();
        }
    }

    public void work(Point2D point2D){
        // описывает движение stickImg
        stickBound.pos.setPoint(point2D);
        float dx = circleBound.pos.getX() - stickBound.pos.getX();
        float dy = circleBound.pos.getY() - stickBound.pos.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        direction.setPoint(-(dx / distance), -(dy / distance));
    }

    public void returnStick(){
        // вызывается если Stick вышел за пределы окружности
        stickBound.pos.setPoint(stickBound.pos);
        direction.setPoint(0, 0);
        pointer = -1;
    }

    public Point2D getDir(){
        return direction;
    }
}
