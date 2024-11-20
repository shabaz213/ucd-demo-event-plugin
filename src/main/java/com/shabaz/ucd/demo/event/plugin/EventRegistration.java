package com.shabaz.ucd.demo.event.plugin;

import java.util.Properties;

public class EventRegistration {

    private enum Role {
        ATTENDEE,
        SPEAKER,
        VOLUNTEER
    }

    private static final Properties outputProperties = new Properties();

    private final String name;
    private final String additionalRequests;
    private final boolean acceptedRulesAndRegulations;
    private final Role role;

    public EventRegistration(Properties inputProperties) {
        name = inputProperties.getProperty("name").trim();
        additionalRequests = inputProperties.getProperty("additionalRequests").trim();
        acceptedRulesAndRegulations =
            Boolean.parseBoolean(inputProperties.getProperty("acceptRulesAndRegulations"));
        role = Role.valueOf(inputProperties.getProperty("role"));
    }

    public static void main(String[] args)
    {
        int returnCode = 0;
        try {
            Properties inputProperties = Util.readProperties(args[0]);
            new EventRegistration(inputProperties).execute();
            if (!outputProperties.isEmpty()) {
                Util.writeProperties(args[1], outputProperties);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            returnCode = 1;
        }
        System.exit(returnCode);
    }

    private void execute() {
        if (!acceptedRulesAndRegulations) {
            throw new IllegalArgumentException("Please accept rules and regulations to register for the event.");
        }

        System.out.println("User Details:");
        System.out.println("==============================================================");
        System.out.println("Name - " + name);
        System.out.println("Additional Requests - \n" + additionalRequests);
        System.out.println("Role - " + role.toString());

        System.out.println();
        System.out.println();

        // For simplicity the below registration ID is hard-coded
        String registrationId = "ETL132462";
        System.out.println("Your registration id is " + registrationId);

        outputProperties.setProperty("registrationId", registrationId);
    }
}