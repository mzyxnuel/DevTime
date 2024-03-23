package timecode.model.net;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import timecode.model.local.DotEnv;

public class HttpHandler {
   private DotEnv env = new DotEnv();
   private HttpClient client = HttpClient.newHttpClient();

   public HttpResponse<String> http(String method, String uri, String content) throws URISyntaxException, IOException, InterruptedException {
      String extension = "";
      if (!env.envProd())
         extension = ".php";

      HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .uri(new URI(
               env.get("DOMAIN")
               + uri
               + extension
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
            throw new IllegalArgumentException("Invalid Method: " + method);
      }

      HttpRequest request = requestBuilder.build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response;
   }
}
