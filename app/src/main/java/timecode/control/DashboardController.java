package timecode.control;

import java.net.URL;
import java.net.http.HttpResponse;
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
import javafx.scene.text.Text;
import timecode.model.local.DotEnv;
import timecode.model.local.MessageManager;
import timecode.model.net.HttpHandler;
import timecode.model.net.JAXB;
import timecode.model.responses.ResponseDashboard;
import timecode.model.responses.ResponseDashboard.DatesContainer;
import timecode.model.responses.ResponseDashboard.LanguagesContainer;
import timecode.model.responses.ResponseDashboard.OssContainer;
import timecode.model.responses.ResponseDashboard.ProjectNamesContainer;
import timecode.view.components.SemiCircleChart;

public class DashboardController implements Initializable {
   HttpHandler http = new HttpHandler();
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
   public void initialize(URL u, ResourceBundle r) { make(null); }

   private void make(String project) {
      HttpResponse<String> response = http.http(
         "GET",
         "/activity?api_key=" + new DotEnv().getApiKey() + "&project=" + project,
         null
      );

      if (response != null) {
         ResponseDashboard res = (ResponseDashboard) new JAXB(ResponseDashboard.class).unmarshal(response.body());
         String status = res.getState().substring(0, res.getState().indexOf("/"));

         if (status.equals("success")) {
            initProjectSelector(res.getProjectNamesContainer());
            initLanguagesChart(res.getLanguagesContainer());
            initOssChart(res.getOssContainer());
            initProductivityChart(res.getIncrementalPercentage());
            initTimeAreaChart(res.getDatesContainer());
         } else
            new MessageManager(res.getState());
      }
   }

   private void initProjectSelector(ProjectNamesContainer pnc) {
      String[] projects = {"All"};
      for (int i = 1; i < pnc.getProjectContainer().size(); i++)
         projects[i] = pnc.getProjectContainer().get(i).getProjectName();

      selector.getItems().addAll(projects);
      selector.setOnAction(this::ui);
   }

   private void ui(ActionEvent event) {
      if (!selector.getValue().equals("All"))
         make(selector.getValue());
      else
         make(null);
   }

   private void initTimeAreaChart(DatesContainer datesContainer) {
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

   private void initProductivityChart(double d) {
      List<SemiCircleChart.Data> dataList = new ArrayList<>();
      dataList.add(new SemiCircleChart.Data(70));

      SemiCircleChart semicircleChart = new SemiCircleChart(dataList, 70, false);
      productivity.getChildren().add(semicircleChart);
   }

   private void initOssChart(OssContainer ossContainer) {
      oss.setTitle("Operating Systems");
      ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
            new PieChart.Data("A", 10),
            new PieChart.Data("B", 30),
            new PieChart.Data("C", 100)
         );

      oss.setData(data);
   }

   private void initLanguagesChart(LanguagesContainer languagesContainer) {
      languages.setTitle("Languages");
      ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
            new PieChart.Data("A", 10),
            new PieChart.Data("B", 30),
            new PieChart.Data("C", 100)
         );

      languages.setData(data);
   }
}
