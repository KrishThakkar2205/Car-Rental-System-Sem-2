package com.cls;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConeection {
	
	
		static Connection getConnection() {
		Connection connection=null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental", "root","");
            
        }catch (Exception e) {
            System.out.println(e);
        }
	    return connection;
	}
	
	
}
