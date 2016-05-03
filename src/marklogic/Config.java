package marklogic;

import java.io.IOException;
import java.util.Properties;

import java.io.InputStream;
import com.marklogic.client.DatabaseClientFactory.Authentication;

public class Config {

    public static Properties props = loadProperties();
    
    public static String host = props.getProperty("EmailApp.host");
    
    public static String DbName = props.getProperty("EmailApp.DbName");

    
    public static int port = Integer.parseInt(props.getProperty("EmailApp.port"));
    
    public static String user = props.getProperty("EmailApp.writer_user");
    
    public static String password = props.getProperty("EmailApp.writer_password");
    
    public static String admin_user = props.getProperty("EmailApp.admin_user");
    
    public static String admin_password = props.getProperty("EmailApp.admin_password");
    
    public static Authentication authType = Authentication.valueOf(
                                                props.getProperty("EmailApp.authentication_type").toUpperCase()
                                                );

    // get the configuration for the EmailApp
    private static Properties loadProperties() {                           
        try {
                                 String propsName = "config.properties";
                                 InputStream propsStream =
                                                (InputStream) Config.class.getClassLoader().getResourceAsStream(propsName);
                                 if (propsStream == null)
                                                throw new IOException("Could not read config properties");

                                 Properties props = new Properties();
                                 props.load(propsStream);

                                 return props;

        } catch (final IOException exc) {
            throw new Error(exc);
        }  
    }
}
