package com.will.android.faurecia;


public class TableDLE {
    private double DLE;
    private double totalHours;
    private int goodSum;

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public int getGoodSum() {
        return goodSum;
    }

    public void setGoodSum(int goodSum) {
        this.goodSum = goodSum;
    }

    void countDLE() {
        if (totalHours != 0) {
            DLE = goodSum * 330 / totalHours / 3600 / 4 * 100;
        }
    }

    public double getDLE() {
        return DLE;
    }
}
