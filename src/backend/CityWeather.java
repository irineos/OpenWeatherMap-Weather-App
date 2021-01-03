package backend;

import java.util.Date;
import java.text.SimpleDateFormat;  


public class CityWeather {
	
	private String cityName;
	private String country;
	private double temperature;
	private int maxTemp;
	private int minTemp;
	private int pressure;
	private int humidity;
	private double windSpeed;
	private String description;
	private String icon;
	
	public CityWeather(String cityName, String country, double temperature, int maxTemp, int minTemp, int pressure, int humidity, double windSpeed, String description, String icon) {
		this.cityName = cityName;
		this.country = country;
		this.temperature = temperature;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.description = description;
		this.icon = icon;
		
	}

	public String getCityName() {
		return cityName +" "+ country;
	}

	public String getTemperature() {
		return Math.round(temperature)+"";
	}

	public String getMaxTemp() {
		return Math.round(maxTemp)+"";
	}

	public String getMinTemp() {
		return Math.round(minTemp)+"";
	}

	public String getPressure() {
		return pressure+"";
	}

	public String getHumidity() {
		return humidity+"";
	}

	public String getWindSpeed() {
		return windSpeed+"";
	}

	public Date getDate() {
		return new Date();
	}

	public String getDescription() {
		return description.substring(0, 1).toUpperCase() + description.substring(1);
		
	}
	
	public String getIcon() {
		return icon;
	}
	
	
}
