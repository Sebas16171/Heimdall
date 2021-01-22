import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import Scripts.SQL_Connection;
import Scripts.Encrypter;
import UI.Login;

class Main {

    private static SQL_Connection MainConn = new SQL_Connection();
    private static Encrypter Cipher = new Encrypter();
    private static String active_user[] = {"","","","",""};

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        String config[] = m.getProperties();
        m = null;

        Login startup = new Login(MainConn, config[0], config[1]);


        MainConn.connect(config[0], config[1], config[2], config[3]);
        MainConn.scan();
        MainConn.close();
    }

    private String[] getProperties() throws Exception {

        
        String output[] = new String[4];

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
        output[2] = prop.getProperty("db_user");
        output[3] = prop.getProperty("db_passwd");

        return output;
    }
}