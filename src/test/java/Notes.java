import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Notes {

    @Test
    public void readingFromPropertiesFile() throws IOException {

        Properties properties = new Properties();
        //  String path = "configuration.properties";
        FileInputStream file = new FileInputStream("configuration.properties");
        properties.load(file);
        System.out.println("properties.getProperty(\"browser\") = " + properties.getProperty("browser"));
        System.out.println("properties.getProperty(\"username\") = " + properties.getProperty("username"));

        file.close();
    }
}
