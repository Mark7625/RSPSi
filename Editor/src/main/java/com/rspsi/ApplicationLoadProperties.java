package com.rspsi;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationLoadProperties {

    public static boolean skipLaunchWindow = false;
    public static boolean printDebugConsole = false;
    public static String cacheLocation = "";
    public static String xteaLocation = "";

    public static void load(String propertiesFilePath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            props.load(fis);

            skipLaunchWindow = Boolean.parseBoolean(props.getProperty("skipLaunchWindow", "false"));
            cacheLocation = props.getProperty("cacheLocation", "");
            xteaLocation = props.getProperty("xteaLocation", "");
            printDebugConsole = Boolean.parseBoolean(props.getProperty("printDebugConsole", "false"));
            if (cacheLocation.isEmpty()) {
                skipLaunchWindow = false;
                System.out.println("Making Launch Window Show as its not missing");
            }
        } catch (IOException e) {
            new File(propertiesFilePath).createNewFile();
            System.out.println("Could not load properties file: " + propertiesFilePath + ". Using defaults.");
        }
    }

}