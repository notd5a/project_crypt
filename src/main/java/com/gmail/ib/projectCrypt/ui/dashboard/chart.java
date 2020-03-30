package com.gmail.ib.projectCrypt.ui.dashboard;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.config.xaxis.XAxisType;
import com.github.appreciated.apexcharts.helper.Series;
import com.gmail.ib.projectCrypt.backend.cryptData.SQLConnector;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class chart extends VerticalLayout {

    public chart(AtomicReference<String> symbol) throws SQLException {


        Object[] arr = new Object[1];
        String[] dates = new String[1];
        SQLConnector connect = new SQLConnector();
        Statement stmt = connect.SQLConnection();
        /* Put any currency you want where it says BTC grab the value from the search */
        String extract = "SELECT * FROM historical_data WHERE symbol = '"+symbol+"'";
        ResultSet rs = stmt.executeQuery(extract);
        int a = 0;
        while (rs.next()){
            /*Adding to arrays from resultset*/
            arr[a] = rs.getDouble("price");
            dates[a] = rs.getString("date");
            /* appending to array */
            arr = Arrays.copyOf(arr, arr.length + 1);
            dates = Arrays.copyOf(dates, dates.length +1);
            a++;
        }
        arr = Arrays.copyOf(arr, arr.length - 1);
        dates = Arrays.copyOf(dates, dates.length -1);
        Series s = new Series(String.valueOf(symbol).toUpperCase());
        s.setData(arr);
        ApexCharts areaChart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.area)
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(false)
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false)
                        .build())
                .withStroke(StrokeBuilder.get().withCurve(Curve.straight).build())
                .withSeries(s)
                .withTitle(TitleSubtitleBuilder.get()
                        .withText(symbol.toString().toUpperCase()+" Graph")
                        .withAlign(Align.left).build())
                .withSubtitle(TitleSubtitleBuilder.get()
                        .withText("Price Movements")
                        .withAlign(Align.left).build())
                .withLabels(dates)
                .withXaxis(XAxisBuilder.get().withType(XAxisType.datetime).build())
                .withYaxis(YAxisBuilder.get()
                        .withOpposite(true).withMin(0).build())
                .withLegend(LegendBuilder.get().withHorizontalAlign(HorizontalAlign.left).build())
                .build();
        add(areaChart);
        setWidth("100%");
    }
}




