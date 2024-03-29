package timecode.view.components;

import java.util.List;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class SemiCircleChart extends Parent {
   private List<Data> dataList;

   public SemiCircleChart(List<Data> dataList) {
      this.dataList = dataList;
      double centerX = 120; // coordinates for the center of the chart
      double centerY = 210;
      double radius = 180; // radius of the chart
      double innerHoleRadius = 120; // radius of the inner hole in the chart
      double totalValues = 0;
      double totalAngle = 180;

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
   }

   public List<Data> getDataList() {
      return dataList;
   }

   public static final class Data {
      private String name;
      private double value;
      private Color color;

      public Data(String name, double value, Color color) {
         this.name = name;
         this.value = value;
         this.color = color;
      }

      public String getName() {
         return name;
      }

      public double getValue() {
         return value;
      }

      public Color getColor() {
         return color;
      }
   }
}
