package com.tc.app.exchangemonitor.controller;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesUtil
{
	private static final Preferences USER_PREFERENCES;
	static
	{
		USER_PREFERENCES = Preferences.userNodeForPackage(PreferencesUtil.class);
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
			PreferencesUtil.getUserPreferences().clear();
		}
		catch (BackingStoreException exception)
		{
			exception.printStackTrace();
		}
	}
}