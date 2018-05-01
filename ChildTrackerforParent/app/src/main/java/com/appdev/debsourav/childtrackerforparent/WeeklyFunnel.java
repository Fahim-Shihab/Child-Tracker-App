package com.appdev.debsourav.childtrackerforparent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Funnel;

public class WeeklyFunnel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_funnel);

        AnyChartView anyChartView = findViewById(R.id.funn);

        Funnel funnel = AnyChart.funnel();

        funnel.setData(ChartData.data);

        funnel.setMargin(new String[] { "10", "20%", "10", "20%" });
        funnel.setBaseWidth("70%")
                .setNeckWidth("17%");

        funnel.getLabels()
                .setPosition("outsideleft")
                .setFormat("{%X} - {%Value}");

        funnel.setAnimation(true);

        anyChartView.setChart(funnel);
    }
}
