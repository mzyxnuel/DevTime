package devtime.view.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Window;

public class PopUp extends Popup {
   private Rectangle background;

   public PopUp(String text, boolean success, Window owner) {
      StackPane content = new StackPane();
      Label label = new Label(text);

      if (!success) {
         background = new Rectangle(200, 30, Color.RED);
         content.setStyle( "-fx-background-color: rgba(255, 0, 0); -fx-padding: 10px;");
         label.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
      } else {
         background = new Rectangle(200, 30, Color.GREEN);
         content.setStyle( "-fx-background-color: rgba(0, 255, 0); -fx-padding: 10px;");
         label.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
      }

      content.getChildren().addAll(background, label);

      setAutoHide(true);
      setHideOnEscape(true);
      setAutoFix(true);

      setX(owner.getX() + owner.getWidth() - background.getWidth() - text.length() * 4.5);
      setY(owner.getY() + owner.getHeight() - background.getHeight() - 50);

      getContent().add(content);
   }
}
