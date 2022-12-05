package com.panzer.tank1;

/**
 * @学习小结
 */
public class Surroundings {
    int x;
    int y;
    int length;
    int weight;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Surroundings(int x, int y, int length, int weight) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.weight = weight;
    }

}
