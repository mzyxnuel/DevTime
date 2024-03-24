package timecode.model.local;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

import jakarta.xml.bind.JAXBException;
import timecode.model.net.HttpHandler;
import timecode.model.net.JAXB;
import timecode.model.requests.Activity;
import timecode.model.requests.Activity.FilesContainer;
import timecode.model.requests.Activity.FilesContainer.FileContainer;

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
            if (process.info().command().orElse("").contains(processName)) { //check if process is active
               isOpen = true;
               break;
            } else
               isOpen = false;
         }

         if (!actReg && isOpen) { // if the editor is open for the first time, start the activity
            startTime = Instant.now().getEpochSecond();
            actReg = true;
         } else if (actReg && !isOpen) { // if the editor is closed and the activity is started, then finish the activity
            try {
               endTime = Instant.now().getEpochSecond();

               Activity activity = new Activity(getIdUser(), startTime, endTime, getProjectName(), getOs(), getFiles());

               String xml = new JAXB(Activity.class).marshal(activity);
               HttpResponse<String> response = new HttpHandler().http( // new request
                  "POST",
                  "/index", // TODO path
                  xml
               );

               System.out.println("[monitor] activity saved");
               System.out.println(response.body()); //TODO display activity saved

               actReg = false;
            } catch (URISyntaxException | IOException | InterruptedException e) {
               System.out.println("error/connection");
            } catch (JAXBException e) {
               System.out.println("error/parsing");
            }
         }

         try {
            System.out.println("[monitor]: is editor open: " + isOpen);
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   private int getIdUser() {
      return 0; //TODO
   }

   private String getProjectName() { // simulate the project folder
      String path = System.getProperty("user.dir");
      int lastIndex = path.lastIndexOf('\\');
      String name = path.substring(lastIndex + 1);
      return name;
   }

   private String getOs() {
      return System.getProperty("os.name");
   }

   public FilesContainer getFiles() {
      String path = System.getProperty("user.dir"); // simulate the project folder
      File dir = new File(path);
      return analyzeDirectory(dir);
   }

   private FilesContainer analyzeDirectory(File dir) {
      FilesContainer files = new FilesContainer();
      File[] dirFiles = dir.listFiles();
      if (dirFiles != null) {
         for (File file : dirFiles) {
            if (file.isFile())
               files.add(new FileContainer(file.getName(), countRows(file.getAbsolutePath())));
            else if (file.isDirectory())
               files.addAll(analyzeDirectory(file));
         }
      }
      return files;
   }

   private int countRows(String name) {
      int lines = 0;
      try {
         BufferedReader reader = new BufferedReader(new FileReader(name));
         while (reader.readLine() != null)
            lines++;
         reader.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return lines;
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
