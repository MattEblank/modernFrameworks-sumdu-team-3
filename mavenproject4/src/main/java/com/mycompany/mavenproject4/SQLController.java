package com.mycompany.mavenproject4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matt
 */
public class SQLController {
    private static String url = "jdbc:mysql://localhost:3306/project_db?useUnicode=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static String username = "root";
    private static String password = "root";
    private static volatile SQLController instance;
	
    public static SQLController getInstance() {
            SQLController localInstance = instance;
            if (localInstance == null) {
                    synchronized (SQLController.class) {
                            localInstance = instance;
                            if (localInstance == null) {
                                    instance = localInstance = new SQLController();
                            }
                    }
            }

            return localInstance;
    }
    
    public static boolean addNewInstance(Subject newSubject)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                username, password);
            
            Statement statement = connection.createStatement();
            statement.executeQuery("SET SQL_SAFE_UPDATES = 0");
            
            String query = "INSERT INTO project_db (name) values(\"" + newSubject.name +"\");";
            statement.executeUpdate(query);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
     public static int update(Subject product) {
         
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "UPDATE project_db SET name = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setInt(2, product.getId());
                      
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static Subject selectOne(int id) {
         
        Subject subject = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "SELECT * FROM project_db WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
 
                        int resId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        subject = new Subject(resId, name);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return subject;
    }
    
    public static ArrayList<Subject> getAllInstances()
    {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                username, password);
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from project_db");
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String subjectName = resultSet.getString("name");
                subjects.add(new Subject(id, subjectName));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return subjects;
    }
    
    public static boolean deleteInstance(int id)
    {   
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                username, password);
            
            Statement statement = connection.createStatement();
            statement.executeQuery("SET SQL_SAFE_UPDATES = 0");
            
            String query = "DELETE FROM project_db WHERE id=" + id +";";
            statement.executeUpdate(query);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
