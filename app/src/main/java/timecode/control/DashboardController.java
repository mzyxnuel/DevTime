package timecode.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import timecode.view.components.SemiCircleChart;

public class DashboardController implements Initializable {
   @FXML
   private ComboBox<String> selector;
   @FXML
   private Text name;
   @FXML
   private PieChart languages;
   @FXML
   private PieChart oss;
   @FXML
   private AreaChart<String, Number> timechart;
   @FXML
   private Pane productivity;

   @Override
   public void initialize(URL u, ResourceBundle r) {
      initProjectSelector();
      initLanguagesChart();
      initOssChart();
      initProductivityChart();
      initTimeAreaChart();
   }

   private void initTimeAreaChart() {
      timechart.setTitle("Hours per day");
      NumberAxis x = new NumberAxis(1, 31, 1);
      x.setLabel("Day");

      NumberAxis y = new NumberAxis();
      y.setLabel("Time");

      XYChart.Series<String, Number> ex = new XYChart.Series<String, Number>();

      ex.setName("Example");
      IntStream.range(1, 31)
         .forEach(i -> ex.getData().add(
            new XYChart.Data<String, Number>("" + i, i * 10) // Assuming you want to multiply i by 10 for each value
         ));

      XYChart.Series<String, Number> ex2 = new XYChart.Series<String, Number>();

      ex2.setName("Example2");
      IntStream.range(1, 31)
         .forEach(i -> ex2.getData().add(
            new XYChart.Data<String, Number>("" + i, i * 11) // Assuming you want to multiply i by 10 for each value
         ));

      timechart.getData().addAll(ex, ex2);
   }

   private void initProductivityChart() {
      List<SemiCircleChart.Data> dataList = new ArrayList<>();
      dataList.add(new SemiCircleChart.Data("data1", 1, Color.RED));
      dataList.add(new SemiCircleChart.Data("data2", 2, Color.GREEN));
      dataList.add(new SemiCircleChart.Data("data3", 3, Color.BLUE));

      SemiCircleChart semicircleChart = new SemiCircleChart(dataList);
      productivity.getChildren().add(semicircleChart);
   }

   private void initOssChart() {
      oss.setTitle("Operating Systems");
      ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
            new PieChart.Data("A", 10),
            new PieChart.Data("B", 30),
            new PieChart.Data("C", 100)
         );

      oss.setData(data);
   }

   private void initLanguagesChart() {
      languages.setTitle("Languages");
      ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
            new PieChart.Data("A", 10),
            new PieChart.Data("B", 30),
            new PieChart.Data("C", 100)
         );

      languages.setData(data);
   }

   private void initProjectSelector() {
      String[] projects = {"p1","p2","p3"};
      selector.getItems().addAll(projects);
      selector.setOnAction(this::updateHeader);
   }

   private void updateHeader(ActionEvent event) {
      String selectedProject = selector.getValue();
		name.setText(selectedProject);
   }
}
