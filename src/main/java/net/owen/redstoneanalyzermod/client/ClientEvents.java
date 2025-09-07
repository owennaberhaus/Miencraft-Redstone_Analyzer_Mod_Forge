package net.owen.redstoneanalyzermod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.owen.redstoneanalyzermod.item.ModItems;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        // Only track if holding oscilloscope
        if (player.getMainHandItem().getItem() == ModItems.OSCILLOSCOPE.get()) {
            if (player.getPersistentData().contains("analyzer_target")) {
                BlockPos pos = BlockPos.of(player.getPersistentData().getLong("analyzer_target"));
                Level level = mc.level;
                if (level != null) {
                    // Grab power at that block
                    int power = net.owen.redstoneanalyzermod.util.RedstoneUtils.getRedstonePowerAt(level, pos);
                    OscilloscopeData.addSample(power);
                }
            }
        } else {
            // If player switches items, clear data
            OscilloscopeData.clear();
        }
        if (player.getMainHandItem().getItem() == ModItems.REDSTONEFREQUENCYSPECTRUMANALYZER.get()) {
            if (player.getPersistentData().getBoolean("rfsa_active")
                    && player.getPersistentData().contains("rfsa_target")) {

                BlockPos pos = BlockPos.of(player.getPersistentData().getLong("rfsa_target"));
                Level level = mc.level;
                if (level != null) {
                    int power = net.owen.redstoneanalyzermod.util.RedstoneUtils.getRedstonePowerAt(level, pos);
                    RfsaData.addSample(power);
                }
            }
        } else {
            player.getPersistentData().remove("rfsa_active");
            player.getPersistentData().remove("rfsa_target");
            RfsaData.clear();
        }

    }
}
