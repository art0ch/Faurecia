package com.will.android.faurecia;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TablePPM {

    RawPPM frontLeft = new RawPPM();
    RawPPM frontRight = new RawPPM();
    RawPPM backLeft = new RawPPM();
    RawPPM backRight = new RawPPM();

    int PPM;

    ArrayList<RawPPM> raws = new ArrayList<RawPPM>() {
        {
            add(frontLeft);
            add(frontRight);
            add(backLeft);
            add(backRight);
        }
    };

    public void countPPM() {
        int totalSum = 0;
        int defectSum = 0;

        for (RawPPM raw : raws) {
            totalSum += raw.getTotalItemsNumber();
            defectSum += raw.getDefectiveItemsNumber();
        }

        PPM = (defectSum * 1_000_000) / totalSum;
    }

    public int getPPM() {
        return PPM;
    }

    public RawPPM getFrontLeft() {
        return frontLeft;
    }

    public RawPPM getFrontRight() {
        return frontRight;
    }

    public RawPPM getBackLeft() {
        return backLeft;
    }

    public RawPPM getBackRight() {
        return backRight;
    }

    @NonNull
    @Override
    public String toString() {
        return "Table of details";
    }

    static class RawPPM {
        private String itemName;
        private int goodItemsNumber = 0;
        private int defectiveItemsNumber = 0;
        private int totalItems = 0;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getGoodItemsNumber() {
            return goodItemsNumber;
        }

        public void setGoodItemsNumber(int goodItemsNumber) {
            this.goodItemsNumber = goodItemsNumber;
        }

        public int getDefectiveItemsNumber() {
            return defectiveItemsNumber;
        }

        public void setDefectiveItemsNumber(int defectiveItemsNumber) {
            this.defectiveItemsNumber = defectiveItemsNumber;
        }

        public int getTotalItemsNumber() {
            return totalItems;
        }

        public void setTotalItemsNumber(int totalItems) {
            this.totalItems = totalItems;
        }
    }
}
