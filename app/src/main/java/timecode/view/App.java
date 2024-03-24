package timecode.view;

import java.io.IOException;

import atlantafx.base.theme.CupertinoDark;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timecode.model.local.DotEnv;
import timecode.model.local.EditorMonitor;
import timecode.view.local.FxmlManager;

public class App extends Application {
   private static Group root;
   private static Scene scene;
   private static Stage stage;

   @Override
   public void start(Stage x) throws IOException {
      root = new Group();
      scene = new Scene(root);
      stage = new Stage();

      String path = "";
      if (new DotEnv().get("USER") == null)
         path = "/ui/login";
      else {
         path = "/ui/dashboard";
         new Thread(new EditorMonitor()).start();
      }

      Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
      scene = new Scene(new FxmlManager().loadFXML(path));

      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
   }

   public static Stage getStage() {
      return stage;
   }

   public static void setScene(String fxml) {
      scene.setRoot(new FxmlManager().loadFXML(fxml));
   }

   public static void setRoot(Node node) {
      root.getChildren().add(node);
   }

   public static void main(String[] args) {
      launch();
   }
}
