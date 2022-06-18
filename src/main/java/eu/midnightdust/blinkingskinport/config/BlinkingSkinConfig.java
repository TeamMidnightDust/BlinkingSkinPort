package eu.midnightdust.blinkingskinport.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlinkingSkinConfig extends MidnightConfig {

    @Entry public static boolean enabled = false;

    @Entry public static String player = ""; // Optional: Only enable the mod when the name matches the currently active account. Useful for users with multiple minecraft accounts
    @Entry public static List<String> serverBlocklist = new ArrayList<>();
    @Entry public static boolean useBlocklistAsAllowlist = false;

    @Comment public static Comment spacer;
    @Entry public static boolean capeEnabled = true;
    @Entry public static boolean hatEnabled = true;
    @Entry public static boolean jacketEnabled = true;
    @Entry public static boolean leftSleeveEnabled = true;
    @Entry public static boolean rightSleeveEnabled = true;
    @Entry public static boolean leftPantsLegEnabled = true;
    @Entry public static boolean rightPantsLegEnabled = true;
    @Comment public static Comment spacer2;
    @Comment public static Comment disclaimer; // Blink Intervals lower than 5 are disabled, because they can be used to trigger epileptic episodes, which isn't fun for affected people!
    @Comment public static Comment disclaimer2;
    @Entry(min = 5, max = 1000) public static int capeBlinkInterval = 20;
    @Entry(min = 5, max = 1000) public static int hatBlinkInterval = 20;
    @Entry(min = 5, max = 1000) public static int jacketBlinkInterval = 20;
    @Entry(min = 5, max = 1000) public static int leftSleeveBlinkInterval = 20;
    @Entry(min = 5, max = 1000) public static int rightSleeveBlinkInterval = 20;
    @Entry(min = 5, max = 1000) public static int leftPantsLegBlinkInterval = 20;
    @Entry(min = 5, max = 1000) public static int rightPantsLegBlinkInterval = 20;

    public static boolean isEnabled(PlayerModelPart part) {
        return switch (part) {
            case HAT -> hatEnabled;
            case CAPE -> capeEnabled;
            case JACKET -> jacketEnabled;
            case LEFT_SLEEVE -> leftSleeveEnabled;
            case RIGHT_SLEEVE -> rightSleeveEnabled;
            case LEFT_PANTS_LEG -> leftPantsLegEnabled;
            case RIGHT_PANTS_LEG -> rightPantsLegEnabled;
        };
    }

    public static int getToggleInterval(PlayerModelPart part) {
        return switch (part) {
            case HAT -> hatBlinkInterval;
            case CAPE -> capeBlinkInterval;
            case JACKET -> jacketBlinkInterval;
            case LEFT_SLEEVE -> leftSleeveBlinkInterval;
            case RIGHT_SLEEVE -> rightSleeveBlinkInterval;
            case LEFT_PANTS_LEG -> leftPantsLegBlinkInterval;
            case RIGHT_PANTS_LEG -> rightPantsLegBlinkInterval;
        };
    }
    public static boolean isBlocklisted(String address) {
        if (serverBlocklist.isEmpty()) return useBlocklistAsAllowlist;
        return serverBlocklist.contains(address) != useBlocklistAsAllowlist;
    }

}
