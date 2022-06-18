package eu.midnightdust.blinkingskinport;

import eu.midnightdust.blinkingskinport.config.BlinkingSkinConfig;
import eu.midnightdust.blinkingskinport.mixin.GameOptionsAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.HashMap;
import java.util.Map;

public class BlinkingSkinClient implements ClientModInitializer {

    private final Map<PlayerModelPart, Integer> intervals = new HashMap<>();

    @Override
    public void onInitializeClient() {
        BlinkingSkinConfig.init("blinkingskinport",BlinkingSkinConfig.class);

        for (PlayerModelPart part : PlayerModelPart.values()) {
            this.intervals.put(part, 0);
        }

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (BlinkingSkinConfig.enabled && (BlinkingSkinConfig.player.equals("") || client.getSession().getUsername().equals(BlinkingSkinConfig.player))) {
                if (client.getCurrentServerEntry() != null && BlinkingSkinConfig.isBlocklisted(client.getCurrentServerEntry().address)) return;
                for (Map.Entry<PlayerModelPart,Integer> interval : this.intervals.entrySet()) {
                    if (!BlinkingSkinConfig.isEnabled(interval.getKey())) {
                        continue;
                    }

                    int counter = this.intervals.get(interval.getKey());
                    if (counter++ >= BlinkingSkinConfig.getToggleInterval(interval.getKey())) {
                        this.intervals.put(interval.getKey(), 0);
                        client.options.togglePlayerModelPart(interval.getKey(), !((GameOptionsAccessor)client.options).getEnabledPlayerModelParts().contains(interval.getKey()));
                    }
                    else {
                        this.intervals.put(interval.getKey(), counter);
                    }
                }
            }
        });
    }
}
