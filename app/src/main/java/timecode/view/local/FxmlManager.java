package timecode.view.local;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import timecode.view.App;

public class FxmlManager {
   public Parent loadFXML(String fxml) {
      try {
         FXMLLoader fx = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
         return fx.load();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }
}
