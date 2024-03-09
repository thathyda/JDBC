package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesLoader {
    public final static Properties properties = new Properties();
    public static void loadPropertiesFile(){
        try(BufferedReader buffRead = new BufferedReader(new FileReader("application.properties"))){
            properties.load(buffRead);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
