package com.crossover.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestProperties
{
    public static Map<String, Properties> propertiesMap = null;

    public static Map<String, Properties> readPropertiesFile() throws ClassCastException, IOException
    {
        propertiesMap = new HashMap<>();
        propertiesMap.put(Constants.TEST_METADATA_FILENAME, getFile(Constants.TEST_METADATA_FILENAME));
        propertiesMap.put(Constants.PAGES_URL_FILENAME, getFile(Constants.PAGES_URL_FILENAME));

        return propertiesMap;
    }

    public static Properties getFile(String fileName) throws IOException
    {
        InputStream inputStream = null;
        Properties fileProperties = new Properties();
        File file = null;

        if (TestProperties.class.getClassLoader().getResource(fileName) != null)
        {
            file = new File(TestProperties.class.getClassLoader().getResource(fileName).getFile());

            if (file.length() > 0)
            {
                inputStream = new FileInputStream(file);
            }
            else
            {
                throw new IOException();
            }
        }
        else
        {
            throw new IOException();
        }

        if (inputStream != null)
        {
            fileProperties.load(inputStream);
            inputStream.close();
        }
        return fileProperties;
    }
}
