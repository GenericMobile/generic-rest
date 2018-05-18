namespace Generic {
  using System;
  using System.Text;
  using System.Net;
  using System.Net.Http;
  using System.Net.Http.Headers;

  public class REST {
    private static readonly string username = "USERNAME";
    private static readonly string password = "PASSWORD";

    static void Main() {
      string base64 = System.Convert.ToBase64String(Encoding.UTF8.GetBytes(username + ":" + password));
      string jsonData = "{ \"From\": \"Test\", \"To\": [ \"+15551234\" ],\"Text\": \"Test message\"}";

      // Setup client and handle IDisposable interface
      using(var client = new HttpClient()) {
        client.BaseAddress = new Uri("https://api.genericmobile.se/SmsGateway/api/v1/");
        client.DefaultRequestHeaders.Accept.Clear();
        client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", base64);

        // Setup the request
        HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Post, "Message");
        request.Content = new StringContent(
          jsonData,
          Encoding.UTF8,
          "application/json" // Set Content-Type to application/json
        );

        // Calling Result causes request to run synchronously
        var response = client.SendAsync(request).Result;
        Console.WriteLine(response.Content.ReadAsStringAsync().Result);
      }
      // flush output so we see the response before console is closed
      Console.Out.Flush();
    }
  }
}