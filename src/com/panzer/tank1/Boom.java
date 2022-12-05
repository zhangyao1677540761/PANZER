package com.panzer.tank1;

/**
 * @学习小结
 */
public class Boom {
    int x;
    int y;
    boolean islive = true; //是否存货
    int life = 6;//炸弹生命周期

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if (life> 0) {
            life--;
        }else{
            islive = false;
        }
    }

}
