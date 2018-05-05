package com.appdev.debsourav.childtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual, buttonClock;
    EditText EditText1, EditTextSign, EditText2, SetHour, SetMinute;

    float mValueOne, mValueTwo;

    boolean Addition, Subtract, Multiplication, Division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        setTitle("Calculator");

        button0 =  findViewById(R.id.button0);
        button1 =  findViewById(R.id.button1);
        button2 =  findViewById(R.id.button2);
        button3 =  findViewById(R.id.button3);
        button4 =  findViewById(R.id.button4);
        button5 =  findViewById(R.id.button5);
        button6 =  findViewById(R.id.button6);
        button7 =  findViewById(R.id.button7);
        button8 =  findViewById(R.id.button8);
        button9 =  findViewById(R.id.button9);
        button10 =  findViewById(R.id.button10);

        buttonAdd =  findViewById(R.id.buttonadd);
        buttonSub =  findViewById(R.id.buttonsub);
        buttonMul =  findViewById(R.id.buttonmul);
        buttonDivision =  findViewById(R.id.buttondiv);
        buttonC =  findViewById(R.id.buttonC);
        buttonEqual =  findViewById(R.id.buttoneql);

        EditText1 =  findViewById(R.id.edt1);
        EditTextSign = findViewById(R.id.sign);
        EditText2 = findViewById(R.id.edt2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "1");
                }
                else    EditText2.setText(EditText2.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "2");
                }
                else    EditText2.setText(EditText2.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "3");
                }
                else    EditText2.setText(EditText2.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "4");
                }
                else    EditText2.setText(EditText2.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "5");
                }
                else    EditText2.setText(EditText2.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "6");
                }
                else    EditText2.setText(EditText2.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "7");
                }
                else    EditText2.setText(EditText2.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "8");
                }
                else    EditText2.setText(EditText2.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "9");
                }
                else    EditText2.setText(EditText2.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + "0");
                }
                else    EditText2.setText(EditText2.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EditText1 == null) {
                    EditText1.setText("");
                } else {
                    mValueOne = Float.parseFloat(EditText1.getText() + "");
                    Addition = true;
                    EditTextSign.setText("+");
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(EditText1.getText() + "");
                Subtract = true;
                EditTextSign.setText("-");
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(EditText1.getText() + "");
                Multiplication = true;
                EditTextSign.setText("*");
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(EditText1.getText() + "");
                Division = true;
                EditTextSign.setText("/");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(EditText2.getText() + "");

                if (Addition) {
                    EditText1.setText(mValueOne + mValueTwo + "");
                    EditTextSign.setText("");
                    EditText2.setText(null);
                    Addition = false;
                }

                if (Subtract) {
                    EditText1.setText(mValueOne - mValueTwo + "");
                    EditTextSign.setText("");
                    EditText2.setText(null);
                    Subtract = false;
                }

                if (Multiplication) {
                    EditText1.setText(mValueOne * mValueTwo + "");
                    EditTextSign.setText("");
                    EditText2.setText(null);
                    Multiplication = false;
                }

                if (Division) {
                    EditText1.setText(mValueOne / mValueTwo + "");
                    EditTextSign.setText("");
                    EditText2.setText(null);
                    Division = false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText1.setText("");
                EditText2.setText("");
                EditTextSign.setText("");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Addition && !Subtract && !Multiplication && !Division) {
                    EditText1.setText(EditText1.getText() + ".");
                }
                else    EditText2.setText(EditText2.getText() + ".");
            }
        });

    }
}
