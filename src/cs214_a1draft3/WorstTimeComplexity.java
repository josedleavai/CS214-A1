package cs214_a1draft3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class WorstTimeComplexity extends Application {

    @Override
    public void start(Stage stage) {
        // Create axes
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Input Size");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Time Taken (ms)");

        // Create line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Worst-case Time Complexity Graph");

        // Prepare data series
        XYChart.Series<Number, Number> linearSeries = new XYChart.Series<>();
        linearSeries.setName("Linear Search");

        XYChart.Series<Number, Number> binarySeries = new XYChart.Series<>();
        binarySeries.setName("Binary Search");

        XYChart.Series<Number, Number> sentinelSeries = new XYChart.Series<>();
        sentinelSeries.setName("Sentinel Search");

        XYChart.Series<Number, Number> jumpSeries = new XYChart.Series<>();
        jumpSeries.setName("Jump Search");

        // Add worst-case time complexity data points for each algorithm (replace these
        // with your data)
        linearSeries.getData().add(new XYChart.Data<>(100, 100)); // Example linear worst-case time complexity
        linearSeries.getData().add(new XYChart.Data<>(200, 200));
        linearSeries.getData().add(new XYChart.Data<>(300, 300));

        binarySeries.getData().add(new XYChart.Data<>(100, 400)); // Example binary worst-case time complexity
        binarySeries.getData().add(new XYChart.Data<>(200, 800));
        binarySeries.getData().add(new XYChart.Data<>(300, 1200));

        sentinelSeries.getData().add(new XYChart.Data<>(100, 200)); // Example sentinel linear worst-case time
                                                                    // complexity
        sentinelSeries.getData().add(new XYChart.Data<>(200, 400));
        sentinelSeries.getData().add(new XYChart.Data<>(300, 600));

        jumpSeries.getData().add(new XYChart.Data<>(100, 150)); // Example jump search worst-case time complexity
        jumpSeries.getData().add(new XYChart.Data<>(200, 300));
        jumpSeries.getData().add(new XYChart.Data<>(300, 450));

        // Add data series to the chart
        lineChart.getData().addAll(linearSeries, binarySeries, sentinelSeries, jumpSeries);

        // Create and set up the scene
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Worst-case Time Complexity Visualization");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
