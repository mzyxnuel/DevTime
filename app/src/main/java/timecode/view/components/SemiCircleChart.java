package timecode.view.components;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SemiCircleChart extends Parent {
   public SemiCircleChart(double percentage, boolean increase) {
      List<SemiCircleChart.Data> dataList = new ArrayList<>();
      double centerX = 150; // coordinates for the center of the chart
      double centerY = 230;
      double radius = 180; // radius of the chart
      double innerHoleRadius = 120; // radius of the inner hole in the chart
      double totalValues = 0;
      double totalAngle = 180;
      String text = percentage + "% ";

      dataList.add(new Data(percentage));

      if (increase) {
         text +=  "Increase";
         dataList.get(0).setColor(Color.GREEN);
      } else {
         text +=  "Descrease";
         dataList.get(0).setColor(Color.RED);
      }

      dataList.add(new SemiCircleChart.Data(100 - percentage, Color.GRAY)); // calculate the remaining data

      for (Data data : dataList)
         totalValues += data.getValue();

      double ratio = totalValues / 180;  // calculate the ratio of each data point's value to the total

      for (int i = 0; i < dataList.size(); i++) {
         Data data = dataList.get(i);

         Arc slice = new Arc(); // create a new arc for each slice
         slice.setCenterX(centerX);
         slice.setCenterY(centerY);
         slice.setRadiusX(radius);
         slice.setRadiusY(radius);
         slice.setType(ArcType.ROUND);

         slice.setFill(data.getColor());
         slice.setStartAngle(totalAngle);

         double length = data.getValue() / ratio; // calculate the length of the slice based on the data value and the ratio
         slice.setLength(-length);
         totalAngle -= length;

         this.getChildren().add(slice);
      }

      Arc hole = new Arc();
      hole.setCenterX(centerX);
      hole.setCenterY(centerY);
      hole.setRadiusX(innerHoleRadius);
      hole.setRadiusY(innerHoleRadius);
      hole.setType(ArcType.ROUND);
      hole.setStartAngle(0);
      hole.setLength(180);
      hole.setFill(Color.rgb(49,49,49));
      this.getChildren().add(hole);

      Text statusText = new Text(text);
      statusText.setX(centerX - statusText.getLayoutBounds().getWidth() / 2 - 10);
      statusText.setY(centerY - statusText.getLayoutBounds().getHeight() / 2 - 20);
      statusText.setFont(new Font(16));

      this.getChildren().add(statusText);
   }

   public static final class Data {
      private double value;
      private Color color;

      public Data(double value, Color color) {
         this.value = value;
         this.color = color;
      }

      public Data(double value) {
         this.value = value;
      }

      public double getValue() {
         return value;
      }

      public Color getColor() {
         return color;
      }

      public void setColor(Color color) {
         this.color = color;
      }

      public void setValue(double value) {
         this.value = value;
      }
   }
}
