package com.tc.framework.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

import com.tc.app.exchangemonitor.util.FilePathUtil;

public class Configurator
{
	 //public final static String CONFIGURATION_FILE = "Configuration.properties";

    private final Properties systemProperties;
    private Function<Object, Object> customConfigurator;

    private Consumer<String> LOG;

    public Configurator()
    {
        this.systemProperties = System.getProperties();
        this.LOG = l -> {};
    }

    public Configurator set(Function<Object, Object> custom)
    {
        this.customConfigurator = custom;
        return this;
    }

    public Configurator setLogger(Consumer<String> log)
    {
        this.LOG = log;
        return this;
    }

    private Properties getProperties(Class<?> clazz)
    {
    	Properties configuration = null;
    	//try (InputStream stream = clazz.getResourceAsStream(CONFIGURATION_FILE))
    	try (InputStream stream = clazz.getResourceAsStream(FilePathUtil.getViewSpecificPropertiesFileForClass(clazz)))
        {
            if (stream == null)
            {
                return null;
            }
            configuration = new Properties();
            configuration.load(stream);
        } catch (IOException ex) {
            //a property file does not have to exist...
        }
        return configuration;
    }

    /**
     *
     * @param clazz is used to locate the properties @see
     * java.lang.Class#getResourceAsStream
     * @param key
     * @return
     */
    public Object getProperty(Class<?> clazz, Object key)
    {
        Object value = this.systemProperties.get(key);
        if (value != null) {
            return value;
        }
        if (customConfigurator != null) {
            value = customConfigurator.apply(key);
            if (value != null) {
                return value;
            }
        }
        Properties clazzProperties = this.getProperties(clazz);
        if (clazzProperties != null) {
            value = clazzProperties.get(key);
        }
        return value;
    }

    public void forgetAll()
    {
        this.customConfigurator = null;
    }
}