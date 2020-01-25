package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * GUI to present GA algorithms on line charts
 */
public class GUI extends Application {
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * Presented maximum fitness values
     */
    public static List<Double> optValues = new ArrayList<>();
    public static int iteration = 0;
    /**
     * Thread list which is used for running algorithms
     */
    private List<Thread> threads = new ArrayList<>();
    private List<XYChart.Series<String, Number>> series = new ArrayList<>();
    private boolean isPause = false;
    private boolean isRun = false;
    private boolean isStop = false;

    private LineChart<String,Number> lineChart;
    private LineChart<String,Number> lineChart2;
    private LineChart<String,Number> lineChart3;


    public static void main(String[] args) {

        for(int i = 0; i<3; ++i )
            optValues.add(0.0);
        launch(args);
    }

    /**
     * Draw charts with given data of series
     */
    private void drawCharts(){

        if(series.isEmpty()){
            for(int i = 0; i<3; ++i )
                series.add(new XYChart.Series<>());


            for(XYChart.Series<String, Number> i : series)
                i.setName("Maximum value");

            // add series to chart
            lineChart.getData().add(series.get(0));
            lineChart2.getData().add(series.get(1));
            lineChart3.getData().add(series.get(2));
        }

    }

    /**
     * Starts GUI
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Alert a = new Alert(Alert.AlertType.WARNING);

        primaryStage.setTitle("Genetic Algorithm");

        lineChart = createLineChart("Version 1");
        lineChart2 = createLineChart("Version 2");
        lineChart3 = createLineChart("Version 3");


        drawCharts();


        FlowPane root = new FlowPane();
        Button run = new Button("Run");
        Button pause = new Button("Pause");
        Button stop = new Button("Stop");


        EventHandler<ActionEvent> event = e -> {

            if(isRun){
                a.setContentText("Algorithms have already running");
                a.show();
            }
            else{
                isRun = true;
                isStop = false;
                drawCharts();
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                runAlgorithms();
                startSchedule();

            }

        };

        EventHandler<ActionEvent> event2 = e -> {
            pauseAlgorithms();
            isPause = true;
            isRun = false;
            isStop = false;
            scheduledExecutorService.shutdownNow();
        };

        EventHandler<ActionEvent> event3 = e -> {
            if(isStop){
                a.setContentText("Algorithms have already stopped");
                a.show();
            }
            else{
                isStop = true;
                isRun = false;
                stopAlgorithms();
                clearCharts();
                scheduledExecutorService.shutdownNow();
            }

        };

        run.setOnAction(event);
        pause.setOnAction(event2);
        stop.setOnAction(event3);


        root.getChildren().addAll(lineChart, lineChart2,lineChart3,run,pause,stop);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Terminates the GUI
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        if(scheduledExecutorService != null)
            scheduledExecutorService.shutdownNow();

        for(Thread thread : threads)
            thread.stop();
    }

    /**
     * Configure of Axis X
     * @return Configured axis x
     */
    private CategoryAxis getXAxis(){
        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        xAxis.setLabel("Iteration");
        xAxis.setAnimated(false); // axis animations are removed

        return xAxis;
    }

    /**
     * Configure of Axis Y
     * @return Configured axis y
     */
    private NumberAxis getYAxis(){
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");
        yAxis.setAnimated(false); // axis animations are removed

        return yAxis;
    }

    /**
     * Generate line chart and configures it
     * @param title title of chart
     * @return Configured chart
     */
    private LineChart<String,Number> createLineChart(String title){

        //creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(getXAxis(), getYAxis());
        lineChart.setTitle(title);
        lineChart.setAnimated(true);

        return lineChart;
    }

    /**
     * Generates 3 threads and run each algorithm on them
     */
    private void runAlgorithms(){

        if(isPause){
            for(Thread thread : threads)
                thread.resume();
            isPause = false;
        }
        else{
            List<GeneticAlgorithm> geneticAlgorithms = new ArrayList<>();
            geneticAlgorithms.add(new AlgorithmVersion1());
            geneticAlgorithms.add(new AlgorithmVersion2());
            geneticAlgorithms.add(new AlgorithmVersion3());

            for(GeneticAlgorithm geneticAlgorithm : geneticAlgorithms){
                Thread thread = new Thread(geneticAlgorithm::optimizeFunction);
                threads.add(thread);
                thread.start();
            }
        }
    }

    /**
     * Stops threads to terminate algorithms
     */
    private void stopAlgorithms(){
        for(Thread thread : threads)
            thread.stop();

        threads.clear();
    }

    /**
     * Clear data of charts
     */
    private void clearCharts(){
        lineChart.getData().clear();
        lineChart2.getData().clear();
        lineChart3.getData().clear();

        series.clear();

        for(int i = 0; i<3; ++i )
            optValues.set(i,0.0);

        iteration = 0;
    }

    /**
     * Suspends threads to re-run algorithms next time
     */
    private void pauseAlgorithms() {
        for(Thread thread : threads) {
            try {
                thread.suspend();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Use scheduledExecutorService to real time GUI update
     */
    private void startSchedule(){

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // Update the chart
            Platform.runLater(() -> {

                for(int i = 0; i<3; ++i )
                    series.get(i).getData().add(new XYChart.Data<>(String.valueOf(iteration / 3), optValues.get(i)));

                for(int i = 0; i<3; ++i )
                    if (series.get(i).getData().size() > 10)
                        series.get(i).getData().remove(0);
            });
        }, 0, 1, TimeUnit.SECONDS);
    }
}
