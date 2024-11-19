package com.ibm.ucd.sample.automation.plugin;

import java.util.Properties;

public class SecondStep {

    private static final Properties outputProperties = new Properties();

    private final Medal medal;
    private final boolean isTermsAndConditionsAgreed;

    public SecondStep(Properties inputProperties) {
        medal = Medal.valueOf(inputProperties.getProperty("medal"));
        isTermsAndConditionsAgreed =
            Boolean.parseBoolean(inputProperties.getProperty("agreeTermsAndConditions"));
    }

    public static void main(String[] args) {
        int returnCode = 0;
        try {
            Properties inputProperties = Util.readProperties(args[0]);
            new SecondStep(inputProperties).execute();
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
        if (medal == Medal.GOLD)
            outputProperties.setProperty("position", "top");
        else if (medal == Medal.SILVER)
            outputProperties.setProperty("position", "middle");
        else if (medal == Medal.BRONZE)
            outputProperties.setProperty("position", "bottom");
        else
            outputProperties.setProperty("position", "none");

        if (medal != Medal.NONE && !isTermsAndConditionsAgreed)
            throw new IllegalArgumentException("Please agree to the terms and conditions.");
    }

    private enum Medal {
        GOLD,
        SILVER,
        BRONZE,
        NONE
    }
}