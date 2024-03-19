package timecode.model;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

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
}
