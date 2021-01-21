package com.will.android.faurecia;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TablePPM {

    RowPPM frontLeft = new RowPPM();
    RowPPM frontRight = new RowPPM();
    RowPPM backLeft = new RowPPM();
    RowPPM backRight = new RowPPM();
    Form form;

    int PPM;
    int goodItemsTotal;

    ArrayList<RowPPM> rows = new ArrayList<RowPPM>() {
        {
            add(frontLeft);
            add(frontRight);
            add(backLeft);
            add(backRight);
        }
    };

    public ArrayList<RowPPM> getRows() {
        return rows;
    }

    public void countPPM() {

        goodItemsTotal = 0;
        int defectSum = 0;
        int totalSum = 0;

        for (RowPPM raw : rows) {
            goodItemsTotal += raw.getGoodItemsNumber();
            totalSum += raw.getTotalItemsNumber();
            defectSum += raw.getDefectiveItemsNumber();
        }
        if (totalSum != 0) {
            PPM = (defectSum * 1_000_000) / totalSum;
        }

    }

    public int getGoodItemsTotal() {
        return goodItemsTotal;
    }

    public int getPPM() {
        return PPM;
    }

    public RowPPM getFrontLeft() {
        return frontLeft;
    }
    public RowPPM getFrontRight() {
        return frontRight;
    }
    public RowPPM getBackLeft() {
        return backLeft;
    }
    public RowPPM getBackRight() {
        return backRight;
    }

    @NonNull
    @Override
    public String toString() {
        return "Table of details";
    }

    static class RowPPM {
        private EditText goodItemsView;
        private EditText defectiveItemsView;
        private TextView totalView;

        public EditText getGoodItemsView() {
            return goodItemsView;
        }

        public void setGoodItemsView(EditText goodItemsView) {
            this.goodItemsView = goodItemsView;
        }

        public EditText getDefectiveItemsView() {
            return defectiveItemsView;
        }

        public void setDefectiveItemsView(EditText defectiveItemsView) {
            this.defectiveItemsView = defectiveItemsView;
        }

        public TextView getTotalView() {
            return totalView;
        }

        public void setTotalView(TextView totalView) {
            this.totalView = totalView;
        }

        private int goodItemsNumber = 0;
        private int defectiveItemsNumber = 0;
        private int totalItems = 0;

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
