package com.ibm.ucd.sample.automation.plugin;

import java.util.Properties;

public class FirstStep {

    private final String userName;
    private final String age;
    private final String bio;

    public FirstStep(Properties inputProperties) {
        userName = inputProperties.getProperty("userName").trim();
        age = inputProperties.getProperty("age").trim();
        bio = inputProperties.getProperty("bio").trim();
    }

    public static void main(String[] args)
    {
        int returnCode = 0;
        try {
            Properties inputProperties = Util.readProperties(args[0]);
            new FirstStep(inputProperties).execute();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            returnCode = 1;
        }
        System.exit(returnCode);
    }

    private void execute() {
        System.out.println("Username is " + userName);
        System.out.println("age is " + age);
        System.out.println("bio is " + bio);
    }
}