package timecode.control.auth;

import java.net.http.HttpResponse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import timecode.model.local.DotEnv;
import timecode.model.local.MessageManager;
import timecode.model.net.HttpHandler;
import timecode.model.net.JAXB;
import timecode.model.requests.SignUp;
import timecode.model.responses.ResponseAuth;
import timecode.view.App;

public class SignUpController {
   private HttpHandler http = new HttpHandler();
   @FXML
   private TextField email;
   @FXML
   private TextField password;
   @FXML
   private TextField name;
   @FXML
   private TextField surname;

   @FXML
   private void signup() {
      String emailField = email.getText();
      String passwordField = password.getText();
      String nameField = name.getText();
      String surnameField = surname.getText();


      SignUp signup = new SignUp(nameField, surnameField, emailField, passwordField);
      String xml = new JAXB(SignUp.class).marshal(signup); // marshal the request

      HttpResponse<String> response = http.http( // send the request
         "POST",
         "/auth/signup",
         xml
      );

      if (response != null) {
         ResponseAuth res = (ResponseAuth) new JAXB(ResponseAuth.class).unmarshal(response.body()); // unmarshal the response
         String status = res.getState().substring(0, res.getState().indexOf("/")); // split the response state

         if (status.equals("success"))
            new DotEnv().setApiKey(res.getApiKey()); // save the api key in the .env file
         else
            new MessageManager(res.getState());
      }
   }

   @FXML
   private void switchToLogin() {
      App.setScene("/ui/login"); // switch ui
   }
}
