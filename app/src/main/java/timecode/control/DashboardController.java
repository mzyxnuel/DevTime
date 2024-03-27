package timecode.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class DashboardController implements Initializable {
   @FXML
   private ComboBox<String> projectselector;
   @FXML
   private Text name;
   @FXML
   private Text time;

   @Override
   public void initialize(URL u, ResourceBundle r) {
      String[] projects = {"p1","p2","p3"};
      projectselector.getItems().addAll(projects);

      //get req for all projects names
      //get req for the author name

		projectselector.setOnAction(this::updateHeader);
   }

   private void updateHeader(ActionEvent event) {
      String selectedProject = projectselector.getValue();
		name.setText(selectedProject);
   }
}
