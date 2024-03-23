package timecode.control.auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import timecode.model.local.MessageManager;
import timecode.model.net.HttpHandler;
import timecode.model.net.JAXB;
import timecode.model.requests.SignUp;
import timecode.model.responses.ResSignUp;
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

      try {
         SignUp signup = new SignUp(nameField, surnameField, emailField, passwordField);
         String xml = new JAXB(SignUp.class).marshal(signup);
         HttpResponse<String> response = http.http(
            "POST",
            "/auth/signup",
            xml
         );

         ResSignUp res = (ResSignUp) new JAXB(ResSignUp.class).unmarshal(response.body());
         String status = res.getState().substring(0, res.getState().indexOf("/"));

         if (status.equals("success")) {
            try {
               File key = new File("app/.env.local");
               FileWriter w = new FileWriter(key);
               w.write("USER = " + res.getIdUser().toString());
               w.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         } else
            new MessageManager(res.getState());

      } catch (URISyntaxException | IOException | InterruptedException e) {
         new MessageManager("error/connection");
      } catch (JAXBException e) {
         new MessageManager("error/parsing");
      }
   }

   @FXML
   private void switchToLogin() {
      App.setScene("/ui/login");
   }
}
