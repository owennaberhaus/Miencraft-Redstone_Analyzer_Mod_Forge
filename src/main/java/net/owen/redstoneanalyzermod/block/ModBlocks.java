package net.owen.redstoneanalyzermod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import net.owen.redstoneanalyzermod.RedstoneAnalyzerMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.owen.redstoneanalyzermod.block.Custom.AndGateBlock;
import net.owen.redstoneanalyzermod.block.Custom.NotGateBlock;
import net.owen.redstoneanalyzermod.block.Custom.OrGateBlock;
import net.owen.redstoneanalyzermod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RedstoneAnalyzerMod.MOD_ID);



//    public static final RegistryObject<Block> XYZ_BLOCK = registerBlock("xyz_block",                 /// example!!!
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> ANDGATE = BLOCKS.register("andgate",
            () -> new AndGateBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f)
                    .noOcclusion()));

    public static final RegistryObject<Block> ORGATE = BLOCKS.register("orgate",
            () -> new OrGateBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f)
                    .noOcclusion()));

    public static final RegistryObject<Block> NOTGATE = BLOCKS.register("notgate",
            () -> new NotGateBlock(BlockBehaviour.Properties.of()
                    .strength(0.5f)
                    .noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
