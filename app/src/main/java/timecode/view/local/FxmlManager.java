package timecode.view.local;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import timecode.model.local.MessageManager;
import timecode.view.App;

public class FxmlManager {
   private FXMLLoader fx;

   public Parent loadFXML(String fxml) {
      try {
         fx = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
         return fx.load();
      } catch (IOException e) { new MessageManager("error/system"); }
      return null;
   }
}
