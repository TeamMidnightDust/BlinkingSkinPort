package eu.midnightdust.blinkingskinport.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.ArrayList;
import java.util.List;

@Config(name = "blinkingskinport")
public class BlinkingSkinConfig implements ConfigData {

    @ConfigEntry.Gui.PrefixText // Blink Intervals lower than 5 are disabled, because they can be used to trigger epileptic episodes, which isn't fun for affected people!
    public boolean enabled = false;

    @ConfigEntry.Gui.PrefixText
    public boolean capeEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int capeBlinkInterval = 20;
    public boolean hatEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int hatBlinkInterval = 20;
    public boolean jacketEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int jacketBlinkInterval = 20;
    public boolean leftSleeveEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int leftSleeveBlinkInterval = 20;
    public boolean rightSleeveEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int rightSleeveBlinkInterval = 20;
    public boolean leftPantsLegEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int leftPantsLegBlinkInterval = 20;
    public boolean rightPantsLegEnabled = true;
    @ConfigEntry.BoundedDiscrete(min = 5, max = 200)
    @Comment("(20 ticks = 1 second)")
    public int rightPantsLegBlinkInterval = 20;


    public boolean isEnabled(PlayerModelPart part) {
        switch (part) {
            case HAT:
                return hatEnabled;
            case CAPE:
                return capeEnabled;
            case JACKET:
                return jacketEnabled;
            case LEFT_SLEEVE:
                return leftSleeveEnabled;
            case RIGHT_SLEEVE:
                return rightSleeveEnabled;
            case LEFT_PANTS_LEG:
                return leftPantsLegEnabled;
            case RIGHT_PANTS_LEG:
                return rightPantsLegEnabled;
            default:
                throw new AssertionError("Could not get value for " + part);
        }
    }

    public int getToggleInterval(PlayerModelPart part) {
        switch (part) {
            case HAT:
                return hatBlinkInterval;
            case CAPE:
                return capeBlinkInterval;
            case JACKET:
                return jacketBlinkInterval;
            case LEFT_SLEEVE:
                return leftSleeveBlinkInterval;
            case RIGHT_SLEEVE:
                return rightSleeveBlinkInterval;
            case LEFT_PANTS_LEG:
                return leftPantsLegBlinkInterval;
            case RIGHT_PANTS_LEG:
                return rightPantsLegBlinkInterval;
            default:
                throw new AssertionError("Could not get interval for " + part);
        }
    }

}
