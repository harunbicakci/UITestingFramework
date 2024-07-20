package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {

    private static Properties prop;

    // this method will read the properties file
    public static void readProperties(String filePath){

        try{
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // this method will return the valye for a specific key
    public static String getProperty(String key){
        return prop.getProperty(key);
    }

}
