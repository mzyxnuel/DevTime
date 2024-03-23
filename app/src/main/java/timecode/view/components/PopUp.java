package timecode.view.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Window;

public class PopUp extends Popup {
   public PopUp(String text, boolean success, Window owner) {
      Rectangle background = new Rectangle(200, 30, Color.RED);
      StackPane content = new StackPane();
      content.setStyle( "-fx-background-color: rgba(255, 0, 0); -fx-padding: 10px;");

      Label label = new Label(text);
      label.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");

      content.getChildren().addAll(background, label);

      setAutoHide(true);
      setHideOnEscape(true);
      setAutoFix(true);

      setX(owner.getX() + owner.getWidth() - background.getWidth() - text.length() * 4.5);
      setY(owner.getY() + owner.getHeight() - background.getHeight() - 50);

      getContent().add(content);
   }
}
