package de.lulkas.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {
    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("block.lulkas_biomes.withered_dirt", "Withered Dirt");
        translationBuilder.add("block.lulkas_biomes.burnt_bush", "Burnt Bush");

        translationBuilder.add("item.lulkas_biomes.withered_dirt", "Withered Dirt");
        translationBuilder.add("item.lulkas_biomes.burnt_bush", "Burnt Bush");

        translationBuilder.add("itemgroup.lulkas_biomes", "Lulkas Biomes");
    }
}
