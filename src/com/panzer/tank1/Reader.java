package com.panzer.tank1;

import java.io.*;
import java.util.Vector;

/**
 * @学习小结
 */
public class Reader {
    private static int hitTanks = 0;  //击毁数量
    private static FileWriter fileWriter = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String address = "src\\MyReader.txt";
    private static Vector<OtherTank> otherTanks =null;
    private static Vector<SaveDate> otherTankDates = new Vector<>();
//读取
    public static Vector<SaveDate> otherTankDate() throws IOException { //保存敌方位置
            br = new BufferedReader(new FileReader(address));
        hitTanks = Integer.parseInt(br.readLine());  //先读取第一行的击毁数量
         String str ;
        while((str = br.readLine() )!=null){
            String [] xy = str.split(" ");
            SaveDate saveDate = new SaveDate(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), Integer.parseInt(xy[2]));
            otherTankDates.add(saveDate);
        }
        br.close();

        return otherTankDates;
    }


    public static void setOtherTanks(Vector<OtherTank> otherTanks) {
        Reader.otherTanks = otherTanks;
    }

    public static String getAddress() {
        return address;  //记录文件路径
    }

    public static int getHitTanks() {
        return hitTanks;
    }

    public static void setHitTanks(int hitTanks) {
        Reader.hitTanks = hitTanks;
    }

public static void addhitTanks(){
    Reader.hitTanks++;  //每次调用就意味着打掉了对面一个坦克
}

//保存
    public static void save() throws IOException {
         bw =   new BufferedWriter(new FileWriter(address));
            bw.write(hitTanks+"");
            bw.newLine();
            for (int i = 0; i <otherTanks.size() ; i++) {
                OtherTank otherTank = otherTanks.get(i);
                bw.write(otherTank.getX()+" "+otherTank.getY()+" "+otherTank.getDirect());
                bw.newLine();
            }  //读取所有敌方坦克的坐标

            bw.close();

    }


}
