package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQlConnection {
    //atributos
    private Properties p;
    private Connection connection;

    //construtor
    public MySQlConnection() {
        setConnection();
    }

    //metodo para fazer a ligaçao
    public void setConnection() {
        p = new Properties();
        try {
            InputStream input = new FileInputStream("dbConfig.properties");
            p.load(input);
            connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
            System.out.println("Ligado à DB");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um problema");
        }
    }

}

