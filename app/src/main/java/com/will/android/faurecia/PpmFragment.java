package com.will.android.faurecia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;;import java.util.HashMap;
import java.util.Map;


public class PpmFragment extends Fragment {
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

    private TextView onResultPPM;
    private Button toCount;

    TablePPM mTablePPM = MainActivity.mForm.getTablePPM();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ppm = inflater.inflate(R.layout.fragment_ppm, container, false);

        onResultPPM = ppm.findViewById(R.id.PPM_result);
        toCount = ppm.findViewById(R.id.PPM_count_button);
        toCount.setEnabled(false);

        toCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTablePPM.countPPM();
                String result = "PPM = " + mTablePPM.getPPM();
                onResultPPM.setText(String.valueOf(result));
            }
        });


        frontLeftNormal = ppm.findViewById(R.id.front_left_normal);
        frontLeftNormal.addTextChangedListener(mFrontLeftNormal);

        frontLeftDefect = ppm.findViewById(R.id.front_left_defect);
        frontLeftDefect.addTextChangedListener(mFrontLeftDefect);

        frontLeftTotal = ppm.findViewById(R.id.front_left_total);


        frontRightNormal = ppm.findViewById(R.id.front_right_normal);
        frontRightNormal.addTextChangedListener(mFrontRightNormal);

        frontRightDefect = ppm.findViewById(R.id.front_right_defect);
        frontRightDefect.addTextChangedListener(mFrontRightDefect);

        frontRightTotal = ppm.findViewById(R.id.front_right_total);


        backLeftNormal = ppm.findViewById(R.id.back_left_normal);
        backLeftNormal.addTextChangedListener(mBackLeftNormal);

        backLeftDefect = ppm.findViewById(R.id.back_left_defect);
        backLeftDefect.addTextChangedListener(mBackLeftDefect);

        backLeftTotal = ppm.findViewById(R.id.back_left_total);


        backRightNormal = ppm.findViewById(R.id.back_right_normal);
        backRightNormal.addTextChangedListener(mBackRightNormal);

        backRightDefect = ppm.findViewById(R.id.back_right_defect);
        backRightDefect.addTextChangedListener(mBackRightDefect);

        backRightTotal = ppm.findViewById(R.id.back_right_total);

        return ppm;
    }


    TextWatcher mFrontLeftNormal = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.frontLeft.setGoodItemsNumber(Integer.parseInt(s.toString()));

                if (frontLeftDefect.length() != 0) {
                    int total = mTablePPM.frontLeft.getGoodItemsNumber()
                            + mTablePPM.frontLeft.getDefectiveItemsNumber();

                    frontLeftTotal.setText(String.valueOf(total));
                    mTablePPM.frontLeft.setTotalItemsNumber(total);
                    checkIfTableIsFilled();
                    Log.d("total", String.valueOf(total));
                }

            } catch (Exception e) {
                mTablePPM.frontLeft.setGoodItemsNumber(0);
                frontLeftTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher mFrontLeftDefect = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.frontLeft.setDefectiveItemsNumber(Integer.parseInt(s.toString()));

                if (frontLeftNormal.length() != 0) {
                    int total = mTablePPM.frontLeft.getGoodItemsNumber()
                            + mTablePPM.frontLeft.getDefectiveItemsNumber();

                    frontLeftTotal.setText(String.valueOf(total));
                    mTablePPM.frontLeft.setTotalItemsNumber(total);
                    checkIfTableIsFilled();
                    Log.d("total", String.valueOf(total));
                }

            } catch (Exception e) {
                mTablePPM.frontLeft.setDefectiveItemsNumber(0);
                frontLeftTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher mFrontRightNormal = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.frontRight.setGoodItemsNumber(Integer.parseInt(s.toString()));

                if (frontRightDefect.length() != 0) {
                    int total = mTablePPM.frontRight.getGoodItemsNumber()
                            + mTablePPM.frontRight.getDefectiveItemsNumber();

                    Log.d("front right total", String.valueOf(total));
                    mTablePPM.frontRight.setTotalItemsNumber(total);
                    frontRightTotal.setText(String.valueOf(total));
                    checkIfTableIsFilled();
                }

            } catch (Exception e) {
                mTablePPM.frontRight.setGoodItemsNumber(0);
                frontRightTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher mFrontRightDefect = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.frontRight.setDefectiveItemsNumber(Integer.parseInt(s.toString()));

                if (frontLeftNormal.length() != 0) {
                    int total = mTablePPM.frontRight.getGoodItemsNumber()
                            + mTablePPM.frontRight.getDefectiveItemsNumber();

                    Log.d("front right total 2", String.valueOf(total));
                    mTablePPM.frontRight.setTotalItemsNumber(total);
                    frontRightTotal.setText(String.valueOf(total));
                    checkIfTableIsFilled();
                }

            } catch (Exception e) {
                mTablePPM.frontRight.setDefectiveItemsNumber(0);
                frontRightTotal.setText("");
                checkIfTableIsFilled();
            }
        }
        
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher mBackLeftNormal = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.backLeft.setGoodItemsNumber(Integer.parseInt(s.toString()));

                if (backLeftDefect.length() != 0) {
                    int total = mTablePPM.backLeft.getGoodItemsNumber()
                            + mTablePPM.backLeft.getDefectiveItemsNumber();

                    mTablePPM.backLeft.setTotalItemsNumber(total);
                    backLeftTotal.setText(String.valueOf(total));
                    checkIfTableIsFilled();
                }

            } catch (Exception e) {
                mTablePPM.backLeft.setGoodItemsNumber(0);
                backLeftTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    TextWatcher mBackLeftDefect = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.backLeft.setDefectiveItemsNumber(Integer.parseInt(s.toString()));

                if (backLeftNormal.length() != 0) {
                    int total = mTablePPM.backLeft.getGoodItemsNumber()
                            + mTablePPM.backLeft.getDefectiveItemsNumber();

                    mTablePPM.backLeft.setTotalItemsNumber(total);
                    backLeftTotal.setText(String.valueOf(total));
                    checkIfTableIsFilled();
                }

            } catch (Exception e) {
                mTablePPM.backLeft.setDefectiveItemsNumber(0);
                backLeftTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher mBackRightNormal = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.backRight.setGoodItemsNumber(Integer.parseInt(s.toString()));

                if (backRightDefect.length() != 0) {
                    int total = mTablePPM.backRight.getGoodItemsNumber()
                            + mTablePPM.backRight.getDefectiveItemsNumber();

                    mTablePPM.backRight.setTotalItemsNumber(total);
                    backRightTotal.setText(String.valueOf(total));
                    checkIfTableIsFilled();
                }

            } catch (Exception e) {
                mTablePPM.backRight.setGoodItemsNumber(0);
                backRightTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher mBackRightDefect = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                mTablePPM.backRight.setDefectiveItemsNumber(Integer.parseInt(s.toString()));

                if (backRightNormal.length() != 0) {
                    int total = mTablePPM.backRight.getGoodItemsNumber()
                            + mTablePPM.backRight.getDefectiveItemsNumber();

                    mTablePPM.backRight.setTotalItemsNumber(total);
                    backRightTotal.setText(String.valueOf(total));
                    checkIfTableIsFilled();
                }

            } catch (Exception e) {
                mTablePPM.backRight.setDefectiveItemsNumber(0);
                backRightTotal.setText("");
                checkIfTableIsFilled();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    void checkIfTableIsFilled() {
        toCount.setEnabled(frontLeftNormal.length() != 0
                && frontRightDefect.length() != 0
                && frontRightNormal.length() != 0
                && frontRightDefect.length() != 0
                && backLeftNormal.length() != 0
                && backLeftDefect.length() != 0
                && backRightNormal.length() != 0
                && backRightDefect.length() != 0);
    }

}




