package timecode.control.auth;

import java.net.http.HttpResponse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timecode.model.HttpHandler;
import timecode.view.App;
import timecode.view.components.PopUp;

public class LoginController {
   private Stage stage = App.getStage();
   private HttpHandler http = new HttpHandler();
   @FXML
   private TextField email;
   @FXML
   private TextField password;

   @FXML
   private void login() {
      String emailField = email.getText();
      String passwordField = password.getText();

      if (passwordField.length() < 8)
         new PopUp("Password is too weak, at least 8 character", stage).show(stage);

      //TODO: xml parser
      String content = "";

      HttpResponse<String> response = http.http(
         "POST",
         "/login",
         content
      );

      //xsd validation
      // System.out.println(response.body());
   }

   @FXML
   private void switchToSignUp() {
      App.setScene("/ui/signup");
   }

}
