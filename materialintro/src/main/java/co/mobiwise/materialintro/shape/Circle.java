package co.mobiwise.materialintro.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import co.mobiwise.materialintro.target.Target;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class Circle extends Shape {

    private int radius;

    private Point circlePoint;

    public Circle(Target target, Focus focus, FocusGravity focusGravity, int padding) {
        super(target, focus, focusGravity, padding);
        circlePoint = getFocusPoint();
        calculateRadius(padding);
    }

    @Override
    public void draw(Canvas canvas, Paint eraser, int padding){
        calculateRadius(padding);
        circlePoint = getFocusPoint();
        canvas.drawCircle(circlePoint.x, circlePoint.y, radius, eraser);
    }

    @Override
    public void reCalculateAll(){
        calculateRadius(padding);
        circlePoint = getFocusPoint();
    }

    private void calculateRadius(int padding){
        int side;

        if(focus == Focus.MINIMUM)
            side = Math.min(target.getRect().width() / 2, target.getRect().height() / 2);
        else if(focus == Focus.ALL)
            side = Math.max(target.getRect().width() / 2, target.getRect().height() / 2);
        else{
            int minSide = Math.min(target.getRect().width() / 2, target.getRect().height() / 2);
            int maxSide = Math.max(target.getRect().width() / 2, target.getRect().height() / 2);
            side = (minSide + maxSide) / 2;
        }

        radius = side + padding;
    }

    private int getRadius(){
        return radius;
    }

    @Override
    public Point getPoint(){
        return circlePoint;
    }

    @Override
    public int getHeight() {
        return getRadius();
    }

    @Override
    public boolean isTouchOnFocus(double dx, double dy) {
        return (dx + dy) <= Math.pow(radius, 2);
    }

}
