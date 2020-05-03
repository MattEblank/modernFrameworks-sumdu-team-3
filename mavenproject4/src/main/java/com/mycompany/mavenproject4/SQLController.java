package com.mycompany.mavenproject4;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public boolean addNewInstance(Subject newSubject, SQLUser user)
    {
        String[] credentials = user.getCredentials();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(user.getURL(),
                credentials[1], credentials[2]);
            
            Statement statement = connection.createStatement();
            statement.executeQuery("SET SQL_SAFE_UPDATES = 0");
            
            String query = "INSERT INTO " + user.getDBName() +""
                    + " (name) values(\"" + newSubject.getSubjectName() +"\");";
            statement.executeQuery(query);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    public List<Subject> getAllInstances(SQLUser user)
    {
        List<Subject> subjects = new ArrayList<Subject>();
        String[] credentials = user.getCredentials();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(user.getURL(),
                credentials[1], credentials[2]);
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + user.getDBName() );
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
    
    public boolean deleteInstance(Subject newSubject, SQLUser user)
    {   
        String[] credentials = user.getCredentials();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(user.getURL(),
                credentials[1], credentials[2]);
            
            Statement statement = connection.createStatement();
            statement.executeQuery("SET SQL_SAFE_UPDATES = 0");
            
            String query = "DELETE FROM" + user.getDBName() +""
                    + " WHERE id=" + newSubject.getSubjectID()+";";
            statement.executeUpdate(query);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
