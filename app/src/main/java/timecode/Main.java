package timecode;

import timecode.model.local.EditorMonitor;
import timecode.view.App;

public class Main {
   public static void main(String[] args) {
      new Thread(new EditorMonitor()).start();
      App.main(args);
   }
}
