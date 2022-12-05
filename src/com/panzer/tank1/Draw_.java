package com.panzer.tank1;//package com.panzer.tank1;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.security.Key;
//
///**
// * @学习小结
// */
//public class Draw_ extends   {  //继承窗口,是一个弹窗，可视为画板
//    private MyPanel mp = null;//定义一个面纸
//    public static void main(String[] args) {
//            new Draw_();
//    }
//    public Draw_(){
//        //初始化面板s
//        mp = new MyPanel();
//        this.add(mp);
//        this.addKeyListener(mp);//将监听器放入弹窗
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点窗口退出会退出程序
//        this.setSize(1000,750); //设置窗口大小
//        this.setVisible(true);//表示他是可视的
//    }
//}
////KeyListener是监听器，可以监听键盘事件
//class MyPanel extends JPanel implements KeyListener {
//    int x = 10;
//    int y = 10;
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        g.fillOval(x,y,20,20);
//    }
//
//    @Override//监听字符输出，会触发
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override//监听某个按键，若按下，会触发
//    public void keyPressed(KeyEvent e) {
//     //   System.out.println((char)e.getKeyCode()+"被按下...");
//        if (e.getKeyCode() == KeyEvent.VK_DOWN) {//向下
//            y+=3;
//        }else if (e.getKeyCode() == KeyEvent.VK_UP) {
//            y-=3;
//        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            x+=3;
//        }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            x-=3;
//        }
//
//        this.repaint();//面板重绘
//    }
//
//    @Override//监听某个按键，若松开，会触发
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
//
//
//
//
//
//
