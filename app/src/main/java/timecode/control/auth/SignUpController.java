package timecode.control.auth;

import javafx.fxml.FXML;
import timecode.view.App;

public class SignUpController {

   @FXML
   private void switchToLogin() {
      App.setScene("/ui/login");
   }
}
