package com.mycompany.mavenproject4;

/**
 *
 * @author Matt
 */
public class Subject {
    
    protected int id;
    protected String name;
    
    public Subject(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
}
