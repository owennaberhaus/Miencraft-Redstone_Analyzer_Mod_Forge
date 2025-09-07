package net.owen.redstoneanalyzermod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class RedstoneFrequencySpectrumAnalyzerItem extends Item {
    public RedstoneFrequencySpectrumAnalyzerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide && player != null) {
            player.sendSystemMessage(Component.literal("RFSA probing block at " + pos.toShortString()));
            player.getPersistentData().putLong("rfsa_target", pos.asLong());
            player.getPersistentData().putBoolean("rfsa_active", true);
        }

        if (level.isClientSide) {
            player.getPersistentData().putLong("rfsa_target", pos.asLong());
            player.getPersistentData().putBoolean("rfsa_active", true);
        }

        return InteractionResult.SUCCESS;
    }

}

