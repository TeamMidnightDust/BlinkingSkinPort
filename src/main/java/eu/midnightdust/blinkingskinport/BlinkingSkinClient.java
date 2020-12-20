package eu.midnightdust.blinkingskinport;

import eu.midnightdust.blinkingskinport.config.BlinkingSkinConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.HashMap;
import java.util.Map;

public class BlinkingSkinClient implements ClientModInitializer {

    public static BlinkingSkinConfig BS_CONFIG;
    private final Map<PlayerModelPart, Integer> intervals = new HashMap<>();

    @Override
    public void onInitializeClient() {
        AutoConfig.register(BlinkingSkinConfig.class, JanksonConfigSerializer::new);
        BS_CONFIG = AutoConfig.getConfigHolder(BlinkingSkinConfig.class).getConfig();

        for (PlayerModelPart part : PlayerModelPart.values()) {
            this.intervals.put(part, 0);
        }

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (BS_CONFIG.enabled) {
                for (Map.Entry<PlayerModelPart,Integer> interval : this.intervals.entrySet()) {
                    if (!BS_CONFIG.isEnabled(interval.getKey())) {
                        continue;
                    }

                    int counter = this.intervals.get(interval.getKey());
                    if (counter++ >= BS_CONFIG.getToggleInterval(interval.getKey())) {
                        this.intervals.put(interval.getKey(), 0);
                        client.options.togglePlayerModelPart(interval.getKey());
                    }
                    else {
                        this.intervals.put(interval.getKey(), counter);
                    }
                }
            }
        });
    }
}
