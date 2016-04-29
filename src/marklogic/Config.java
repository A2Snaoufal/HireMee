package marklogic;

import java.io.IOException;
import java.util.Properties;

import java.io.InputStream;
import com.marklogic.client.DatabaseClientFactory.Authentication;

public class Config {

    public static Properties props = loadProperties();
    
    protected static String host = props.getProperty("EmailApp.host");
    
    protected static String DbName = props.getProperty("EmailApp.DbName");

    
    protected static int port = Integer.parseInt(props.getProperty("EmailApp.port"));
    
    protected static String user = props.getProperty("EmailApp.writer_user");
    
    protected static String password = props.getProperty("EmailApp.writer_password");
    
    protected static String admin_user = props.getProperty("EmailApp.admin_user");
    
    protected static String admin_password = props.getProperty("EmailApp.admin_password");
    
    protected static Authentication authType = Authentication.valueOf(
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
