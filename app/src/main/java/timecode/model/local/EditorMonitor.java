package timecode.model.local;

import java.time.Instant;
import java.util.List;

import timecode.model.Activity;
import timecode.model.Activity.Files;
import timecode.model.net.HttpHandler;
import timecode.model.net.JAXB;

public class EditorMonitor implements Runnable {
   private long startTime;
   private long endTime;
   private boolean isOpen = false;
   private boolean actReg = false;

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

         if (!actReg && isOpen) { //if the edior is open for the first time start the activity
            startTime = Instant.now().getEpochSecond();
            actReg = true;
         } else if (actReg && !isOpen) { // if the edior is closed and the activity is started the finish the activity
            endTime = Instant.now().getEpochSecond();

            System.out.println("[monitor] activity saved");
            Activity activity = new Activity(getIdUser(), startTime, endTime, getProjectName(), getOs(), getFiles());

            String xml = new JAXB(Activity.class).marshal(activity);

            new HttpHandler().http(
               "POST",
               "/", //TODO
               xml
            );

            actReg = false;
         }

         System.out.println("[monitor]: is editor open: " + isOpen);

         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   private int getIdUser() {
      return 0;
   }

   private String getProjectName() {
      return "";
   }

   private String getOs() {
      return System.getProperty("os.name");
   }

   private Files getFiles() {
      Files files = new Files();
      return files;
   }

   public boolean isOpen() {
      return isOpen;
   }

   public long getStartTime() {
      return startTime;
   }

   public long getEndTime() {
      return endTime;
   }
}
