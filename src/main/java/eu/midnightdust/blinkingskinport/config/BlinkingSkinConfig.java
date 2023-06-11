package eu.midnightdust.blinkingskinport.config;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlinkingSkinConfig extends MidnightConfig {
    public static final String general = "general";
    public static final String parts = "parts";
    public static final String intervals = "intervals";

    @Entry(category = general) public static boolean enabled = false;

    @Entry(category = general) @Hidden public static String player = "";
    @Entry(category = general) public static List<String> playerAllowList = Lists.newArrayList(player); // Optional: Only enable the mod when one of the names matches the currently active account. Useful for users with multiple minecraft accounts
    @Entry(category = general) public static List<String> serverBlocklist = new ArrayList<>();
    @Entry(category = general) public static boolean useBlocklistAsAllowlist = false;
    @Entry(category = parts) public static boolean capeEnabled = true;
    @Entry(category = parts) public static boolean hatEnabled = true;
    @Entry(category = parts) public static boolean jacketEnabled = true;
    @Entry(category = parts) public static boolean leftSleeveEnabled = true;
    @Entry(category = parts) public static boolean rightSleeveEnabled = true;
    @Entry(category = parts) public static boolean leftPantsLegEnabled = true;
    @Entry(category = parts) public static boolean rightPantsLegEnabled = true;
    @Comment(category = intervals) public static Comment disclaimer; // Blink Intervals lower than 5 are disabled, because they could trigger epileptic episodes, which isn't fun for affected people!
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int capeBlinkInterval = 20;
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int hatBlinkInterval = 20;
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int jacketBlinkInterval = 20;
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int leftSleeveBlinkInterval = 20;
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int rightSleeveBlinkInterval = 20;
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int leftPantsLegBlinkInterval = 20;
    @Entry(category = intervals, min = 5, max = 250, isSlider = true) public static int rightPantsLegBlinkInterval = 20;

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
