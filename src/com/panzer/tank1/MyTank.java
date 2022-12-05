package com.panzer.tank1;

import java.util.Vector;

/**
 * @学习小结
 */
public class MyTank extends Tank {
    Vector<ZiDan> myZiDanVector = new Vector<ZiDan>();
    public MyTank(int x, int y) {
        super(x, y);
        this.setSpeed(8);
    }
    boolean islive = true;





    ZiDan zidan = null;
    public void ZiDangogogo() {
        if (myZiDanVector.size() == 100) {
            return;   //控制最多5个子弹，即最多包含5个对象
        }
        switch (this.getDirect()) {
            case 0:
                zidan = new ZiDan(this.getX() + 35, this.getY() - 20, 0);
                break;
            case 1:
                zidan = new ZiDan(this.getX() + 90, this.getY() + 35, 1);
                break;
            case 2:
                zidan = new ZiDan(this.getX() + 35, this.getY() + 90, 2);
                break;
            case 3:
                zidan = new ZiDan(this.getX() - 20, this.getY() + 35, 3);
                break;
        }
        myZiDanVector.add(zidan);//加入集合

        zidan.start();//启动线程
    }
}
