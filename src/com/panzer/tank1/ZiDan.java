package com.panzer.tank1;

/**
 * @学习小结
 * 发射一个子弹就启动一个线程，该线程让子弹一直向前跑
 * 1按下j就启动该对象的射击子弹的线程
 * 2碰到边界或者打到敌人就停止线程
 * 3让画纸尽可能的多次循环重绘，(可以设计成线程，每隔一秒就让其重绘)
 */
public class ZiDan extends Thread {
    private int x;
    private int y;
    private int direct;//子弹射出方向
    private int speed = 15;  //控制速度
    boolean islive =true; //是否存货

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

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ZiDan(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
//判断是否是铁墙，是为true
    public boolean ironWall(){
        Surroundings ironWall = new Surroundings(790,580,210,70);
        if (x >=ironWall.getX() && y<=600 && y>=530) {
                        return true;
        }
        return false;
    }


    @Override
    public void run() {
        while(true){        //子弹一直跑
            try {
                Thread.sleep(50);//若不设置会直接到边界，不会出现子弹的轨迹，(可以大幅度提高子弹射速)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch(direct){
                case 0://我子弹向上
                    y-=speed;
                    break;
                case 1://子弹向右
                    x+=speed;
                    break;
                case 2://子弹向下
                    y+=speed;
                    break;
                case 3://子弹向做
                    x-=speed;
                    break;
            }
         //  System.out.println(this.getName()+"子弹"+x+"子弹"+y);//验证
            //当子蛋碰到敌方坦克也会销毁......
            if (!(x >= 0 && x <=1000 && y<=750 &&y>=0 && islive == true && !ironWall())) {
                islive =false;
             //   System.out.println("子弹线程退出");
                break;  //当碰到墙壁就停止循环，即结束线程
            }
        }
    }
}
