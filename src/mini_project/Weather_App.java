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
import java.util.TimeZone;
import java.util.Date;
import java.util.InputMismatchException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;


public class Weather_App {
	private static final String API_KEY = "0aac0de3a654da2194619b7a944c69c3";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String Country_Code = "IN";
	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		 // Prompt user for city name
		       
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
			       
			      while(true)
			      {
			    	  try 
				      {
				    	  System.out.print("\nEnter the number of the option you want to select: ");
				    	  int choice  = scanner.nextInt();
				    	  
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
				                displayOnlyTemperature(getWeatherData(city));
				            
				                break;
				            }
					       
				            case 3:
				            {
				            	System.out.print("\nEnter the city name: ");
				                Scanner sc = new Scanner(System.in);
				                String city = sc.next();
				                displayHumidityAndPressure(getWeatherData(city),city);
				            	
				                break;
				            }
				            case 4:
				            {
				                // Code to handle Cloud Cover option
				            	System.out.print("\nEnter the city name: ");
				                Scanner sc = new Scanner(System.in);
				                String city = sc.next();
				            	displayCloudCover(getWeatherData(city),city); 
				                break;
				            }
				            case 5:
				            {
				                // Code to handle Sunrise and Sunset Times option
				            	System.out.print("\nEnter the city name: ");
				                Scanner sc = new Scanner(System.in);
				                String city = sc.next();
				            	displaySunriseAndSunset(getWeatherData(city)) ;
				                break;
				            }
				            case 6:
				            {
				                // Code to handle Wind Information 
				            	System.out.print("\nEnter the city name: ");
				                Scanner sc = new Scanner(System.in);
				                String city = sc.next();
				            	displayWindInfo(getWeatherData(city));
				                break;
				            }
				            case 7:
				            {
				            	
				            	try 
				        		{
				        			// Attempt to retrieve and display weather informatio
				            		displayWeatherData(getFiveDayData());
				        			
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
				            case 8:
				            {
				            	 displayAirPollution(getAirPollutionData());
				             	break;
				            }
				            case 9:
				            {
				                // Close The Application
				            	System.err.println("The Application Was Closed !!!");
				            	System.exit(0);
				                break;
				            }
				            default:
				            {
				                System.err.println("Invalid option selected.");
				            }
					        
				        }
					  } 
				      catch (InputMismatchException e) 
				      {
						System.err.println("Plese Enter the valid Input Data ");
						break;
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
		    			 
		    			 System.out.println(jsonObject);
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
		    	
		    	
		    	
		    	/**
		    	 * Displays weather information for a given JSON response, focusing on Indian cities.
		    	 *
		    	 * @param jsonResponse A JSON response containing weather data for a city.
		    	 */
		    	public static void displayTemperature(String jsonResponse) {
		    	    try {
		    	        // Parse the JSON response into a JSONObject
		    	        JSONObject jsonObject = new JSONObject(jsonResponse);

		    	        // Extract the 'cod' value from the JSON response
		    	        int codValue = jsonObject.getInt("cod");

		    	        // Extract the 'sys' object from the JSON response
		    	        JSONObject sysObject = jsonObject.getJSONObject("sys");

		    	        // Extract the country code from the 'sys' object
		    	        String country = sysObject.getString("country");

		    	        // Check if the country code matches the specified Indian country code
		    	        if (Country_Code.equals(country)) {
		    	            // Extract relevant weather data for Indian cities
		    	            String cityName = jsonObject.getString("name");
		    	            String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
		    	            double temperature = jsonObject.getJSONObject("main").getDouble("temp") - 273.15; // Convert from Kelvin to Celsius
		    	            double mintemperature = jsonObject.getJSONObject("main").getDouble("temp_min") - 273.15;

		    	            // Display the weather information for the Indian city
		    	            System.out.println("\nWeather in " + cityName + ":");
		    	            System.out.println("Description: " + description + "");
		    	            System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
		    	            System.out.println("Min_temperature: " + String.format("%.2f", mintemperature) + "°C");
		    	        } else {
		    	            // Print an error message if the country code is not Indian
		    	            System.err.println("\nThis Application is only for Indian weather, not for other countries.");
		    	        }
		    	    } catch (Exception e) {
		    	        // Handle any exceptions that may occur during JSON parsing or data extraction
		    	        e.printStackTrace();
		    	    }
		    	}

		    	
		    	/**
		    	 * Retrieves and returns five-day weather forecast data for a specified city using OpenWeatherMap API.
		    	 *
		    	 * @return A JSON string containing the five-day weather forecast data for the specified city.
		    	 * @throws JSONException If there is an issue with JSON parsing.
		    	 * @throws Exception If there is a general exception during the API request.
		    	 */
		    	public static String getFiveDayData() throws JSONException, Exception {
		    	    try {
		    	        // Prompt the user to enter the city name
		    	        System.out.print("\nEnter the city name: ");
		    	        Scanner sc = new Scanner(System.in);
		    	        String city = sc.next();

		    	        // Fetch current weather data for the specified city
		    	        JSONObject jsonObject = new JSONObject(getWeatherData(city));

		    	        // Extract latitude and longitude coordinates from the current weather data
		    	        JSONObject coordObject = jsonObject.getJSONObject("coord");
		    	        double latitude = (double) coordObject.get("lat");
		    	        double longitude = (double) coordObject.get("lon");

		    	        // Construct the API URL for retrieving five-day weather forecast data
		    	        String baseUrl = "https://api.openweathermap.org/data/2.5/forecast";
		    	        String apiUrl = baseUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
		    	        URL url = new URL(apiUrl);
		    	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		    	        try {
		    	            // Read the response from the API and build a StringBuilder to store it
		    	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    	            StringBuilder response = new StringBuilder();
		    	            String line;

		    	            while ((line = reader.readLine()) != null) {
		    	                response.append(line);
		    	            }

		    	            // Return the JSON string containing the five-day weather forecast data
		    	            return response.toString();
		    	        } finally {
		    	            // Ensure that the connection is closed, even in case of exceptions
		    	            connection.disconnect();
		    	        }
		    	    } catch (Exception e) {
		    	        // Handle exceptions related to API requests and JSON parsing
		    	        e.printStackTrace();
		    	        throw e; // Re-throw the exception for the caller to handle
		    	    }
		    	}

		    	
		    	/**
		    	 * Displays weather forecast data extracted from a JSON response, including city information,
		    	 * temperature, weather description, and timestamps for multiple time intervals.
		    	 *
		    	 * @param jsonData A JSON string containing weather forecast data.
		    	 * @throws JSONException If there is an issue with JSON parsing.
		    	 */
		    	public static void displayWeatherData(String jsonData) throws JSONException {
		    	    try {
		    	        // Parse the JSON data into a JSONObject
		    	        JSONObject jsonObject = new JSONObject(jsonData);

		    	        // Extract city information
		    	        String cityName = jsonObject.getJSONObject("city").getString("name");
		    	        double cityLatitude = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lat");
		    	        double cityLongitude = jsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lon");

		    	        // Display city details
		    	        System.out.println("City: " + cityName);
		    	        System.out.println("Latitude: " + cityLatitude);
		    	        System.out.println("Longitude: " + cityLongitude);

		    	        // Extract the weather forecast list
		    	        JSONArray weatherList = jsonObject.getJSONArray("list");
		    	        int d = getDay(); // Get the current day index

		    	        // Iterate through the weather forecast data
		    	        for (int i = 0; i < weatherList.length(); i++) {
		    	            JSONObject weatherObject = weatherList.getJSONObject(i);
		    	            long timestamp = weatherObject.getLong("dt");
		    	            System.out.println("TimeStrap: " + timestamp);
		    	            JSONObject main = weatherObject.getJSONObject("main");
		    	            double temperature = main.getDouble("temp") - 273.15;
		    	            JSONArray weatherArray = weatherObject.getJSONArray("weather");
		    	            String weatherDescription = weatherArray.getJSONObject(0).getString("description");

		    	            // Display timestamp, temperature, and weather description
		    	            System.out.println("TimeStamp: " + getTimeStamp(timestamp));
		    	            System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
		    	            System.out.println("Weather Description: " + weatherDescription);

		    	            // Display the day of the week based on the current day index
		    	            if (d > 6) {
		    	                d = 0;
		    	            } else {
		    	                System.out.println("Day: " + getDays(d++));
		    	            }

		    	            System.out.println("------------------------");
		    	        }
		    	    } catch (JSONException e) {
		    	        // Handle JSON parsing exceptions
		    	        e.printStackTrace();
		    	        throw e; // Re-throw the exception for the caller to handle
		    	    }
		    	}

		    	
		    	/**
		    	 * Converts a Unix timestamp (in seconds) to a formatted date and time string.
		    	 *
		    	 * @param timestamp The Unix timestamp to convert to a formatted date and time.
		    	 * @return A formatted date and time string in the "yyyy-MM-dd HH:mm:ss" format.
		    	 */
		    	public static String getTimeStamp(long timestamp) {
		    	    // Define a Unix timestamp in seconds (e.g., 1693882800)
		    	    // In practice, this method should use the provided 'timestamp' parameter

		    	    // Convert the Unix timestamp to an Instant
		    	    Instant instant = Instant.ofEpochSecond(timestamp);

		    	    // Define a time zone (e.g., "UTC" or "America/New_York")
		    	    ZoneId zoneId = ZoneId.of("UTC");

		    	    // Convert the Instant to a ZonedDateTime using the specified time zone
		    	    ZonedDateTime zonedDateTime = instant.atZone(zoneId);

		    	    // Define a DateTimeFormatter for the desired output format
		    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		    	    // Format the ZonedDateTime as a String
		    	    String formattedDateTime = zonedDateTime.format(formatter);

		    	    // Return the formatted date and time string
		    	    return formattedDateTime;
		    	}

	            
		    	
		    //This method extracts and displays the temperature in Celsius from a JSON object
		    	 public static void displayOnlyTemperature(String json) {
		    	        try {
		    	            JSONObject jsonObject = new JSONObject(json);
		    	            
		    	            JSONObject mainObject = jsonObject.getJSONObject("main");

		    	        
		    	           double temperature = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
		    	           
		    	           System.out.println("Temperature in Pune: " + temperature + "°C");   
		    	            
		    	        } 
		    	        catch (Exception e)
		    	        {
		    	            e.printStackTrace();
		    	        }
		    	    }
		    	
		    	 
		    	//This method extracts and displays theHumidity And Pressure in from a JSON object
		    	 public static void displayHumidityAndPressure(String json,String city) {
		    	        try {
		    	            JSONObject jsonObject = new JSONObject(json);

		    	           
		    	             JSONObject mainObject = jsonObject.getJSONObject("main");

		    	               
		    	                	double humidity = jsonObject.getJSONObject("main").getDouble("humidity");
		    	                	double pressure = jsonObject.getJSONObject("main").getDouble("pressure");
		    	                	System.out.println("Humidity in " +city+" : "+ humidity + "%");
		    	                    System.out.println("Pressure in " + city+" : "+pressure + " hPa");   
		    	        } 
		    	        catch (Exception e) 
		    	        {
		    	            e.printStackTrace();
		    	        }
		    	    }
		    	
		    	 
		    	 
		    	 
		    	 public static void displaySunriseAndSunset(String json) {
		    	        try {
		    	            JSONObject jsonObject = new JSONObject(json);
		    	            
		    	            JSONObject sysObject = jsonObject.getJSONObject("sys");

		    	               
		    	            long sunriseTimestamp = sysObject.getLong("sunrise");
		                    long sunsetTimestamp = sysObject.getLong("sunset");

		                    Instant sunriseInstant = Instant.ofEpochSecond(sunriseTimestamp);
		                    Instant sunsetInstant = Instant.ofEpochSecond(sunsetTimestamp);

		                    ZoneId zoneId = ZoneId.of("UTC");

		                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneId);

		                    String sunriseTime = formatter.format(sunriseInstant);
		                    String sunsetTime = formatter.format(sunsetInstant);

		                    System.out.println("Sunrise time : " + sunriseTime);
		                    System.out.println("Sunset time  : " + sunsetTime);
		    	               
		    	        } 
		    	        catch (Exception e) 
		    	        {
		    	            e.printStackTrace();
		    	        }
		    	    }
		    	
		    	
		    	 
		    	 public static void displayCloudCover(String json,String city) {
		    	        try {
		    	            JSONObject jsonObject = new JSONObject(json);

		    	            if (jsonObject.has("clouds")) {
		    	                JSONObject cloudsObject = jsonObject.getJSONObject("clouds");

		    	                if (cloudsObject.has("all")) {
		    	                    int cloudCoverPercentage = cloudsObject.getInt("all");

		    	                    System.out.println("Cloud Cover in "+city+": " + cloudCoverPercentage + "%");
		    	                } else {
		    	                    System.out.println("Cloud Cover information not found in the JSON.");
		    	                }
		    	            } else {
		    	                System.out.println("Clouds information not found in the JSON.");
		    	            }
		    	        } catch (Exception e) {
		    	            e.printStackTrace();
		    	        }
		    	 }
		    	        
		    	        
		    	 
		    	 public static String getAirPollutionData() throws JSONException, Exception {
			    	    try {
			    	        // Prompt the user to enter the city name
			    	        System.out.print("\nEnter the city name: ");
			    	        Scanner sc = new Scanner(System.in);
			    	        String city = sc.next();

			    	        // Fetch current weather data for the specified city
			    	        JSONObject jsonObject = new JSONObject(getWeatherData(city));

			    	        // Extract latitude and longitude coordinates from the current weather data
			    	        JSONObject coordObject = jsonObject.getJSONObject("coord");
			    	        double latitude = (double) coordObject.get("lat");
			    	        double longitude = (double) coordObject.get("lon");

			    	        // Construct the API URL for retrieving five-day weather forecast dat
			    	      

			    	        // Construct the URL using variables
			    	        String baseUrl = "https://api.openweathermap.org/data/2.5/air_pollution";
			    	        String apiUrl = baseUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;

			    	        URL url = new URL(apiUrl);
			    	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			    	        try {
			    	            // Read the response from the API and build a StringBuilder to store it
			    	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    	            StringBuilder response = new StringBuilder();
			    	            String line;

			    	            while ((line = reader.readLine()) != null) {
			    	                response.append(line);
			    	            }

			    	            // Return the JSON string containing the five-day weather forecast data
			    	            return response.toString();
			    	        } finally {
			    	            // Ensure that the connection is closed, even in case of exceptions
			    	            connection.disconnect();
			    	        }
			    	    } catch (Exception e) {
			    	        // Handle exceptions related to API requests and JSON parsing
			    	        e.printStackTrace();
			    	        throw e; // Re-throw the exception for the caller to handle
			    	    }
			    	}
     
		    	        
		    	 public static void displayAirPollution(String jsonData) {
		    	        try {
		    	            // Parse the JSON data
		    	            JSONObject jsonObject = new JSONObject(jsonData);

		    	            // Extract air pollution data
		    	            JSONArray list = jsonObject.getJSONArray("list");
		    	            if (list.length() > 0) {
		    	                JSONObject pollutionData = list.getJSONObject(0);

		    	                JSONObject components = pollutionData.getJSONObject("components");

		    	                double co = components.getDouble("co");
		    	                double no = components.getDouble("no");
		    	                double no2 = components.getDouble("no2");
		    	                double o3 = components.getDouble("o3");
		    	                double so2 = components.getDouble("so2");
		    	                double pm2_5 = components.getDouble("pm2_5");
		    	                double pm10 = components.getDouble("pm10");
		    	                double nh3 = components.getDouble("nh3");

		    	                System.out.println("Air Pollution Data:");
		    	                System.out.println("CO: " + co);
		    	                System.out.println("NO: " + no);
		    	                System.out.println("NO2: " + no2);
		    	                System.out.println("O3: " + o3);
		    	                System.out.println("SO2: " + so2);
		    	                System.out.println("PM2.5: " + pm2_5);
		    	                System.out.println("PM10: " + pm10);
		    	                System.out.println("NH3: " + nh3);
		    	            } else {
		    	                System.out.println("No air pollution data found.");
		    	            }
		    	        } catch (Exception e) {
		    	            e.printStackTrace();
		    	        }
		    	    }
		    	 
		    	 
		    	 
		    	 public static void displayWindInfo(String jsonData) {
		    	        try {
		    	            // Parse the JSON data
		    	            JSONObject jsonObject = new JSONObject(jsonData);

		    	            // Extract wind data
		    	            JSONObject wind = jsonObject.getJSONObject("wind");

		    	            double windSpeed = wind.getDouble("speed");
		    	            double windDegree = wind.getDouble("deg");
		    	            double windGust = wind.getDouble("gust");

		    	            System.out.println("Wind Information:");
		    	            System.out.println("Wind Speed: " + windSpeed + " m/s");
		    	            System.out.println("Wind Degree: " + windDegree + " degrees");
		    	            System.out.println("Wind Gust: " + windGust + " m/s");
		    	        } catch (Exception e) {
		    	            e.printStackTrace();
		    	        }
		    	    }






		    	        
		    	        
		    	        
		    	        
		    	        
		    	 






		    	
		    	
}
		            
		            
		    	
		    	


		    
		    

