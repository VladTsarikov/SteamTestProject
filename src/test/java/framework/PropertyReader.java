package framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

    private final static String FILE_PATH = "src/test/java/resources/%s.properties";
    private static String filePath = "src/test/java/resources/config.properties";
    private static FileInputStream fileIS;
    private static Properties properties;

    private static Map<String,String> propertiesMap = new HashMap<>();

    public static void initProperty() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "CP1251"));
            properties = new Properties();
            properties.load(br);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileIS != null)
                try {
                    fileIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        readProperties();

    }

    /**
     * changing path of property file after reading config.property
     *
     */
    public static void changePath(){
        String language = propertiesMap.get("PagesLanguage");
        String lang = language.toLowerCase();
        setFilePath(String.format(FILE_PATH,lang));

    }

    private static void setFilePath(String filePath) {
        PropertyReader.filePath = filePath;
    }

    /**
     *
     * @param property
     * @return information from properties
     */
    public static String getProperty(String property){

        return propertiesMap.get(property);

    }

    private static void readProperties(){

        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            propertiesMap.put(key,value);
        }

    }

}
