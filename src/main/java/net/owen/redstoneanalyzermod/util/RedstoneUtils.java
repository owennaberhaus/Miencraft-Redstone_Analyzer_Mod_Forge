package net.owen.redstoneanalyzermod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneUtils {
    public static int getRedstonePowerAt(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        // If it's redstone dust, read directly from blockstate
        if (state.getBlock() instanceof RedStoneWireBlock) {
            return state.getValue(RedStoneWireBlock.POWER);
        }

        // Otherwise, sample signal from all directions
        int maxSignal = 0;
        for (Direction dir : Direction.values()) {
            maxSignal = Math.max(maxSignal, level.getSignal(pos, dir));
        }

        return maxSignal;
    }
}
