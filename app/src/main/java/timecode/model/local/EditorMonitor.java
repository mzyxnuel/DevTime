package timecode.model.local;

import java.util.List;

public class EditorMonitor implements Runnable {
   private boolean isOpen = false;

   @Override
   public void run() {
      String processName = "Code.exe";
      while (true) {
         List<ProcessHandle> processes = ProcessHandle.allProcesses().toList();
         for (ProcessHandle process : processes) {
            if (process.info().command().orElse("").contains(processName)) {
               isOpen = true;
               break;
            } else
               isOpen = false;
         }

         System.out.println("[monitor]: is editor open: " + isOpen);

         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   public boolean isOpen() {
      return isOpen;
   }

}
