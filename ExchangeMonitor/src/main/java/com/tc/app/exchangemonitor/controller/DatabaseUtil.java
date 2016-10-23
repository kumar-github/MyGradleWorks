package com.tc.app.exchangemonitor.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseUtil
{
	private static final Logger LOGGER = LogManager.getLogger(DatabaseUtil.class);
	public static boolean makeTestConnection(String url, String username, String password) throws SQLException
	{
		boolean isSuccess = false;
		//String driver = "net.sourceforge.jtds.jdbc.Driver";
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(url, username, password);
			if(connection != null)
			{
				isSuccess = true;
			}
		}
		catch (SQLException exception)
		{
			LOGGER.error(exception);
			throw exception;
		}

		return isSuccess;
	}
}