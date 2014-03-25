package beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Account {

    private static Properties prop;

    static {
        prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = classLoader.getResource("config/account.properties").getPath();
            input = new FileInputStream(path);
            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getAuthor() {
        return prop.getProperty("mailOwner");
    }

    public static String getFirstContact() {
        return prop.getProperty("contact1");
    }

    public static String getSecondContact() {
        return prop.getProperty("contact2");
    }

    public static int getNumberOfVisibleBodyCharacters() {
        return Integer.valueOf(prop.getProperty("numberOfVisibleBodyCharacters"));
    }
}
