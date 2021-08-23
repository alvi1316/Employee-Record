package javaapplication2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    Connection con;
    
    Database(){
        createConnection();
    }
    
   boolean checkDB(){
        File tempFile = new File("database.db");
        return tempFile.exists();
    }
    
    void createConnection(){       
        try {
            if(checkDB()){
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:database.db");
            }else{
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:database.db");
                String createAdmin = "CREATE TABLE IF NOT EXISTS admin (username TEXT, password TEXT, PRIMARY KEY(username))";
                String createInfo = "CREATE TABLE IF NOT EXISTS info(id INTEGER, name TEXT, salary INTEGER,address TEXT, PRIMARY KEY(id  AUTOINCREMENT))";
                Statement stmt = con.createStatement();
                stmt.execute(createAdmin);
                stmt.execute(createInfo);
                String insertQuery = String.format("INSERT INTO admin(username,password) VALUES('%s','%s')","admin","123");
                stmt.execute(insertQuery);
                stmt.close();
            }                      
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean checkLogin(String username, String password){
        boolean result = false;
        try {
            String str = String.format("SELECT password FROM admin WHERE username = '%s'",username);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            if(rs.next()){
                result = rs.getString("password").equals(password);
            }                                   
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    List<Info> getInfo(){
        List<Info> infoList = new ArrayList<>();
        String query = "SELECT * FROM info";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                    Info info = new Info();
                    info.id = rs.getInt("id");
                    info.name = rs.getString("name");
                    info.salary = rs.getInt("salary");
                    info.address = rs.getString("address");
                    infoList.add(info);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return infoList;
    }
    
    void addInfo(String name, int salary, String address) {        
        try {
            String insertQuery = String.format("INSERT INTO info(name,salary,address) VALUES('%s','%d','%s')",name,salary,address);
            Statement stmt = con.createStatement();
            stmt.execute(insertQuery);        
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean deleteRow(int id) {
        boolean result = false;
        try {
            String del = String.format("DELETE FROM info WHERE id = '%d'",id);
            Statement stmt = con.createStatement();
            if(stmt.executeUpdate(del) > 0){
                result = true;
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
