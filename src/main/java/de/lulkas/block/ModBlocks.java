package de.lulkas.block;

import de.lulkas.LulkasBiomes;
import de.lulkas.block.custom.BurntBushBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block WITHERED_DIRT = registerNormalBLock("withered_dirt",
            AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.GRAY));
    public static final Block BURNT_BUSH = registerBlock("burnt_bush",
            AbstractBlock.Settings.copy(Blocks.SHORT_DRY_GRASS).mapColor(MapColor.BLACK), BurntBushBlock::new);

    private static Block registerNormalBLock(String name, AbstractBlock.Settings settings) {
        return registerBlock(name, settings, Block::new);
    }

    private static Block registerBlock(String name, AbstractBlock.Settings settings, Function<AbstractBlock.Settings, Block> factory) {
        Identifier id = Identifier.of(LulkasBiomes.MOD_ID, name);
        Block block = factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    private static void registerBlockItem(Identifier id, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings().registryKey(key)));
    }

    public static void register() {
        LulkasBiomes.LOGGER.info("Registered Blocks for " + LulkasBiomes.MOD_ID);
    }
}
