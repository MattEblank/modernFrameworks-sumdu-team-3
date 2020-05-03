package com.mycompany.mavenproject4;

/**
 *
 * @author Matt
 */
public class SQLUser {
    
    private final String dbURL;
    private final String username;
    private final String password;
    private final String dbName;
    
    public SQLUser(String url,String name,String pass,String db)
    {
        dbURL = url;
        username = name;
        password = pass;
        dbName = db;
    }
    
    public String getURL()
    {
        return dbURL;
    }
    
    public String[] getCredentials()
    {
        return new String[] {username, password};
    }
    
    public String getDBName()
    {
        return dbName;
    }
}
