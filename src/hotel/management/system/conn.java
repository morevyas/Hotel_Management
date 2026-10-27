package hotel.management.system;

import java.sql.*;

/** Shared JDBC connection. Configure HOTEL_DB_URL, HOTEL_DB_USER and HOTEL_DB_PASSWORD. */
public class conn implements AutoCloseable {
    public Connection c;
    public Statement s;
    public conn() {
        String url=env("HOTEL_DB_URL","jdbc:mysql://localhost:3306/hotelmanagementsystem1?useSSL=false&serverTimezone=UTC");
        String user=env("HOTEL_DB_USER","root");
        String password=env("HOTEL_DB_PASSWORD","");
        try { Class.forName("com.mysql.cj.jdbc.Driver"); c=DriverManager.getConnection(url,user,password); s=c.createStatement(); }
        catch (ClassNotFoundException e) { System.err.println("MySQL Connector/J is missing from the project classpath."); e.printStackTrace(); }
        catch (SQLException e) { System.err.println("Database connection failed. Check MySQL service, database and HOTEL_DB_* settings."); e.printStackTrace(); }
    }
    private static String env(String key,String fallback){String value=System.getenv(key);return value==null?fallback:value;}
    public void close(){try{if(s!=null)s.close();}catch(SQLException ignored){}try{if(c!=null)c.close();}catch(SQLException ignored){}}
}
