package timecode.model.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import timecode.view.App;

public class DotEnv {
   private Dotenv env;

   public DotEnv() {
      try {
         this.env = Dotenv.configure().directory("app/.env").load();
      } catch (DotenvException e) {
         System.out.println("[error]: missing .env");
         System.exit(0);
      }
   }

   public String get(String key) {
      return this.env.get(key);
   }

   public void saveApiKey(String apiKey) throws IOException {
      File key = new File("app/.env");
      List<String> lines = Files.readAllLines(key.toPath());
      boolean userExists = false;
      for (int i = 0; i < lines.size(); i++) {
         if (lines.get(i).startsWith("APIKEY")) { // check if in all the .env lines there is a APIKEY variable
            lines.set(i, "APIKEY = " + apiKey); // update APIKEY variable to a new one
            userExists = true;
            break;
         }
      }
      if (!userExists)
         lines.add("APIKEY = " + apiKey); // set the APIKEY variable to a new one
      Files.write(key.toPath(), lines);

      App.startApp("/ui/dashboard"); //then start the dashboard if the user is logged
   }
}
