package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection connection = null;
	
	//Carregar db.properties
	private static Properties loadProperties() {
		try(FileInputStream fileStream = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fileStream);
			return properties;
			
		}catch(IOException e) {	throw new DbException(e.getMessage()); }
	}
	
	public static Connection getConnection() {
		try {
			if(connection == null) {
				Properties properties = loadProperties();
				String dburl = properties.getProperty("dburl");
				connection = DriverManager.getConnection(dburl);
			}			
		}catch(SQLException e) { throw new DbException("Falha ao tentar estabelecer conexão: " + e.getMessage()); }
		
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch (SQLException e) { throw new DbException("Falha ao tentar fechar conexão: " + e.getMessage()); }
	}
	
	public static void closeStatement(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
}//Fim Classe