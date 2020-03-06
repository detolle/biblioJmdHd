package com.bibliotheque.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDeConnection {

	public static void main(String[] args) {
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4 
            //Class.forName(DRIVER_CLASS_NAME);
            
            //MySQL driver MySQL Connector
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?seUnicode=true" + 
//            		"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
//            		"amok","amok");
            //Oracle Driver officiel OJDBC Thin
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","livraison","livraison");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		

	}

}
