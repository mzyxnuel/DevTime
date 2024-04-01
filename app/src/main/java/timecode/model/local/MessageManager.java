package timecode.model.local;

import javafx.application.Platform;
import javafx.stage.Stage;
import timecode.view.App;
import timecode.view.components.PopUp;

public class MessageManager {
   private Stage stage = App.getStage();
   private String text = "";
   private boolean success = false;

   public MessageManager(String code) {
      String status = code.substring(0, code.indexOf("/"));

      switch (status) {
         case "success":
            success = true;
            break;

         case "error":
            success = false;
         break;
      }

      switch (code) {
         case "error/xml":
            text = "XML parsing error";
            break;
         case "error/connection":
            text = "Connection error";
            break;
         case "error/env":
            text = "Missing enviroment";
            break;
         case "error/system":
            text = "System error";
            break;
         case "error/login":
            text = "Login error";
            break;
         case "error/signup":
            text = "Signup error";
            break;
         case "error/access_denied":
            text = "Access denied";
            break;
         case "error/invalid_project_name":
            text = "Invalid name project";
            break;
         case "error/invalid_api_key":
            text = "Invalid API key";
            break;

         case "success/login":
            text = "Login success";
            break;
         case "success/signup":
            text = "Signup success";
            break;
         case "success/activity":
            text = "Activity saved";
            break;
         case "success/get_info":
            text = "Get informations";
            break;

         default:
            text = "Unknown error";
            break;
      }

      Platform.runLater(() -> {
         new PopUp(text, success, stage).show(stage);
      });
   }
}
