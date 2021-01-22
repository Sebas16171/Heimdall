package Scripts;

import java.sql.*;

public class SQL_Connection {

    Connection con = null;

    public void prepare() throws SQLException {

        String sql_querys[] = {
            "create database heimdall;",
            "CREATE TABLE IF NOT EXISTS `heimdall`.`user` ( `id` INT NOT NULL AUTO_INCREMENT , `user` VARCHAR(50) NOT NULL , `name` VARCHAR(50) NOT NULL , `lst_name` VARCHAR(50) NOT NULL , `crpt_method` INT NOT NULL DEFAULT '1' , PRIMARY KEY (`id`)) ENGINE = InnoDB;",
            "CREATE TABLE `heimdall`.`data` ( `id` INT NOT NULL AUTO_INCREMENT , `owner` INT NOT NULL , `domain` VARCHAR(100) NOT NULL , `username` VARCHAR(100) NOT NULL , `password` VARCHAR(100) NOT NULL , `updated` DATE NOT NULL , PRIMARY KEY (`id`), CONSTRAINT FOREIGN KEY fk_owner (`owner`) REFERENCES user (`id`)) ENGINE = InnoDB"
        };

        Statement stmt = con.createStatement();

        stmt.executeUpdate(sql_querys[1]);
        stmt.executeUpdate(sql_querys[2]);
    }

    public void scan() throws SQLException {
        Statement stmt = con.createStatement();

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM `data`");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            prepare();
            scan();
        }

    }

    public void RegisterUser(String UserName, String Name, String LastName, int Method){
        String sql = String.format("INSERT INTO `user` (`user`, `name`, `lst_name`, `crpt_method`) VALUES ('%s', '%s', '%s', '%d')", UserName, Name, LastName, Method);
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

    public String retriever_user_data(){
        String output = "";
        return output;
    }

    public void close() throws SQLException {
        if (!con.isClosed()) {
            con.close();
        }
    }
}
