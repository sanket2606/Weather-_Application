package mini_project;

import java.io.BufferedReader;







import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Date; 

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Weather_App {
	private static final String API_KEY = "0aac0de3a654da2194619b7a944c69c3";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String Country_Code = "IN";
	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		 // Prompt user for city name
		       while(true)
		       {
		    	   System.out.println("\n================== Weather Application =======================");
			        System.out.println("1. Current Weather");
			        System.out.println("2. Temperature Details");
			        System.out.println("3. Humidity and Pressure");
			        System.out.println("4. Cloud Cover");
			        System.out.println("5. Sunrise and Sunset Times");
			        System.out.println("6. Wind Information");
			        System.out.println("7. 5-Day / 3-Hour Forecast");
			        System.out.println("8. Current Air Pollution Data");
			        System.out.println("9. Exit");
			        System.out.println("===============================================================");
			        
			        System.out.print("\nEnter the number of the option you want to select: ");
			        int choice = scanner.nextInt();
			        
			        switch (choice) {
		            case 1:
		            {
		            	System.out.print("\nEnter the city name: ");
		                Scanner sc = new Scanner(System.in);
		                String city = sc.next();

		        		try 
		        		{
		        			// Attempt to retrieve and display weather informatio
		        	    	displaywetherData(getWeatherData(city));
		        	    	
		        			
		        		}
		        		catch ( FileNotFoundException e) 
		        		{
		        			 // Handle case where city is not found
		        			System.err.println("Invalid city name. Please enter a valid city name.");
		        		
		        		}
		        		catch ( UnknownHostException e) 
		        		{
		        			// Handle case where there is no internet connection
		        			 System.err.println("Error: No internet connection. Please check your network settings.");
		        		}
		        		catch (Exception e) 
		        		{
		        	        // Handle other exceptions
		        	        e.printStackTrace();
		        	    }
		            
		            	
		            	
		                break;
		            }
			        
		            case 2:
		            {
		            	System.out.print("\nEnter the city name: ");
		                Scanner sc = new Scanner(System.in);
		                String city = sc.next();
		            	displayTemperature(getWeatherData(city));
		                break;
		            }
			       
		            case 3:
		            {
		            	System.out.print("\nEnter the city name: ");
		                Scanner sc = new Scanner(System.in);
		                String city = sc.next();
		            	//displayFiveDayWeather(getWeatherData(city));
		                // Code to handle Humidity and Pressure option
		                break;
		            }
		            case 4:
		            {
		                // Code to handle Cloud Cover option
		                break;
		            }
		            case 5:
		            {
		                // Code to handle Sunrise and Sunset Times option
		                break;
		            }
		            case 6:
		            {
		                // Code to handle Wind Information option
		                break;
		            }
		            case 7:
		            {
		            	displayWeatherData(getFiveDayData());

		               
		                break;
		            }
		            case 8:
		            {
		                // Code to handle Current Air Pollution Data option
		                break;
		            }
		            case 9:
		            {
		                // Close The Application
		            	System.exit(0);
		                break;
		            }
		            default:
		            {
		                System.out.println("Invalid option selected.");
		            }
			        
		        }
		
			       
		       }
		      
		        
		        
	}
		        
		        /**
		         * Establishes a connection to the OpenWeatherMap API using the provided city name
		         * and API key. Retrieves weather data in JSON format for the specified city.
		         *
		         * @param city The name of the city for which weather data is requested.
		         * @return A JSON-formatted string containing weather data for the specified city.
		         * @throws Exception If an error occurs during the API request or data retrieval.
		         */
		    	
		    	public static String getWeatherData(String city) throws Exception
		    	{
		    		URL url = new URL(BASE_URL + city + "&appid=" + API_KEY);
		    	     HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		    		 try {
		    			 		     
		    		     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    		     StringBuilder response = new StringBuilder();
		    		     //System.out.println(response);
		    		    // System.out.println(reader.readLine());
		    	         String line ;
		    	         
		    	         while((line=reader.readLine()) !=null)
		    	         {
		    	       	     response.append(line);
		    	       	  
		    	         }
		    	         
		    	         
		    	        
		    			return response.toString();
		    			
		    		       
		    			
		    		} 
		    		 finally
		    		 {
		    			 connection.disconnect();
		    		}
		    	}
		    	
		    	/**
		    	 * Displays the current weather data retrieved from the API response.
		    	 *
		    	 * @param jsonResponse The JSON response containing weather data.
		    	 */
		    	public static void displaywetherData(String jsonResponse)
		    	{
		    		 try 
		    		 {
		    			 JSONObject jsonObject = new JSONObject(jsonResponse);
		    			 
		    			// System.out.println(jsonObject);
		    			 int codValue = jsonObject.getInt("cod");
		    			 JSONObject sysObject = jsonObject.getJSONObject("sys");
		    	         String country = sysObject.getString("country");
		    			 if(Country_Code.equals(country) )
		    			 {
		    				 String cityName = jsonObject.getString("name");
		    		            String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
		    		            double temperature = jsonObject.getJSONObject("main").getDouble("temp") - 273.15; // Convert from Kelvin to Celsius
		    				

		    		            System.out.println("\nWeather in " + cityName + ":");
		    		            System.out.println("Description: " + description+ "");
		    		            System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
		    		            System.out.println("Day  : "+getDay());
		    		            System.out.println("Date : " +java.time.LocalDate.now()); 
		    		            System.out.println("Time : " + java.time.LocalTime.now());
		    		           
		    		            
		    			 }
		    			 else
		    			 {
		    				 System.err.println("\nThis Applicatin is only for Indian weather, not for other countries.");
		    			 }
		    		 } 
		    		 catch (Exception e)
		    		 {
		    			 e.printStackTrace();
		    		}
		    		
		    	}
		    	
		    	// Display the five-day weather forecast
//		    	public static void displayFiveDayWeather(String jsonResponse) {
//		    	    try {
//		    	        JSONObject jsonObject = new JSONObject(jsonResponse);
//		    	        System.out.println(jsonObject);
//		    	        System.out.println("Sanket");
//
//		    	        JSONArray dailyForecasts = jsonObject.getJSONArray("daily");
//		    	        ZoneId zoneId = ZoneId.of((String) jsonObject.get("timezone"));
//
//		    	        System.out.println("Five-Day Weather Forecast for " + jsonObject.getString("name"));
//		    	        System.out.println("Date\t\tMax Temp (°C)\tMin Temp (°C)\tPrecipitation (%)");
//
//		    	        for (int i = 0; i < dailyForecasts.length(); i++) {
//		    	            JSONObject dailyForecast = dailyForecasts.getJSONObject(i);
//		    	            long timestamp = dailyForecast.getLong("dt");
//		    	            ZonedDateTime datetime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zoneId);
//		    	            double maxTemp = dailyForecast.getJSONObject("temp").getDouble("max") - 273.15;
//		    	            double minTemp = dailyForecast.getJSONObject("temp").getDouble("min") - 273.15;
//		    	            double precipitation = dailyForecast.getDouble("pop") * 100; // Convert from decimal to percentage
//
//		    	            System.out.printf("%s\t%.1f\t\t%.1f\t\t%.1f%n",
//		    	                datetime.format(DateTimeFormatter.ISO_LOCAL_DATE),
//		    	                maxTemp, minTemp, precipitation);
//		    	            
//		    	        }
//		    	    } catch (Exception e) {
//		    	        e.printStackTrace();
//		    	    }
//		    	}

		    	 //Fetches and returns the day of the week (e.g.,in int 0,1,2) from a given date. 
		    	public static int getDay()
		    	{
		    		Date dt=new Date();  
		            int d=dt.getDay();
		            
		    		
		    		return d;
		            
		    	}
		    	
		    	 //Fetches and returns the day of the week (e.g.,in int 0,1,2) from a given date. 
		    	public static String getDays(int d)
		    	{
		    		
		           
		            
		            switch (d) {
		    		case 0:
		    		{
		    			return "Sunday";
		    			
		    		}
		    		case 1:
		    		{
		    			 return "Monday";
		    			
		    		}
		    		case 2:
		    		{
		    			return "Tusday";
		    			
		    			
		    		}
		    		case 3:
		    		{
		    			return "Wensday";
		    			
		    			 
		    		}
		    		case 4:
		    		{
		    			
		    			return "Thursday";
		    		}
		    		case 5:
		    		{
		    			
		    			return "Friday";
		    		}
		    		case 6:
		    		{
		    			
		    			return "Saturday";
		    		}
		    		default :
		    		{
		    			return "null"; 
		    		}
		    		
		    		
		    		
		            }
		    	}
		    	
		    	
		    	
		    	public static void displayTemperature(String jsonResponse)
		    	{
		    		 try 
		    		 {
		    			 JSONObject jsonObject = new JSONObject(jsonResponse);
		    			 
		    			 System.out.println(jsonObject);
		    			 int codValue = jsonObject.getInt("cod");
		    			 JSONObject sysObject = jsonObject.getJSONObject("sys");
		    	         String country = sysObject.getString("country");
		    			 if(Country_Code.equals(country) )
		    			 {
		    				 String cityName = jsonObject.getString("name");
		    		            String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
		    		            double temperature = jsonObject.getJSONObject("main").getDouble("temp") - 273.15; // Convert from Kelvin to Celsius
		    		            double mintemperature = jsonObject.getJSONObject("main").getDouble("temp_min") - 273.15;
		    				

		    		            System.out.println("\nWeather in " + cityName + ":");
		    		            System.out.println("Description: " + description+ "");
		    		            System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
		    		            System.out.println("Min_temperature: " + String.format("%.2f", mintemperature) + "°C");
		    		            
		    		           
		    		           
		    		            
		    			 }
		    			 else
		    			 {
		    				 System.err.println("\nThis Applicatin is only for Indian weather, not for other countries.");
		    			 }
		    		 } 
		    		 catch (Exception e)
		    		 {
		    			 e.printStackTrace();
		    		}
		    		
		    	}
		    	
		    	
		    	public static String getFiveDayData() throws JSONException, Exception
		    	{
		    		
		    		System.out.print("\nEnter the city name: ");
	                Scanner sc = new Scanner(System.in);
	                String city = sc.next();
	            	JSONObject jsonObject = new JSONObject(getWeatherData(city));
	            	System.out.println(jsonObject );
	            	JSONObject coordObject = jsonObject.getJSONObject("coord");
	            	 double latitude = (double) coordObject.get("lat");
	            	 double longitude = (double) coordObject.get("lon");
	            	 String baseUrl = "https://api.openweathermap.org/data/2.5/forecast";
	                 String apiUrl = baseUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
	                 URL url = new URL(apiUrl);
	                 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	                 
	                 try {
			 		     
		    		     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    		     StringBuilder response = new StringBuilder();
		    		     //System.out.println(response);
		    		    // System.out.println(reader.readLine());
		    	         String line ;
		    	         
		    	         while((line=reader.readLine()) !=null)
		    	         {
		    	       	     response.append(line);
		    	       	  
		    	         }
		    	         
		    	         
		    	        
		    		 return response.toString();
		    			
		    		       
		    			
		    		} 
		    		 finally
		    		 {
		    			 connection.disconnect();
		    		}

		    		
		    	}
		    	
		    	
		    	public static void displayWeatherData(String jsonData) throws JSONException {
		            JSONObject jsonObject = new JSONObject(jsonData);

		            // Extracting data from the JSON object
		            String cityName = jsonObject.getJSONObject("city").getString("name");
		            double cityLatitude = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lat");
		            double cityLongitude = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lon");

		            System.out.println("City: " + cityName);
		            System.out.println("Latitude: " + cityLatitude);
		            System.out.println("Longitude: " + cityLongitude);

		            JSONArray weatherList = jsonObject.getJSONArray("list");
		            int d=getDay();

		            for (int i = 0; i < weatherList.length(); i++) {
		                JSONObject weatherObject = weatherList.getJSONObject(i);
		                long timestamp = weatherObject.getLong("dt");
		                System.out.println("TimeStrap :"+ timestamp );
		                JSONObject main = weatherObject.getJSONObject("main");
		                double temperature = main.getDouble("temp") - 273.15;
		                JSONArray weatherArray = weatherObject.getJSONArray("weather");
		                String weatherDescription = weatherArray.getJSONObject(0).getString("description");
		                

		                 System.out.println("TimeStamp: "+getTimeStamp( timestamp));
		                System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
		                System.out.println("Weather Description: " + weatherDescription);
		                
		               if(d>6)
		               {
		            	   d=0;
		               }
		               else
		               {
		            	   System.out.println("Day  : "+getDays(d++));
					   }
		                System.out.println("------------------------");
		            }
		            
		            
		            
		            
		            
		        }
		    	
		    	
		    	public static String getTimeStamp( long timestamp)
	            {
	            	 // Unix timestamp in seconds (e.g., 1693882800)
	                long unixTimestamp = 1693882800L;

	                // Convert Unix timestamp to an Instant
	                Instant instant = Instant.ofEpochSecond(unixTimestamp);

	                // Define a time zone (e.g., "UTC" or "America/New_York")
	                ZoneId zoneId = ZoneId.of("UTC");

	                // Convert Instant to ZonedDateTime using the specified time zone
	                ZonedDateTime zonedDateTime = instant.atZone(zoneId);

	                // Define a DateTimeFormatter for the desired output format
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	                // Format the ZonedDateTime as a String
	                String formattedDateTime = zonedDateTime.format(formatter);
	                return formattedDateTime;
	            }
	            
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
}
		            
		            
		    	
		    	


		    
		    

