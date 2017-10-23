package SQLAPI;

import utilities.BuildUtils;
import utilities.Debug;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQL extends BuildUtils {

    private String host,user,pass,database;


    public String getHost() {
        return host;
    }


    public String getUser() {
        return user;
    }


    public String getPassword() {
        return pass;
    }


    public String getDatabase() {
        return database;
    }


    public void setHost(String host) {
        this.host = host;
    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }


    public void setDatabase(String database) {
        this.database = database;
    }


//    public SQLData() {}

     public Connection connection;

    public SQL(boolean enabled,String ip, String userName, String access, String db) {

        if(enabled)
        {

            this.host = ip;
            this.user = userName;
            this.pass = access;
            this.database = db;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
                Debug.log("&4Connection successful.",1);
            } catch (Exception e) {
                exceptionDebug(e);
            }
        }else
        {
            Debug.log(Debug.LOG+"&4Database has been disabled in config.yml!",1);
        }
    }

   public Connection getConnection() {
        return connection;
    }


}