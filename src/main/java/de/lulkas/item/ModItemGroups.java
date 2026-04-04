package de.lulkas.item;

import de.lulkas.LulkasBiomes;
import de.lulkas.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup LULKAS_BIOMES = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LulkasBiomes.MOD_ID, "lulkas_biomes"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.WITHERED_DIRT))
                    .displayName(Text.translatable("itemgroup.lulkas_biomes"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.WITHERED_DIRT);
                        entries.add(ModBlocks.BURNT_BUSH);
                    })
                    .build()
    );

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.WITHERED_DIRT);
            entries.add(ModBlocks.BURNT_BUSH);
        });

        LulkasBiomes.LOGGER.info("Registered Item Groups for " + LulkasBiomes.MOD_ID);
    }
}
