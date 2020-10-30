import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import UI.*;
import Scripts.*;

class Main {

    private static SQL_Connection MainConn = new SQL_Connection();

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        String config[] = m.getProperties();
        m = null;

        Login startup = new Login(MainConn, config[0], config[1]);
    }

    private String[] getProperties() throws Exception {

        
        String output[] = new String[2];

        Properties prop = new Properties();
        String fileDir = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileDir);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("Configuration file '" + fileDir + "' does not exist");
        }

        output[0] = prop.getProperty("host");
        output[1] = prop.getProperty("database");

        return output;
    }
}