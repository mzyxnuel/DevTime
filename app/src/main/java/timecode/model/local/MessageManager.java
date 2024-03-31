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
         case "erro/xml":
            text = "XML parsing error";
            break;
         case "error/connection":
            text = "Connection error";
            break;
         case "error/env":
            text = "Missing Enviroment";
            break;
         case "system/env":
            text = "System error";
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
