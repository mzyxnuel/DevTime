package devtime.view.local;

import java.io.IOException;

import devtime.model.local.MessageManager;
import devtime.view.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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
