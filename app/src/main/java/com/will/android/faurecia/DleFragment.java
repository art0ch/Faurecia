package com.will.android.faurecia;

import android.content.Context;
import android.icu.text.DecimalFormat;
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

import java.util.Objects;

public class DleFragment extends Fragment {

    MainActivity mainActivity;

    public DleFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private EditText hours;
    private TextView DLE_number;
    private Button toCount;

    private TableDLE tableDLE;
    private TablePPM tablePPM;

    ConstraintLayout constraintLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dle = inflater.inflate(R.layout.fragment_dle, container, false);

        constraintLayout = dle.findViewById(R.id.dle_layout);
        constraintLayout.setOnFocusChangeListener(onFocusChangeListener);

        tablePPM = mainActivity.getForm().getTablePPM();
        tableDLE = mainActivity.getForm().getTableDLE();

        hours = dle.findViewById(R.id.dle_hours_input);
        hours.addTextChangedListener(hoursInput);

        DLE_number = dle.findViewById(R.id.dle_DLE_number);
        toCount = dle.findViewById(R.id.dle_count_button);

        DecimalFormat dF = new DecimalFormat("###.#");

        toCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tableDLE.getTotalHours() != 0) {
                    tableDLE.setGoodSum(tablePPM.getGoodItemsTotal());
                    tableDLE.countDLE();
                    double dle = tableDLE.getDLE();
                    DLE_number.setText(String.format("%s%%", dF.format(dle)));
                }
            }
        });

        return dle;
    }

    private TextWatcher hoursInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String stringIn = s.toString();
            if (stringIn.length() > 1 && stringIn.matches("0+?\\d")) {
                stringIn = stringIn.substring(1);
                Log.d("str ", stringIn);
                hours.removeTextChangedListener(this);
                hours.setText(stringIn);
                hours.setSelection(hours.getText().length());
                hours.addTextChangedListener(this);
            } else if (stringIn.equals(".")) {
                hours.removeTextChangedListener(this);
                hours.setText("");
                hours.addTextChangedListener(this);
            }
                try {
                    double in = Double.parseDouble(s.toString());
                    if (in > 0) {
                        tableDLE.setTotalHours(in);
                    }
                } catch (Exception ignored) {
                    tableDLE.setTotalHours(0);
                }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (hasFocus) {
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE));
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

        }
    };




}

