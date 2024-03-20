package timecode;

import timecode.view.App;

public class Main {
   public static void main(String[] args) {
      App.main(args);


      // EditorMonitor monitor = new EditorMonitor();
      // Thread t = new Thread(monitor, "ed");
      // t.start();

      // while (true) {
      //    boolean isOpen = monitor.isOpen();
      //    if (!isOpen) {
      //       // http richiest + thread stop
      //       System.out.println(System.getProperty("os.name"));
      //       System.out.println(System.getProperty("user.dir"));
      //       System.out.println(System.getProperty("user.name"));
      //    }

      //    try {
      //       Thread.sleep(1000);
      //    } catch (InterruptedException e) {
      //       e.printStackTrace();
      //    }
      // }
   }
}
