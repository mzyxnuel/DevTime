package timecode;

import timecode.model.local.EditorMonitor;

public class Main {
   public static void main(String[] args) {
      // App.main(args);

      new Thread(new EditorMonitor()).start();
   }
}
