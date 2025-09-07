package net.owen.redstoneanalyzermod.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RfsaOverlay {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (!mc.player.getPersistentData().getBoolean("rfsa_active")) {
            return; // only render if RFSA is active
        }

        List<Integer> samples = RfsaData.getSamples();
        if (samples.isEmpty()) return;

        int baseX = 20;
        int baseY = event.getWindow().getGuiScaledHeight() - 100;

        // Show header
        event.getGuiGraphics().drawString(mc.font, "RF Spectrum Analyzer:", baseX, baseY - 60, 0xFFFFFF);

        // Draw simple bar graph of power levels
        for (int i = 0; i < samples.size(); i++) {
            int height = samples.get(i) * 2;
            event.getGuiGraphics().fill(baseX + i, baseY, baseX + i + 1, baseY - height, 0xFF00AAFF);
        }

        // Show toggle frequency estimate
        int toggles = RfsaData.getToggleCount();
        event.getGuiGraphics().drawString(mc.font, "Toggles: " + toggles, baseX, baseY - 72, 0xFFFFFF);
    }
}
