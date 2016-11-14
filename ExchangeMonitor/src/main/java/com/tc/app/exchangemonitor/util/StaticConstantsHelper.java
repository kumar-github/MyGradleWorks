package com.tc.app.exchangemonitor.util;

public interface StaticConstantsHelper
{
	public static final String IS_AUTHENTICATED_USER = "isAuthenticatedUser";
	public static final String CONNECTION_URL_FORMAT = "jdbc:jtds:sqlserver://{0};databaseName={1}";
	public static final String CONNECTION_URL = "connectionURL";
	public static final String SERVER_NAME = "serverName";
	public static final String DATABASE_NAME = "databaseName";
	public static final String HIBERNATE_CONNECTION_URL_KEY = "hibernate.connection.url";

	public static final String SHOULD_DISPLAY_ACCOUNTS_WITH_PERMISSION = "ShouldDisplayAccountsWithPermission";
	public static final String SHOULD_ENABLE_ROW_COLORS = "ShouldEnableRowColors";
	public static final String SHOULD_ENABLE_ANIMATIONS = "ShouldEnableAnimations";
	public static final String SHOULD_ENABLE_ROW_TOOLTIPS = "ShouldEnableRowToolTips";
	public static final String SHOULD_ENABLE_ROW_CONTEXT_MENU = "ShouldEnableRowContextMenu";
}