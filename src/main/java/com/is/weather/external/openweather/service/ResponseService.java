package com.is.weather.external.openweather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {

  public String getResponse(int cityId) {
    String content = "";
    try {

      final URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?id=" + cityId + "&units=metric&appid=29136ab1221e8f97ae8e698cf4529a08");

      final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

      con.setRequestMethod("GET");
      con.setConnectTimeout(5000);
      con.setReadTimeout(5000);
      con.setDoOutput(true);

      try  {
        content = readInputStream(con);
        con.disconnect();
      } catch (Exception e) {
        e.printStackTrace();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return content;
  }

  private static String readInputStream(final HttpURLConnection con){

    try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
      String inputLine;
      final StringBuilder content = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      return content.toString();
    }
    catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

}
