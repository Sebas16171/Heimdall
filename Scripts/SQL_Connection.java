package Scripts;

import java.sql.*;

public class SQL_Connection {

    Connection con = null;

    public void prepare(){
        String sql_users = "CREATE TABLE IF NOT EXISTS `heimdall`.`users` "
                + "( `Username` VARCHAR(50) NOT NULL , `Passwd` VARCHAR(100) NOT NULL , PRIMARY KEY (`Username`(50))) ENGINE = InnoDB;";
    
        String sql_data = "CREATE TABLE IF NOT EXISTS `heimdall`.`data` "
                + "( `id` INT(64) NOT NULL AUTO_INCREMENT , `owner` VARCHAR(128) NOT NULL , `passwd` VARCHAR(156) NOT NULL , `username` "
                + "VARCHAR(128) NOT NULL , `domain` VARCHAR(128) NOT NULL , `updated` DATE NOT NULL , PRIMARY KEY (`id`), INDEX `username` (`username`)) ENGINE = InnoDB;";

    }

    public void Create_User(String user){
        String create_user = String.format("CREATE USER '%s'@'%' IDENTIFIED VIA mysql_native_password USING '***';GRANT SELECT, INSERT, UPDATE, DELETE, FILE ON *.* TO '%s'@'%' "
                + "REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;", user, user);
    }



    public boolean connect(String server, String database, String username, String passwd){
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", server, database), username, passwd);
            
            /*Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * FROM `data`");  
            
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                con.close();*/
            return true;
        }catch(Exception e){
            //System.out.println(e);
            return false;
        }  

    }
}
