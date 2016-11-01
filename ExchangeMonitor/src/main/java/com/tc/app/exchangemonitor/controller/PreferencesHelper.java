package com.tc.app.exchangemonitor.controller;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

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
			PreferencesHelper.getUserPreferences().clear();
		}
		catch (BackingStoreException exception)
		{
			exception.printStackTrace();
		}
	}
}