package com.tc.app.exchangemonitor.util;

public class FilePathUtil
{
	private static final String FXML_FILE_EXTENSION = ".fxml";
	private static final String CSS_FILE_EXTENSION = ".css";
	private static final String CONFIGURATION_FILE = "Configuration.properties";
	
	public static String getFXMLFileForClass(Class<?> clazz)
	{
		String fxmlFile = "";
		if(clazz != null)
		{
			String fxmlFileName = clazz.getSimpleName() + FXML_FILE_EXTENSION;
			String javaPackageName = getPackageNameForClass(clazz);
			String fxmlPackageName = convertToFXMLPackageName(javaPackageName);
			String parsedFXMLPackageName = parseAndGetUsablePackageName(fxmlPackageName);
			fxmlFile = parsedFXMLPackageName + fxmlFileName;
		}
		return fxmlFile;
	}
	
	public static String getCSSFileForClass(Class<?> clazz)
	{
		String cssFile = "";
		if(clazz != null)
		{
			String cssFileName = clazz.getSimpleName() + CSS_FILE_EXTENSION;
			String javaPackageName = getPackageNameForClass(clazz);
			String cssPackageName = convertToCSSPackageName(javaPackageName);
			String parsedCSSPackageName = parseAndGetUsablePackageName(cssPackageName);
			cssFile = parsedCSSPackageName + cssFileName;
			
		}
		return cssFile;
	}
	
	public static String getResourceBundlePropertiesFileForClass(Class<?> clazz)
	{
		String resourceBundlePropertiesFile = "";
		if(clazz != null)
		{
			String javaPackageName = getPackageNameForClass(clazz);
			String propsPackageName = convertToPROPSPackageName(javaPackageName);
			//parsedPROPSPackageName = parseAndGetUsablePackageName(propsPackageName);
			resourceBundlePropertiesFile = propsPackageName + "." + clazz.getSimpleName();
		}
		return resourceBundlePropertiesFile;
	}
	
	public static String getViewSpecificPropertiesFileForClass(Class<?> clazz)
	{
		String propertiesFile = "";
		if(clazz != null)
		{
			String controllerFileName = clazz.getSimpleName();
			String veiwFileName = getViewNameForControllerName(controllerFileName);
			String javaPackageName = getPackageNameForClass(clazz);
			String propsPackageName = convertFromControllerPackageToPROPSPackageName(javaPackageName);
			String parsedPROPSPackageName = parseAndGetUsablePackageName(propsPackageName);
			propertiesFile = parsedPROPSPackageName + veiwFileName + CONFIGURATION_FILE;
		}
		return propertiesFile;
	}
	
	private static String getPackageNameForClass(Class<?> clazz)
	{
		return clazz.getPackage().getName();
	}
	
	private static String convertToFXMLPackageName(String packageName)
	{
		return packageName.replace("java", "fxml");
	}
	
	private static String convertToCSSPackageName(String packageName)
	{
		return packageName.replace("java", "css");
	}
	
	private static String convertToPROPSPackageName(String packageName)
	{
		return packageName.replace("java", "props");
	}
	
	private static String convertFromControllerPackageToPROPSPackageName(String packageName)
	{
		return packageName.replace("controller","view.props");
	}
	
	private static String convertToRespectivePackageName(String fromPackageName, String toPackage)
	{
		return fromPackageName.replace("java", toPackage);
	}
	
	private static String convertFromPackageToToPackage(String packageName, String fromPackage, String toPackage)
	{
		return packageName.replace(fromPackage, toPackage);
	}
	
	private static String parseAndGetUsablePackageName(String packageName)
	{
		String starting = "/";
		String ending = "/";
		String parsedPackageName = "";
		parsedPackageName = starting + packageName.replace(".", "/") + ending;	
		return parsedPackageName;
	}
	
	private static String getViewNameForControllerName(String controllerName)
	{
		String viewFileName = "";
		
		viewFileName = controllerName.replace("Controller", "View");
		
		return viewFileName;
	}
}