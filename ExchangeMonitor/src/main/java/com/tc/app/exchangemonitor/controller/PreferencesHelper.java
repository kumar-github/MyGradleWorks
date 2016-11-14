package com.tc.app.exchangemonitor.controller;

import java.util.prefs.Preferences;

import com.tc.app.exchangemonitor.util.StaticConstantsHelper;

public class PreferencesHelper
{
	private static final Preferences USER_PREFERENCES;
	static
	{
		USER_PREFERENCES = Preferences.userNodeForPackage(PreferencesHelper.class);
		//USER_PREFERENCES = Preferences.userRoot();
	}

	public static Preferences getUserPreferences()
	{
		return USER_PREFERENCES;
	}

	public static void forgetLoginCredentials()
	{
		try
		{
			//PreferencesUtil.getUserPreferences().removeNode();
			//PreferencesHelper.getUserPreferences().clear();
			PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.IS_AUTHENTICATED_USER, false);
			PreferencesHelper.getUserPreferences().put(StaticConstantsHelper.CONNECTION_URL, "");
			PreferencesHelper.getUserPreferences().put(StaticConstantsHelper.DATABASE_NAME, "");
			PreferencesHelper.getUserPreferences().put(StaticConstantsHelper.SERVER_NAME, "");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}