package timecode.model.local;

import javafx.stage.Stage;
import timecode.view.App;
import timecode.view.components.PopUp;

public class MessageManager {
   private Stage stage = App.getStage();
   private String text = "";
   private boolean success = false;

   public MessageManager(String code) {
      String status = code.substring(0, code.indexOf("/"));
      String type = code.substring(code.indexOf("/") + 1);

      switch (status) {
         case "success":
            success = true;
            break;

         case "error":
            success = false;
         break;
      }

      // xml connection system env

      switch (type) {
         case "login":
            text = "Login successfull";
            break;
         case "connection":
            text = "Connection error";
            break;
         case "parsing":
            text = "Parsing error";
            break;
         case "password-too-short":
            text = "Password too weak, at least 8 characters";
            break;

         default:
            text = "Unknown error";
            break;
      }
      new PopUp(text, success, stage).show(stage);
   }
}
