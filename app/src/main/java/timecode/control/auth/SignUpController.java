package timecode.control.auth;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

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

         if (status.equals("success"))
            saveUserId(res.getIdUser());
         else
            new MessageManager(res.getState());

      } catch (URISyntaxException | IOException | InterruptedException e) {
         new MessageManager("error/connection");
      } catch (JAXBException e) {
         new MessageManager("error/parsing");
      }
   }

   public void saveUserId(BigInteger userId) throws IOException {
      File key = new File("app/.env");
      List<String> lines = Files.readAllLines(key.toPath(), StandardCharsets.UTF_8);
      boolean userExists = false;
      for (int i = 0; i < lines.size(); i++) {
         if (lines.get(i).startsWith("USER")) {
            lines.set(i, "USER = " + userId.toString());
            userExists = true;
            break;
         }
      }
      if (!userExists)
         lines.add("USER = " + userId.toString());
      Files.write(key.toPath(), lines, StandardCharsets.UTF_8);
   }

   @FXML
   private void switchToLogin() {
      App.setScene("/ui/login");
   }
}
