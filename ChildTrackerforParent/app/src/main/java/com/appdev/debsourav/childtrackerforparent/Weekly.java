package com.appdev.debsourav.childtrackerforparent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Pie;
import com.google.firebase.database.DatabaseReference;

public class Weekly extends AppCompatActivity {

    DatabaseReference WeeklyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        setTitle("Weekly App Usage");

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);

        Pie pie = AnyChart.pie();

        pie.setData(MainActivity.wdata);

        anyChartView.setChart(pie);
    }
}
