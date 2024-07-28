package devtime.control;

import java.net.URL;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import devtime.model.*;
import devtime.view.components.SemiCircleChart;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DashboardController implements Initializable {
   HttpHandler http = new HttpHandler();
   @FXML
   private ComboBox<String> selector;
   @FXML
   private Text header;
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
      String projectParam = "";

      if (project != null)
         projectParam = "&project=" + project;

      HttpResponse<String> response = http.http(
         "GET",
         "/activity?api_key=" + new DotEnv().getApiKey() + projectParam,
         null
      );

      if (response != null) {
         ResponseDashboard res = (ResponseDashboard) new JAXB(ResponseDashboard.class).unmarshal(response.body());
         String status = res.getState().substring(0, res.getState().indexOf("/"));

         if (status.equals("success")) {
            Platform.runLater(() -> {
               clear();
               initProjectSelector(res.getProjectNamesContainer());
               initHeader(res.getName(), res.getTime());
               initLanguagesChart(res.getLanguagesContainer());
               initOssChart(res.getOssContainer());
               initProductivityChart(res.getIncrementalPercentage());
               initTimeAreaChart(res.getProjectNamesContainer(), res.getDatesContainer(), project);
            });
         } else
            new MessageManager(res.getState());
      }
   }

   private void initHeader(String name, long time) {
      header.setText(name + "'s coding time: " + String.format("%.1f", convertUnix(time)) + "h");
   }

   private void clear() {
      selector.getItems().clear();
      timechart.getData().clear();
      languages.getData().clear();
      oss.getData().clear();
      header.setText("");
   }

   private void initProjectSelector(ProjectNamesContainer pnc) {
      List<ProjectContainer> projectContainers = pnc.getProjectContainer();

      ObservableList<String> projectsList = FXCollections.observableArrayList();
      projectsList.add("All");

      for (ProjectContainer projectContainer : projectContainers)
         projectsList.add(projectContainer.getProjectName());

      selector.setItems(projectsList);
      selector.setOnAction(this::ui); // when a project is selected start this method
   }

   private void ui(ActionEvent event) {
      String selectedValue = selector.getValue();

      if (selectedValue != null) {
         if (!selectedValue.equals("All"))
            make(selectedValue);
         else
            make(null);
      }
  }

   private void initTimeAreaChart(ProjectNamesContainer pnc, DatesContainer datesContainer, String project) {
      timechart.setTitle("Time x Day");

      for (int i = 0; i < pnc.getProjectContainer().size(); i++) {
         String projectName = pnc.getProjectContainer().get(i).getProjectName();
         ObservableList<XYChart.Data<String, Number>> values = FXCollections.observableArrayList();

         if (project != null && !projectName.equals(project))
            continue;

         XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
         series.setName(projectName); // create a new series for each project

         for (DateContainer date : datesContainer.getDateContainer()) {
            String visualDate = date.getDate();
            boolean found = false;

            for (int j = 0; j < date.getProjectContainer().size(); j++) { // for each date if the pr.name is equal to the project
               if (date.getProjectContainer().get(j).getProjectName().equals(projectName)) {
                  Number visualTime = convertUnix(date.getProjectContainer().get(j).getTime());

                  values.add(
                     new XYChart.Data<String, Number>( // set a new xychart data
                        visualDate,
                        visualTime
                     )
                  );
                  found = true;
               }
            }

            if (!found) {
               values.add(
                  new XYChart.Data<String, Number>( // set a new xychart data
                     visualDate,
                     0
                  )
               );
            }
         }

         series.setData(values);
         timechart.getData().add(series);
      }
   }

   private double convertUnix(long unix) {
      Instant instant = Instant.ofEpochSecond(unix);
      LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

      int hour = localDateTime.getHour();
      int minute = localDateTime.getMinute();

      return hour + (double) minute / 60;
   }

   private void initProductivityChart(double percentage) {
      double realPercentage = Math.abs(percentage - 100);
      boolean increase = false;

      if (percentage >= 100)
         increase = true;

      SemiCircleChart semicircleChart = new SemiCircleChart(realPercentage, increase);

      productivity.getChildren().add(semicircleChart);
   }

   private void initOssChart(OssContainer ossContainer) {
      oss.setTitle("Operating Systems");
      ObservableList<PieChart.Data> ossData = FXCollections.observableArrayList();

      for (OsContainer os : ossContainer.getOsContainer())
         ossData.add(new PieChart.Data(os.getOsName(), os.getPercentage()));

      oss.setData(ossData);
   }

   private void initLanguagesChart(LanguagesContainer languagesContainer) {
      languages.setTitle("Languages");
      ObservableList<PieChart.Data> languagesData = FXCollections.observableArrayList();

      for (LanguageContainer language : languagesContainer.getLanguageContainer())
         languagesData.add(new PieChart.Data(language.getLanguageName(), language.getPercentage()));

      languages.setData(languagesData);
   }
}
