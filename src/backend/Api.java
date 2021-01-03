package backend;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.URL;
import java.nio.charset.Charset;

import java.util.Date;

import org.json.JSONObject;

public class Api {
	private String apiKey;
	
	public Api() {
		try {
			apiKey = getApiKeyFromFile("apikeyfile.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public String getApiKeyFromFile(String fileName) throws IOException {
		
		String apiKey = "";
		BufferedReader apiKeyBr = new BufferedReader(new FileReader(fileName));
		apiKey = apiKeyBr.readLine();
		
		return apiKey;
	}
	
	private String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
	public JSONObject call(String searchText) throws IOException {
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+searchText+"&units=metric&appid="+apiKey);
		
		InputStream stream = url.openStream();
		try {
		      InputStreamReader input = new InputStreamReader(stream, Charset.forName("UTF-8"));
		      String jsonText = readAll(input);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		} 
		finally {
		    stream.close();
		}
		
	}

	public CityWeather search(String searchText) throws IOException {
		JSONObject json = call(searchText);
		
		String cityName = json.getString("name");
		String country = json.getJSONObject("sys").getString("country");
		double temperature = json.getJSONObject("main").getDouble("temp");
		int maxTemp = json.getJSONObject("main").getInt("temp_max");
		int minTemp = json.getJSONObject("main").getInt("temp_min");
		int pressure = json.getJSONObject("main").getInt("pressure");
		int humidity = json.getJSONObject("main").getInt("humidity");
		double windSpeed  = json.getJSONObject("wind").getDouble("speed");
		String description = ((JSONObject)json.getJSONArray("weather").get(0)).getString("description");
		String icon = ((JSONObject)json.getJSONArray("weather").get(0)).getString("icon");
		
		return new CityWeather(cityName, country, temperature, maxTemp, minTemp, pressure, humidity, windSpeed, description, icon);
		
	}


}