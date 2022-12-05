package com.panzer.tank1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * @学习小结
 * JConsole
 */
@SuppressWarnings({"all"})
public class PanzerMod extends JFrame {
    private MyPanzerPanel mp = null;
   Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        new PanzerMod();
    }

    public PanzerMod() throws IOException {
        System.out.println("请选择:1重新开始2继续");
        String filepath = "src\\MyTankMusic.wav";
        PalyMusic musicObject = new PalyMusic();
        musicObject.playMusic(filepath);  //播放BGM
        String key = input.next();
        mp = new MyPanzerPanel(key);
        new Thread(mp).start();
        this.add(mp);
        this.addKeyListener(mp);//将监听器放入弹窗！！！不加入监听无效果
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点窗口退出会退出程序
        this.setSize(1200, 750); //设置窗口大小
        this.setVisible(true);//表示他是可视的
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        Reader.save();
                        System.out.println("游戏记录存储成功");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
             }
            });
    }
}

@SuppressWarnings({"all"})
class MyPanzerPanel extends JPanel implements KeyListener, Runnable {
    //实现键盘监听
    MyTank MYtank = null;
    Vector<OtherTank> otherTanks = new Vector<>();
    Vector<SaveDate> SaveDates = new Vector<>();
    Vector<Boom> Booms = new Vector<>();
    int otherTankssize = 8 ; //定义敌方坦克数量初始化
    //定义爆炸图片，显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    //    Tank tank1 = null;
//    Tank tank2 = null;
//    Tank tank3 = null;
//    int xx = 0;//定义xx为横移动的度
//    int yy = 0;//定义yy为横移动的度
    public MyPanzerPanel(String key) throws IOException {

        File file = new File(Reader.getAddress());
        if (file.exists()) {
            SaveDates = Reader.otherTankDate();
        }else{
            System.out.println("文件不存在,已结重新开始");
            key = "1";
        }
        Reader.setOtherTanks(otherTanks);
        MYtank = new MyTank(930, 650);
//        tank1 = new OtherTank(200,100);
//        tank2 = new OtherTank(400,100);
//        tank3 = new OtherTank(600,100);
        switch(key){
            case "1":
                Reader.setHitTanks(0);
                for (int i = 0; i < otherTankssize; i++) {
                    OtherTank otherTank = new OtherTank(100 * (i + 1), 100);
                    otherTank.setOtherTanks(otherTanks);
                    //给敌方坦克设置一个子弹，从炮口发出
                    new Thread(otherTank).start();
                    ZiDan zidan = new ZiDan(otherTank.getX() + 35, otherTank.getY() + 90, otherTank.getDirect());
                    zidan.setSpeed(5);
                    //加入敌方坦克类定义的集合，方便管理
                    otherTank.zds.add(zidan);
                    new Thread(zidan).start();
                    //  otherTank.setDirect(2);//设置初始化炮口朝下
                    otherTanks.add(otherTank);
                } //批量创造敌方坦克对象
                //  MYtank.setSpeed(10);
                break;
            case "2":

                for (int i = 0; i < SaveDates.size(); i++) {
                    SaveDate saveDate = SaveDates.get(i);

                    OtherTank otherTank = new OtherTank(saveDate.getY(), saveDate.getY());
                    otherTank.setDirect(saveDate.getDirect());
                    otherTank.setOtherTanks(otherTanks);
                    //给敌方坦克设置一个子弹，从炮口发出
                    new Thread(otherTank).start();
                    ZiDan zidan = new ZiDan(otherTank.getX() + 35, otherTank.getY() + 90, otherTank.getDirect());
                    zidan.setSpeed(5);
                    //加入敌方坦克类定义的集合，方便管理
                    otherTank.zds.add(zidan);
                    new Thread(zidan).start();
                    //  otherTank.setDirect(2);//设置初始化炮口朝下
                    otherTanks.add(otherTank);
                } //批量创造敌方坦克对象
                //  MYtank.setSpeed(10);
                break;
            default:
                System.out.println("输入错误");
        }



        //加载图
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/panzer/tank1/11.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/panzer/tank1/22.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/panzer/tank1/33.png"));
        image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/panzer/tank1/索隆.png"));
    }
        public void  YourGrade(Graphics g){
            g.setColor(Color.RED);
            g.setFont(new Font("行书",Font.BOLD,40));
            g.drawString("击毁数量",1010,40);
            int x = 1010;
            int y = 90;
            g.setColor(Color.blue);
            g.drawRect(x, y, 10, 70);
            g.drawRect(x + 60, y, 10, 70);
            g.drawRect(x + 10, y + 10, 50, 50);
            g.fillOval(x + 30, y + 30, 10, 10);
            g.fillRect(x + 33, y - 20, 4, 55);
            g.setColor(Color.black);
            g.setFont(new Font("行书",Font.BOLD,30));
            g.drawString(Reader.getHitTanks()+"",1110,140);
        }  //成绩面板

    public void YourSurroundings(Graphics g){
        g.setColor(Color.blue);
    //    g.drawRect(0, 400, 500, 70);
        g.fill3DRect(0, 400, 500, 70,false);
        g.setColor(Color.RED);
        g.setFont(new Font("行书",Font.BOLD,40));
        g.drawString("我是一条河",200,450);
        g.setFont(new Font("行书",Font.BOLD,20));
        g.drawString("(我方可以过",0,430);
        g.drawString("(敌方不能过)",0,450);

        g.setColor(Color.gray);
        g.fill3DRect(790, 530, 210, 70,false);
        g.setColor(Color.RED);
        g.setFont(new Font("行书",Font.BOLD,30));
        g.drawString("我是铁墙",810,560);
        g.drawString("我打不穿",810,590);

    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);//画纸
        g.fillRect(0, 0, 1000, 400);
        g.fillRect(500, 400, 500, 70);
        g.fillRect(0, 470, 1000, 60);
        g.fillRect(0, 530, 790, 70);
        g.fillRect(0, 600, 1000, 150);

        //  tankMOD(tank.getX()+xx,tank.getY()+yy,g,tank.getDirect(),0);
        g.setColor(Color.yellow);
        g.fillRect(1000,0,200,750);
        YourGrade(g);
        YourSurroundings(g);
        if (MYtank != null && MYtank.islive == true) {
            //我还活着画出坦克
            tankMOD(MYtank.getX(), MYtank.getY(), g, MYtank.getDirect(), 0);
        }else{
            //我被击中后出现索隆
            g.drawImage(image4,0, 0, 1000, 750, this);
        }
        //当我得坦克没死亡才绘制我得坦克
        //  ZiDanMOD(MYtank.getX(),MYtank.getY(),g,1);
        //绘制子弹
        if (MYtank != null && MYtank.islive == true) {
            g.setColor(Color.red);
            for (int i = 0; i < MYtank.myZiDanVector.size(); i++) {
                ZiDan ziDan = MYtank.myZiDanVector.get(i);
                if (ziDan != null && ziDan.islive == true) { //子弹存在
                    g.fill3DRect(ziDan.getX() - 3, ziDan.getY() - 3, 6, 6, true);
                } else {//若子弹死亡，就从集合取出
                    MYtank.myZiDanVector.remove(ziDan);
                }
            }//我的坦克Booms有对象就会显示图片

        }

        try {
            Thread.sleep(50); //不知道为啥第一个不爆炸，加了个休眠就可以了，怀疑是加载太快了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < Booms.size(); i++) {
            Boom boom = Booms.get(i);
            if (boom.life > 4) {
                g.drawImage(image1, boom.x, boom.y, 70, 70, this);
            } else if (boom.life > 2) {
                g.drawImage(image2, boom.x, boom.y, 70, 70, this);
            } else {
                g.drawImage(image3, boom.x, boom.y, 70, 70, this);
            }
            boom.lifeDown();
            if (boom.life == 0) {
                Booms.remove(boom);
            }
        }//爆炸效果

        for (int i = 0; i < otherTanks.size(); i++) {
            OtherTank otherTank = otherTanks.get(i);
            if (otherTank.islive == true) { //如果敌方坦克是活着的，绘制出来，死了就不管了
                tankMOD(otherTank.getX(), otherTank.getY(), g, otherTank.getDirect(), 1);
                Iterator<ZiDan> iterator = otherTank.zds.iterator(); //将子弹遍历出来绘制
                while (iterator.hasNext()) {
                    ZiDan next = iterator.next();
                    if (next.islive == false) {
                        iterator.remove();
                    }
                    g.setColor(Color.GREEN);
                    g.fill3DRect(next.getX() - 3, next.getY(), 6, 6, false);
                }
            }


//                //取出子弹
//                ZiDan ziDan = otherTank.zds;
//                //绘制子弹模型
//                if (ziDan.islive == true) {  //子弹是存在弹窗时显示，反之无意义
//                    g.fill3DRect(ziDan.getX()-3,ziDan.getX(),6,6,false);
//                }else{
//                    otherTank.zds.remove(ziDan);  //把出界的删除出集合
//                }

        }//将批量的坦克通过坦克加载器在弹窗展现出来
//        tankMOD(tank1.getX(),tank1.getY(),g,tank1.getDirect(),1);
//        tankMOD(tank2.getX(),tank2.getY(),g,tank2.getDirect(),1);
//        tankMOD(tank3.getX(),tank3.getY(),g,tank3.getDirect(),1);
        // tankMOD(tank.getX()+80,tank.getY(),g,0,1);  //我的坦克定义横长度70像素，竖长度70像素
    }  //游戏界面


    public void tankMOD(int x, int y, Graphics g, int direct, int type) {
        //x，y是坦克坐标//g是画笔//direct是方向//type是类型
        switch (type) {
            case 0://我的坦克颜色
                g.setColor(Color.cyan);
//                g.fillRect(tank.getX(),tank.getY(),10,70);
//                g.fillRect(tank.getX()+60,tank.getY(),10,70);
//                g.fillRect(tank.getX()+10,tank.getY()+10,50,50);
//                g.setColor(Color.CYAN);
//                g.fillOval(tank.getX()+30,tank.getY()+30,10,10);
//                g.setColor(Color.CYAN);
//                g.fillRect(tank.getX()+33,tank.getY()-20,4,55);
                break;
            case 1://敌方坦克颜色
                g.setColor(Color.yellow);
//                g.fillRect(tank.getX(),tank.getY(),10,70);
//                g.fillRect(tank.getX()+60,tank.getY(),10,70);
//                g.fillRect(tank.getX()+10,tank.getY()+10,50,50);
//                g.setColor(Color.CYAN);
//                g.fillOval(tank.getX()+30,tank.getY()+30,10,10);
//                g.setColor(Color.CYAN);
//                g.fillRect(tank.getX()+33,tank.getY()-20,4,55);
                break;
        }//坦克颜色配置，我的蓝，敌方黄
        switch (direct) {
            case 0://向上形状
                g.draw3DRect(x, y, 10, 70, false);
                g.draw3DRect(x + 60, y, 10, 70, false);
                g.draw3DRect(x + 10, y + 10, 50, 50, false);
                g.fillOval(x + 30, y + 30, 10, 10);
                g.fillRect(x + 33, y - 20, 4, 55);
                break;
            case 1://向右形状
                g.draw3DRect(x, y, 70, 10, false);
                g.draw3DRect(x, y + 60, 70, 10, false);
                g.draw3DRect(x + 10, y + 10, 50, 50, false);
                g.fillOval(x + 30, y + 30, 10, 10);
                g.fillRect(x + 35, y + 33, 55, 4);
                break;
            case 2://向下形状
                g.draw3DRect(x, y, 10, 70, false);
                g.draw3DRect(x + 60, y, 10, 70, false);
                g.draw3DRect(x + 10, y + 10, 50, 50, false);
                g.fillOval(x + 30, y + 30, 10, 10);
                g.fillRect(x + 33, y + 35, 4, 55);
                break;
            case 3://向左形状
                g.draw3DRect(x, y, 70, 10, false);
                g.draw3DRect(x, y + 60, 70, 10, false);
                g.draw3DRect(x + 10, y + 10, 50, 50, false);
                g.fillOval(x + 30, y + 30, 10, 10);
                g.fillRect(x - 20, y + 33, 55, 4);
                break;
            default:
                System.out.println("只有123");
        }
    }//坦克MOD加载器
    //子弹MOD加载器
//    public void ZiDanMOD(int x,int y,Graphics g,int direct){
//        g.setColor(Color.red);
//        switch (direct) {
//            case 0://向上跑
//                g.fill3DRect(x+35-3,y-20-3,6,6,false);//初始向上
//            case 1://向右跑
//                g.fill3DRect(x+90-3,y+35-3,6,6,false);//初始向上
//            case 2://向下跑
//                g.fill3DRect(x+35-3,y+90-3,6,6,false);//初始向上
//            case 3://向左跑
//                g.fill3DRect(x-20-3,y+35-3,6,6,false);//初始向上
//        }
//
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override //监听某个按键，若按下，会触发 我的坦克操作配置
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {//向下
            MYtank.setDirect(2);
            if (MYtank.getY() + 70 < 695) {
                MYtank.movedown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            MYtank.setDirect(0);
            if (MYtank.getY() > 15) {  //防止出界
                MYtank.moveup();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            MYtank.setDirect(1);
            if (MYtank.getX() + 70 < 945) {
                MYtank.moveright();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            MYtank.setDirect(3);
            if (MYtank.getX() > 20) {
                MYtank.moveleft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //创建一个线程，让其一直在该线上跑
            //想让前一个线程停止才可以发射后一个
            //个人理解：当子弹对象不存在或者子弹的对象已经GG了，才可以发射子弹
            //   if (MYtank.zidan == null *|| MYtank.zidan.islive ==false) {
            MYtank.ZiDangogogo();
            // }
            //实现多发子弹，即 同时多开线程，创建多个子弹对象，用Vector存贮
            //绘制时，遍历该集合

        }

        //   this.repaint();//面板重绘，线程实现

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            if (MYtank.zidan != null && MYtank.zidan.islive == true) {
//                for (int i = 0; i < otherTanks.size(); i++) {
//                    OtherTank otherTank = otherTanks.get(i);
//                    booleanHitTank(otherTank, MYtank.zidan);
//                }
//            }
            booleanHitOtherTanks(); //判断是否击中别人
            booleanHitMyTank(); //判断是否击中我

            this.repaint();//面板重绘

        }//过一秒就重绘一次
    } //重绘画板

    //判断是否击中坦克,让方法跟着重绘的线程走，重绘一次就调用一次(单发)
    public void booleanHitTank(OtherTank ot, ZiDan zd) {

        switch (ot.getDirect()) {
            case 0:
            case 2:
                if (zd.getX() > ot.getX() && zd.getX() < ot.getX() + 70 &&
                        zd.getY() > ot.getY() && zd.getY() < ot.getY() + 70) {
                    ot.islive = false;
                    zd.islive = false;
                    Boom boom = new Boom(ot.getX(), ot.getY());
                    Booms.add(boom);
                    otherTanks.remove(ot);
                    Reader.addhitTanks();
                }
                break;
            case 1:
            case 3:
                if (zd.getX() > ot.getX() && zd.getX() < ot.getX() + 70 &&
                        zd.getY() > ot.getY() && zd.getY() < ot.getY() + 70) {
                    ot.islive = false;
                    zd.islive = false;
                    Boom boom = new Boom(ot.getX(), ot.getY());
                    Booms.add(boom);
                    otherTanks.remove(ot);
                    Reader.addhitTanks();
                }

        }


//        //敌方坦克在上
//            if (zd.getY() <= ot.getY() && zd.getX() >= ot.getY() &&  zd.getX() <= ot.getY()+70 ) {
//                zd.islive=false;
//                ot.islive=false;
//            }
//            //敌方坦克在下
//            if (zd.getY() >= ot.getY() && zd.getX() >= ot.getY() &&  zd.getX() <= ot.getY()+70 ) {
//                zd.islive=false;
//                ot.islive=false;
//            }
//            //敌方坦克在左
//
//            //敌方坦克在右

    }

    //判断是否击中我的坦克，后期优化
    public void booleanHitTank2(MyTank myTank, ZiDan zd) {

        switch (myTank.getDirect()) {
            case 0:
            case 2:
                if (zd.getX() > myTank.getX() && zd.getX() < myTank.getX() + 70 &&
                        zd.getY() > myTank.getY() && zd.getY() < myTank.getY() + 70) {
                    myTank.islive = false;
                    zd.islive = false;
                    Boom boom = new Boom(myTank.getX(), myTank.getY());
                    Booms.add(boom);
                    otherTanks.remove(myTank);
                }
                break;
            case 1:
            case 3:
                if (zd.getX() > myTank.getX() && zd.getX() < myTank.getX() + 70 &&
                        zd.getY() > myTank.getY() && zd.getY() < myTank.getY() + 70) {
                    myTank.islive = false;
                    zd.islive = false;
                    Boom boom = new Boom(myTank.getX(), myTank.getY());
                    Booms.add(boom);
                    otherTanks.remove(myTank);
                }

        }


//        //敌方坦克在上
//            if (zd.getY() <= ot.getY() && zd.getX() >= ot.getY() &&  zd.getX() <= ot.getY()+70 ) {
//                zd.islive=false;
//                ot.islive=false;
//            }
//            //敌方坦克在下
//            if (zd.getY() >= ot.getY() && zd.getX() >= ot.getY() &&  zd.getX() <= ot.getY()+70 ) {
//                zd.islive=false;
//                ot.islive=false;
//            }
//            //敌方坦克在左
//
//            //敌方坦克在右

    }

    //连发，让我的所有的子弹和所有的敌方坦克进行判断
    public void booleanHitOtherTanks() {
        for (int i = 0; i < MYtank.myZiDanVector.size(); i++) {
            ZiDan ziDan = MYtank.myZiDanVector.get(i);
            if (ziDan != null && ziDan.islive == true) {
                for (int j = 0; j < otherTanks.size(); j++) {
                    OtherTank otherTank = otherTanks.get(j);
                    booleanHitTank(otherTank, ziDan);
                }
            }
        }
    }


    //如果敌方子弹击中我
    //遍历敌方坦克的所有坦克，在中级再遍历所有子弹，是否击中我
    public void booleanHitMyTank() {
        for (int i = 0; i < otherTanks.size(); i++) {
            OtherTank otherTank = otherTanks.get(i);
            for (int j = 0; j < otherTank.zds.size(); j++) {

                    ZiDan ziDan = otherTank.zds.get(j);
                if (MYtank != null && MYtank.islive == true && ziDan.islive == true) {
                    booleanHitTank2(MYtank, ziDan);
                }
            }
        }
    }


//    //判断是否发生触碰，发生碰撞返回true,不发生碰撞返回false，传入坦克对象
//    public boolean booleanOverLappingWithOtherTank(Tank myTank,  OtherTank otherTank){
//        //正方形坦克，确定中心单位置，模拟一个圆，该圆半径50左右(49点几)，炮口长55，炮口不计
//        int mygety = myTank.getY();
//        int mygetx = myTank.getX();
//        int otgety = otherTank.getY();
//        int otgetx = otherTank.getX();
//        //1敌方坦克在左上方
//        if (mygety > otgety && mygetx >otgetx ) {
//            if ((mygety-otgety)*(mygety-otgety)+(mygetx-otgetx)*(mygetx-otgetx)<=5000) {
//                return true;
//            }
//            return false;
//        }
//        //2敌方坦克在右上方
//        if (mygety > otgety && mygetx < otgetx) {
//            if ((mygety-otgety)*(mygety-otgety)+(otgetx-mygetx)*(otgetx-mygetx)<=5000) {
//                return true;
//            }
//            return false;
//        }
//        //3敌方坦克在正上方
//        if (mygety > otgety && mygetx == otgetx) {
//            if (mygety-otgety <=70 ) {
//                return true;
//            }
//            return false;
//        }
//        //4敌方坦克在左下方
//        if (mygety < otgety && mygetx > otgetx) {
//            if ((otgety-mygety)*(otgety-mygety)+(mygetx-otgetx)*(mygetx-otgetx)<=5000) {
//                return true;
//            }
//            return false;
//        }
//        //5敌方坦克在右下方
//        if (mygety < otgety && mygetx < otgetx) {
//            if ((otgety-mygety)*(otgety-mygety)+(otgetx-mygetx)*(otgetx-mygetx)<=5000) {
//                return true;
//            }
//            return false;
//        }
//        //6敌方坦克在正下方
//        if (mygety < otgety && mygetx == otgetx) {
//            if (otgety-mygety <=70 ) {
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }

//    //判断自己坦克与敌方坦克是否发生碰撞
//    public boolean OverLappingMyTankWithOtherTank(){
//        for (int i = 0; i < otherTanks.size(); i++) {
//            booleanOverLappingWithOtherTank(MYtank,otherTanks.get(i));
//        }
//        return false;
//    }





}
    //创建一个控制坦克范围的方法
//    public void liveSpace(Tank tank){
//        if (tank.getX() >= 1000 ||  tank.getX() <= 0  || tank.getY() >=750 ||  tank.getX() <= 0 ) {
//
//        }
//    }

