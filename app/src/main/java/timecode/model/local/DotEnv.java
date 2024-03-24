package timecode.model.local;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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

   public boolean envProd() {
      return Boolean.parseBoolean(this.env.get("PRODUCTION"));
   }

   public void saveUserId(BigInteger userId) throws IOException {
      File key = new File("app/.env");
      List<String> lines = Files.readAllLines(key.toPath(), StandardCharsets.UTF_8);
      boolean userExists = false;
      for (int i = 0; i < lines.size(); i++) {
         if (lines.get(i).startsWith("USER")) {
            lines.set(i, "USER = " + userId.toString());
            userExists = true;
            break;
         }
      }
      if (!userExists)
         lines.add("USER = " + userId.toString());
      Files.write(key.toPath(), lines, StandardCharsets.UTF_8);

      App.setScene("/ui/dashboard");
      new Thread(new EditorMonitor()).start();
   }
}
