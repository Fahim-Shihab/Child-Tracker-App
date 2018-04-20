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

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);

        /*Cartesian cartesian = AnyChart.column();

        CartesianSeriesColumn column = cartesian.column(MainActivity.data);

        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setAnchor(EnumsAnchor.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d)
                .setFormat("{%Value}{groupsSeparator: }");

        cartesian.setAnimation(true);
        cartesian.setTitle("Weekly Update");

        cartesian.getYScale().setMinimum(0d);

        cartesian.getYAxis().getLabels().setFormat("{%Value}{groupsSeparator: }");

        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

        cartesian.getXAxis().setTitle("Time in Minutes");
        cartesian.getYAxis().setTitle("App");

        anyChartView.setChart(cartesian);*/

        Pie pie = AnyChart.pie();

        pie.setData(MainActivity.data);

        anyChartView.setChart(pie);
    }
}
