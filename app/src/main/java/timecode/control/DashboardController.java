package timecode.control;

import java.net.URL;
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
import javafx.scene.text.Text;

public class DashboardController implements Initializable {
   @FXML
   private ComboBox<String> projectselector;
   @FXML
   private Text name;
   @FXML
   private PieChart languages;
   @FXML
   private PieChart oss;
   @FXML
   private AreaChart<String, Number> projecttime;

   @Override
   public void initialize(URL u, ResourceBundle r) {
      initProjectSelector();
      initLanguagesChart();
      initOssChart();
      initIncrementChart();
      initProjectTimeAreaChart();
   }

   private void initProjectTimeAreaChart() {
      NumberAxis x = new NumberAxis(1, 31, 1);
      x.setLabel("Day");

      NumberAxis y = new NumberAxis();
      y.setLabel("Time");

      XYChart.Series<String, Number> ex = new XYChart.Series<String, Number>();

      ex.setName("Example");
      IntStream.range(1, 31)
         .forEach(i -> ex.getData().add(
            new XYChart.Data<String, Number>("esempio" + i, i * 10) // Assuming you want to multiply i by 10 for each value
         ));

      XYChart.Series<String, Number> ex2 = new XYChart.Series<String, Number>();

      ex2.setName("Example2");
      IntStream.range(1, 31)
         .forEach(i -> ex2.getData().add(
            new XYChart.Data<String, Number>("esempio" + i, i * 11) // Assuming you want to multiply i by 10 for each value
         ));

      projecttime.getData().addAll(ex, ex2);
   }

   private void initIncrementChart() {
      // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method 'initIncrementChart'");
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
      projectselector.getItems().addAll(projects);
      projectselector.setOnAction(this::updateHeader);
   }

   private void updateHeader(ActionEvent event) {
      String selectedProject = projectselector.getValue();
		name.setText(selectedProject);
   }
}
