package com.will.android.faurecia;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;


public class PpmFragment extends Fragment {

    MainActivity mainActivity;

    public PpmFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    View ppm;

    private EditText frontLeftNormal;
    private EditText frontLeftDefect;
    private TextView frontLeftTotal;

    private EditText frontRightNormal;
    private EditText frontRightDefect;
    private TextView frontRightTotal;

    private EditText backLeftNormal;
    private EditText backLeftDefect;
    private TextView backLeftTotal;

    private EditText backRightNormal;
    private EditText backRightDefect;
    private TextView backRightTotal;

    private TextView PPM_number;
    private TextView dle_PPM_number;
    private Button toCount;

    private Fragment dle;
    private Form form;

    TablePPM tablePPM;
    TableDLE tableDLE;
    ArrayList<TablePPM.RowPPM> rows;

    ConstraintLayout constraintLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ppm = inflater.inflate(R.layout.fragment_ppm, container, false);

        constraintLayout = ppm.findViewById(R.id.ppm_layout);
        constraintLayout.setOnFocusChangeListener(onFocusChangeListener);

        form = this.mainActivity.getForm();
        dle = this.mainActivity.getDleFragment();

        tableDLE = form.getTableDLE();
        dle_PPM_number = Objects.requireNonNull(dle.getView()).findViewById(R.id.dle_PPM_number);

        tablePPM = form.getTablePPM();
        rows = tablePPM.getRows();
        PPM_number = ppm.findViewById(R.id.PPM_number);
        toCount = ppm.findViewById(R.id.PPM_count_button);

        toCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tablePPM.countPPM();
                int result = tablePPM.getPPM();
                PPM_number.setText(String.valueOf(result));
                dle_PPM_number.setText(String.valueOf(result));
            }
        });

        initViews();
        addListeners();
        setRowsFields();

        return ppm;
    }


    private TextWatcher getTextWatcher(final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String stringIn = s.toString();
                if (stringIn.equals("0")) {
                    editText.removeTextChangedListener(this);
                    editText.setText("");
                    editText.addTextChangedListener(this);
                } else {
                    try {
                        int in = Integer.parseInt(s.toString());
                        checkAndFill(editText, in);
                    } catch (Exception e) {
                        checkAndFill(editText, 0);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    void checkAndFill(EditText editText, int in) {
        for (TablePPM.RowPPM row : rows) {
            if (editText.equals(row.getGoodItemsView()) || editText.equals(row.getDefectiveItemsView())) {
                if (row.getGoodItemsView().equals(editText)) {
                    row.setGoodItemsNumber(in);
                } else {
                    row.setDefectiveItemsNumber(in);
                }

                int res = row.getGoodItemsNumber() + row.getDefectiveItemsNumber();
                Log.d("int res", String.valueOf(res));
                row.setTotalItemsNumber(res);
                if (res == 0) {
                    row.getTotalView().setText("");
                } else {
                    row.getTotalView().setText(String.valueOf(res));
                }

            }
        }
    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (hasFocus) {
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE));
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

        }
    };

    void initViews() {
        frontLeftNormal = ppm.findViewById(R.id.front_left_normal);
        frontLeftDefect = ppm.findViewById(R.id.front_left_defect);
        frontLeftTotal = ppm.findViewById(R.id.front_left_total);

        frontRightNormal = ppm.findViewById(R.id.front_right_normal);
        frontRightDefect = ppm.findViewById(R.id.front_right_defect);
        frontRightTotal = ppm.findViewById(R.id.front_right_total);

        backLeftNormal = ppm.findViewById(R.id.back_left_normal);
        backLeftDefect = ppm.findViewById(R.id.back_left_defect);
        backLeftTotal = ppm.findViewById(R.id.back_left_total);

        backRightNormal = ppm.findViewById(R.id.back_right_normal);
        backRightDefect = ppm.findViewById(R.id.back_right_defect);
        backRightTotal = ppm.findViewById(R.id.back_right_total);
    }

    void addListeners() {
        frontLeftNormal.addTextChangedListener(getTextWatcher(frontLeftNormal));
        frontLeftDefect.addTextChangedListener(getTextWatcher(frontLeftDefect));
        frontRightNormal.addTextChangedListener(getTextWatcher(frontRightNormal));
        frontRightDefect.addTextChangedListener(getTextWatcher(frontRightDefect));
        backLeftNormal.addTextChangedListener(getTextWatcher(backLeftNormal));
        backLeftDefect.addTextChangedListener(getTextWatcher(backLeftDefect));
        backRightNormal.addTextChangedListener(getTextWatcher(backRightNormal));
        backRightDefect.addTextChangedListener(getTextWatcher(backRightDefect));
    }


    void setRowsFields() {
        tablePPM.getFrontLeft().setGoodItemsView(frontLeftNormal);
        tablePPM.getFrontLeft().setDefectiveItemsView(frontLeftDefect);
        tablePPM.getFrontLeft().setTotalView(frontLeftTotal);

        tablePPM.getFrontRight().setGoodItemsView(frontRightNormal);
        tablePPM.getFrontRight().setDefectiveItemsView(frontRightDefect);
        tablePPM.getFrontRight().setTotalView(frontRightTotal);

        tablePPM.getBackLeft().setGoodItemsView(backLeftNormal);
        tablePPM.getBackLeft().setDefectiveItemsView(backLeftDefect);
        tablePPM.getBackLeft().setTotalView(backLeftTotal);

        tablePPM.getBackRight().setGoodItemsView(backRightNormal);
        tablePPM.getBackRight().setDefectiveItemsView(backRightDefect);
        tablePPM.getBackRight().setTotalView(backRightTotal);
    }

}




