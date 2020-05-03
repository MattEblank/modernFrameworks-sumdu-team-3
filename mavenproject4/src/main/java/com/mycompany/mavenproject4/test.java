package com.mycompany.mavenproject4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/")
public class test extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try{
            
            String url = "jdbc:mysql://localhost:3306/project_db?useUnicode=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,
                    username, password);
            
            writer.println("Connection to ProductDB succesfull!");
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
        
        
    }
}