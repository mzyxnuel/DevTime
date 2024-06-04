package devtime.model.net;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import devtime.model.local.DotEnv;
import devtime.model.local.MessageManager;

public class HttpHandler {
   private DotEnv env = new DotEnv();
   private HttpClient client = HttpClient.newHttpClient();
   private HttpResponse<String> response = null;

   public HttpResponse<String> http(String method, String uri, String content) {
      try {
         HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
         .uri(new URI(
            env.get("DOMAIN")
            + uri
         ))
         .header("Content-Type", "application/xml");

         switch (method.toUpperCase()) {
            case "GET":
               requestBuilder.GET();
               break;
            case "POST":
               requestBuilder.POST(HttpRequest.BodyPublishers.ofString(content));
               break;
            default:
               throw new Exception();
         }

         HttpRequest request = requestBuilder.build();
         response = client.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (Exception e) { new MessageManager("error/connection"); }

      return response;
   }
}
