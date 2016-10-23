package com.tc.app.exchangemonitor.util;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import javafx.fxml.Initializable;

public class ApplicationHelper
{
	public static ClassToInstanceMap<Initializable> controllersMap = MutableClassToInstanceMap.create();
}