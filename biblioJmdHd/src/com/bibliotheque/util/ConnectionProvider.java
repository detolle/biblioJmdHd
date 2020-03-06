package com.bibliotheque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection singleConnexion = null;

//	public static BasicDataSource getdataSourceInstance() throws SQLException {
	public static Connection getdataSourceInstance() throws SQLException {
		if (singleConnexion == null) {
        	singleConnexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","livraison","livraison");	        	
		}
		// return singledatasource.getConnection();
		return singleConnexion;
	}

}
