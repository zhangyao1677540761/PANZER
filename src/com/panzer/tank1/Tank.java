package com.panzer.tank1;

/**
 * @学习小结
 */
public class Tank {
    private int x;
    private int y;
    private int direct;//坦克形状方向
    private int speed = 8;  //控制速度

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveup(){
        y -=speed;
    }
    public void moveright(){
       x +=speed;
    }
    public void movedown(){
        y +=speed;
    }
    public void moveleft(){
        x -=speed;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
