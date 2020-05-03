package com.mycompany.mavenproject4;

/**
 *
 * @author Matt
 */
public class Subject {
    
    private int subId;
    private String subName;
    
    public Subject(int id, String name)
    {
        subId = id;
        subName = name;
    }
    
    public String getSubjectName()
    {
        return subName;
    }
    
    public void setSubjectName(String name)
    {
        subName = name;
    }
    
    public int getSubjectID()
    {
        return subId;
    }
    
    public void setSubjectID(int id)
    {
        subId = id;
    }
}
