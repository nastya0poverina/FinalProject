package com.vfive.game.Tools;

public class Circle {

    float radius;
    public Point2D pos;

    public Circle(Point2D pos, float radius) {
        this.pos = new Point2D(pos);
        this.radius = radius;
    }

    public boolean isContains(Point2D point2D) {
        // проверяет есть ли точка внутри окружности
        float dx = pos.getX() - point2D.getX();
        float dy = pos.getY() - point2D.getY();
        boolean isContains = dx * dx + dy * dy <= radius * radius;
        return isContains;
    }

    public boolean overlaps(Circle circle){
        // проверяет пересецение окружностей
        // dx и dy это разница между точкой и центром окружности по осям x и y
        float dx = pos.getX() - circle.pos.getX();
        float dy = pos.getY() - circle.pos.getY();
        float distance = dx * dx + dy * dy;
        float radiusSum = circle.radius + radius;
        boolean isOverlaps = distance < radiusSum * radiusSum;
        return isOverlaps;

    }

}
