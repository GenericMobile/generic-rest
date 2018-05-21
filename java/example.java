import java.lang.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class example {
  private static String username = "USERNAME";
  private static String password = "PASSWORD";
  
  public static void main(String[] args) {

    URL url = null;
    try {
      url = new URL("https://api.genericmobile.se/SmsGateway/api/v1/Message");
    } catch(MalformedURLException e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
      return;
    }
    
    try {
      HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
      // Auth Basic header
      String basicAuth = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
      // Body JSON data
      String jsonData = "{ \"From\": \"TEST\", \"To\": [ \"+15551234\" ],\"Text\": \"Test message\"}";

      urlConnection.setRequestMethod("POST");
      urlConnection.setRequestProperty("Authorization", basicAuth);
      urlConnection.setRequestProperty("Content-Type", "application/json");
      urlConnection.setUseCaches(false);
      urlConnection.setDoOutput(true);
      urlConnection.setDoInput(true);

      BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
      httpRequestBodyWriter.write(jsonData);
      httpRequestBodyWriter.close();
    } catch(IOException e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
      return;
    }
  }
}