package com.vfive.game.Tools;

public class Point2D {

    private float x;
    private float y;

    public Point2D(Point2D point2D) {
        x = point2D.getX();
        y = point2D.getY();
    }

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void add(float x, float y){
        this.x += x;
        this.y += y;
    }

    public void setPoint(Point2D point){
        x = point.getX();
        y = point.getY();
    }

    public void setPoint(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
}
