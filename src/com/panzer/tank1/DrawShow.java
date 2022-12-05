package com.panzer.tank1;//package com.panzer.drow;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// * @学习小结
// */
//public class DrawShow extends JFrame {  //继承窗口,是一个弹窗，可视为画板
//    private MyPanel mp = null;//定义一个面纸
//    public static void main(String[] args) {
//        new DrawShow();
//    }
//    public DrawShow(){
//        //初始化面板
//        mp = new MyPanel();
//        this.add(mp);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点窗口退出会退出程序
//        this.setSize(1000,1000); //设置窗口大小
//        this.setVisible(true);//表示他是可视的
//    }
//}
////MyPanel相当于是画纸，Graphics相当于是画笔
//class MyPanel extends JPanel{
//    @Override
//    public void paint(Graphics g) { //绘图方法
//        super.paint(g);//掉用父类的方法完成初始化
//        //   System.out.println("我被调用了");
//        //第一次显示会调用，最大化最小化会调用(窗口变化都会调用)，repaint方法被调用也会调用
//
//        // g.drawOval(10,10,100,100);
//        //xy是圆左上角，
//        // width是横长,height是竖长
////g.drawLine(10,10,100,10);//直线(x1y1起点位置，x2y2终点位置)
////g.drawRect(10,10,100,100);//画一个框框，类似于圆
////g.fillRect(10,10,100,100);//填充框框
////g.setColor(Color.red);//填充颜色
////g.fillOval(10,10,100,100);//填充圆
//
//Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/pd.jpeg"));
//g.drawImage(image,0,0,640,803,this);  // 放入图片
//
////g.setFont(new Font("行书",Font.BOLD,50));  //显示字体
////g.drawString("北京你好",400,400);//xy是左下角
//
//    }
//}
