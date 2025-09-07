package net.owen.redstoneanalyzermod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OscilloscopeOverlay {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (!mc.player.getPersistentData().getBoolean("oscilloscope_active")) {
            return; // only render if oscilloscope is active
        }

        List<Integer> samples = OscilloscopeData.getSamples();
        if (samples.isEmpty()) return;

        int baseX = 20;
        int baseY = event.getWindow().getGuiScaledHeight() - 80;

        // Draw waveform
        for (int i = 1; i < samples.size(); i++) {
            int y1 = baseY - samples.get(i - 1) * 2;
            int y2 = baseY - samples.get(i) * 2;
            event.getGuiGraphics().fill(baseX + i, y1, baseX + i + 1, y2, 0xFF00FF00);
        }

        // Draw text info
        BlockPos pos = BlockPos.of(mc.player.getPersistentData().getLong("analyzer_target"));
        int latest = samples.get(samples.size() - 1);
        event.getGuiGraphics().drawString(mc.font, "Oscilloscope:", baseX, baseY - 60, 0xFFFFFF);
        event.getGuiGraphics().drawString(mc.font, "Block: " + mc.level.getBlockState(pos).getBlock().getName().getString(),
                baseX, baseY - 48, 0xFFFFFF);
        event.getGuiGraphics().drawString(mc.font, "Power: " + latest, baseX, baseY - 36, 0xFFFFFF);
    }

}
