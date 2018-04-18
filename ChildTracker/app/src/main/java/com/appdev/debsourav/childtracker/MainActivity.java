package com.appdev.debsourav.childtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual, buttonClock;
    EditText EditText1, EditTextSign, EditText2, SetHour, SetMinute;

    float mValueOne, mValueTwo;

    boolean Addition, Subtract, Multiplication, Division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startService(new Intent(this, ChildService.class));

        SetHour = findViewById(R.id.Hour);
        SetMinute = findViewById(R.id.Minute);

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
        buttonClock = findViewById(R.id.alarm);

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

        final Switch AMPM = findViewById(R.id.switch1);
        if(AMPM.isChecked()){
            AMPM.setText("");
            AMPM.setText("PM  ");
        }

        buttonClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                String h = SetHour.getText().toString();
                int hour = Integer.parseInt(h);
                String m = SetMinute.getText().toString();
                int minute = Integer.parseInt(m);

                SetHour.setText("");
                SetMinute.setText("");

                if(AMPM.isChecked())    hour+=12;

                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, minute, 0);

                setAlarm(calendar.getTimeInMillis());

            }
        });
        /*Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);*/
    }
    private void setAlarm(long time) {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, MyReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
    }
}
