package timecode.control.auth;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import timecode.model.local.DotEnv;
import timecode.model.local.MessageManager;
import timecode.model.net.HttpHandler;
import timecode.model.net.JAXB;
import timecode.model.requests.Login;
import timecode.model.responses.ResponseAuth;
import timecode.view.App;

public class LoginController {
   private HttpHandler http = new HttpHandler();
   @FXML
   private TextField email;
   @FXML
   private TextField password;

   @FXML
   private void login() {
      String emailField = email.getText();
      String passwordField = password.getText();

      try {
         Login login = new Login(emailField, passwordField);
         String xml = new JAXB(Login.class).marshal(login);
         HttpResponse<String> response = http.http(
            "POST",
            "/auth/login",
            xml
         );

         ResponseAuth res = (ResponseAuth) new JAXB(ResponseAuth.class).unmarshal(response.body());
         String status = res.getState().substring(0, res.getState().indexOf("/"));

         if (status.equals("success"))
            new DotEnv().setApiKey(res.getApiKey());
         else
            new MessageManager(res.getState());
      } catch (URISyntaxException | IOException | InterruptedException e) {
         new MessageManager("error/connection");
      } catch (JAXBException e) {
         new MessageManager("error/parsing");
      }
   }

   @FXML
   private void switchToSignUp() {
      App.setScene("/ui/signup"); // switch ui
   }

}
