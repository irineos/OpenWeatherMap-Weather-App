package frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.javafx.scene.input.KeyCodeMap;

import backend.Api;
import backend.CityWeather;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WeatherAppView extends Application {
	
	@FXML
    private Label day;
    @FXML
    private Label month;
	@FXML
    private Label temp_id;
    @FXML
    private Label description_id;
    @FXML
    private Label cityName_id;
    @FXML
    private ImageView image_id;
    @FXML
    private TextField search_id;
    @FXML
    private Label humidity_id;
    @FXML
    private Label pressure_id;
    @FXML
    private Label wind_id;
    
    private Api api = new Api();
    private String searchText;

    
    private String[] months = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};

	
	public void start(Stage stage) throws IOException {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("weather_app.fxml"));
        Scene scene = new Scene(root);
        
        temp_id = new Label();
        description_id = new Label();
        cityName_id = new Label();
        wind_id = new Label();
        humidity_id = new Label();
        pressure_id = new Label();
        day = new Label();
        month = new Label();
        image_id = new ImageView();
        
        stage.setMinHeight(560);
        stage.setMinWidth(480);
        stage.setTitle("Weather App");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	  void onEnter(KeyEvent e) throws IOException {
		  if(e.getCode().equals(KeyCode.ENTER)) {
      		searchText = search_id.getText();
      		System.out.println(searchText);
      		setComponents(searchText);
      	  }
	  }
	
	@FXML
    void exit(MouseEvent event) {
		System.exit(0);
    }
	
	public void setComponents(String searchText) throws IOException{
		
		
		CityWeather weather = api.search(searchText);
		
	    temp_id.setText(weather.getTemperature()+"°");
	    description_id.setText(weather.getDescription());
	    cityName_id.setText(weather.getCityName());
	    wind_id.setText(weather.getWindSpeed()+"km/h");
	    humidity_id.setText(weather.getHumidity()+"%");
	    pressure_id.setText(weather.getPressure()+"hPa");
	    
	    Date date = weather.getDate();
	    
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    
	    int dayIndex = cal.get(Calendar.DAY_OF_MONTH);
	    int monthIndex = cal.get(Calendar.MONTH);
	    
	    day.setText(dayIndex+"");
	    
	    System.out.println(monthIndex);
	    month.setText(months[monthIndex]);
	   
	    
	    
	    String imageName = weather.getIcon();
	    System.out.println(imageName); 	
	    
	    Image icon = new Image(imageName+".png");
	    image_id.setImage(icon);
	    
	 }
	



	public static void main(String[] args) {
		launch(args);
	}
}
