package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

public class ConfigFileReader {
	public Properties properties;
	
	public Properties init() {
		BufferedReader reader;

        String propertyFilePath = new File("config/Environment.properties").getAbsolutePath();

		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();

			try {
				properties.load(reader);
				reader.close();
			} 
            
            catch (IOException e) {
				e.printStackTrace();
			}
		} 
        
        catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
        
        return properties;
	}
}