package timecode.view;

import java.io.IOException;

import atlantafx.base.theme.CupertinoDark;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

      Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());

      if (new DotEnv().get("APIKEY") == null)
         scene = new Scene(new FxmlManager().loadFXML("/ui/login"));
      else {
         scene = new Scene(new FxmlManager().loadFXML("/ui/dashboard"));
         new Thread(new EditorMonitor(), "monitor").start();
      }

      stage.setScene(scene);
      stage.setResizable(false);
      stage.getIcons().add(new Image(App.class.getResourceAsStream("/icons/timecode.png")));
      stage.setTitle("TimeCode");
      stage.show();
   }

   public static Stage getStage() {
      return stage;
   }

   public static void setScene(String fxml) {
      scene.setRoot(new FxmlManager().loadFXML(fxml));
   }

   public static void setScene(Scene scene) {
      stage.setScene(scene);
   }


   public static void setRoot(Node node) {
      root.getChildren().add(node);
   }

   public static Group getRoot() {
      return root;
   }

   public static void main(String[] args) {
      launch();
   }
}
