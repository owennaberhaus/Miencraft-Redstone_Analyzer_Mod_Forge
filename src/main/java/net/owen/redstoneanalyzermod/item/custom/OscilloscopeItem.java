package net.owen.redstoneanalyzermod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OscilloscopeItem extends Item {
    public OscilloscopeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide && player != null) {
            BlockState state = level.getBlockState(pos);
            int redstonePower = level.getSignal(pos, context.getClickedFace());

            player.sendSystemMessage(Component.literal("Redstone power: " + redstonePower));

            // Save target block for oscilloscope overlay
            player.getPersistentData().putLong("analyzer_target", pos.asLong());
        }

        player.getPersistentData().putLong("analyzer_target", pos.asLong());
        player.getPersistentData().putBoolean("oscilloscope_active", true);



        return InteractionResult.SUCCESS;
    }
}
