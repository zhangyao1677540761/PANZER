package com.panzer.tank1;

import java.util.Random;
import java.util.Vector;

/**
 * @学习小结 敌方坦克可以发射子弹
 * 1创建一个list集合用来保存子弹，
 * 2每创建一个敌方坦克对象就给该对象一个初始化的子弹对象，同时启动线程
 * 3当绘制坦克时，遍历敌方坦克对象的集合子弹
 * 4当子弹的死亡就从集合删除
 */
public class OtherTank extends Tank implements Runnable {
    Random rdm = new Random();
    Vector<ZiDan> zds = new Vector();
    boolean islive = true;
    Vector<OtherTank> otherTanks =new Vector();

    public void setOtherTanks(Vector<OtherTank> otherTanks) {
        this.otherTanks = otherTanks;
    }

    public OtherTank(int x, int y) {
        super(x, y);
       this.setDirect(2);//设置初始化向下的炮口
        this.setSpeed(3);
    }
    //判断是否发生触碰，发生碰撞返回true,不发生碰撞返回false，传入坦克对象
//    public boolean booleanOverLappingWithOtherTank(Tank myTank,  OtherTank otherTank){
//        //正方形坦克，确定中心单位置，模拟一个圆，该圆半径50左右(49点几)，炮口长55，炮口不计
//       if (myTank != otherTank) {
//            int mygety = myTank.getY()+35;
//            int mygetx = myTank.getX()+35;
//            int otgety = otherTank.getY()+35;
//            int otgetx = otherTank.getX()+35;
//            //1敌方坦克在左上方
//            if (mygety > otgety && mygetx >otgetx && ((mygety-otgety)*(mygety-otgety)+(mygetx-otgetx)*(mygetx-otgetx))<=5000) {
//                if ( otherTank.getDirect()==1 || otherTank.getDirect()==2) {
//                    System.out.println("碰撞1");
//                    return true;
//                }
//                return false;
//            }
//            //2敌方坦克在右上方
//            if (mygety > otgety && mygetx < otgetx && ((mygety-otgety)*(mygety-otgety)+(otgetx-mygetx)*(otgetx-mygetx)<=5000)) {
//                if ( otherTank.getDirect()==3 || otherTank.getDirect()==2) {
//                    System.out.println("碰撞2");
//                    return true;
//                }
//                return false;
//            }
//            //3敌方坦克在正上方
//            if (mygety > otgety && mygetx == otgetx && (mygety-otgety <=70 )) {
//                if ( otherTank.getDirect() == 2) {
//                    System.out.println("碰撞3");
//                    return true;
//                }
//                return false;
//            }
//            //4敌方坦克在左下方
//            if (mygety < otgety && mygetx > otgetx && ((otgety-mygety)*(otgety-mygety)+(mygetx-otgetx)*(mygetx-otgetx)<=5000)) {
//                if ( otherTank.getDirect()==0 || otherTank.getDirect()==1) {
//                    System.out.println("碰撞4");
//                    return true;
//                }
//                return false;
//            }
//            //5敌方坦克在右下方
//            if (mygety < otgety && mygetx < otgetx && ((otgety-mygety)*(otgety-mygety)+(otgetx-mygetx)*(otgetx-mygetx)<=5000)) {
//                if ( otherTank.getDirect()==0 || otherTank.getDirect()==3) {
//                    System.out.println("碰撞5");
//                    return true;
//                }
//                return false;
//            }
//            //6敌方坦克在正下方
//            if (mygety < otgety && mygetx == otgetx && (otgety-mygety <=70 )) {
//                if ( otherTank.getDirect()==0) {
//                    System.out.println("碰撞6");
//                    return true;
//                }
//                return false;
//            }
//            //7敌方坦克在正左方
//            if (mygety == otgety && mygetx > otgetx && (mygetx-otgetx <=70 ) ) {
//                if (otherTank.getDirect()==1 ) {
//                    System.out.println("碰撞7");
//                    return true;
//                }
//                return false;
//            }
//            //8敌方坦克在正右方
//            if (mygety == otgety && mygetx < otgetx && (otgetx-mygetx <=70 )) {
//                if ( otherTank.getDirect()==3) {
//                    System.out.println("碰撞8");
//                    return true;
//                }
//                return false;
//            }
//        }
//        return false;
//
//    }

    //判断敌方坦克与敌方坦克是否发生碰撞
//   public boolean OverLappingOtherTankWithOtherTank(){
//            for (int i = 0; i <otherTanks.size() ; i++) {
//               // System.out.println("到这了");
//                return booleanOverLappingWithOtherTank(this,otherTanks.get(i));
//            }
//      return false;
//    }


        public boolean isTouchEnemyTank(){
        switch (this.getDirect()){
            case 0: //上
                for (int i = 0;i< otherTanks.size();i++){
                    OtherTank OtherTank  = otherTanks.get(i);
                    if (this != OtherTank){
                        if (OtherTank.getDirect() == 0 || OtherTank.getDirect() == 2){
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX()  + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                        if (OtherTank.getDirect() == 1 || OtherTank.getDirect() == 3){
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() + 40 >= OtherTank.getX() && this.getX()  + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //右
                for (int i = 0;i< otherTanks.size();i++){
                    OtherTank OtherTank = otherTanks.get(i);
                    if (this != OtherTank){
                        if (OtherTank.getDirect() == 0 || OtherTank.getDirect() == 2){
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX() + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX()  + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY()  + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                        if (OtherTank.getDirect() == 1 || OtherTank.getDirect() == 3){
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX() + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX() + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY() + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                for (int i = 0;i< otherTanks.size();i++){
                    OtherTank OtherTank = otherTanks.get(i);
                    if (this != OtherTank){
                        if (OtherTank.getDirect() == 0 || OtherTank.getDirect() == 2){
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY() + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX()  + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY()  + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                        if (OtherTank.getDirect() == 1 || OtherTank.getDirect() == 3){
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY() + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() + 70 >= OtherTank.getX() && this.getX()  + 70 <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY()  + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                for (int i = 0;i< otherTanks.size();i++){
                    OtherTank OtherTank = otherTanks.get(i);
                    if (this != OtherTank){
                        if (OtherTank.getDirect()== 0 || OtherTank.getDirect() == 2){
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY()  + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                        if (OtherTank.getDirect() == 1 || OtherTank.getDirect() == 3){
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() >= OtherTank.getY() && this.getY() <= OtherTank.getY() + 70){
                                return true;
                            }
                            if (this.getX() >= OtherTank.getX() && this.getX() <= OtherTank.getX() + 70 &&
                                    this.getY() + 70 >= OtherTank.getY() && this.getY()  + 70 <= OtherTank.getY() + 70){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }


//是河就无法通过
   //返回是true就是河流，无法通过
    public boolean isRiver(){
        Surroundings River =  new Surroundings(0,400,500,70);
   if (this.getX()+70 <= River.getLength() && this.getY()+70 >= 400 && this.getY()+70 <= 470) {
     return true;
  }
       return false;

    }




    @Override
    public void run() {  //实现坦克自由移动
        while (true) {

            //实现敌方坦克不止射击一次
            //思路  当子弹集合里面没有子弹了，就再创建一个子弹对象并且启动它
            if (zds.size() <= 3 && this.islive == true) {
                //根据坦克方向创建不同方向的子弹
                ZiDan newzd = null;
                switch(this.getDirect()){
                    case 0:
                        newzd = new ZiDan(this.getX() + 35, this.getY() - 20, 0);
                        break;
                    case 1:
                        newzd = new ZiDan(this.getX() + 90, this.getY() + 35, 1);
                        break;
                    case 2:
                        newzd =  new ZiDan(this.getX() + 35, this.getY() + 90, 2);
                        break;
                    case 3:
                        newzd = new ZiDan(this.getX() - 20, this.getY() + 35, 3);
                        break;
                }
                zds.add(newzd);
                new Thread(newzd).start();
            }
            switch (this.getDirect()) {  //根据坦克的方向继续移动‘
                case 0:
                    for (int i = 0; i <10 ; i++) {
                        if (!isTouchEnemyTank() && this.getY()>15 && !isRiver() ) {  //防止出界
                            this.moveup();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i <10 ; i++) {
                        if (!isTouchEnemyTank() &&this.getX()+70 < 945 && !isRiver()) {
                            this.moveright();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i <10 ; i++) {
                        if (!isTouchEnemyTank() && this.getY()+70 < 695 && !isRiver()) {
                            this.movedown();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i <10 ; i++) {
                        if (!isTouchEnemyTank() && this.getX() > 20 && !isRiver()) {
                            this.moveleft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    this.setDirect(rdm.nextInt(4));
                    if (islive == false) {
                        break;   //如果死亡了，退出线程，即 退出循环结束线程
                    }
        }
    }
}
