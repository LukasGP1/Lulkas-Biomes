package de.lulkas.world.gen;

import de.lulkas.world.biome.ModBiomes;
import de.lulkas.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModVegetationGeneration {
    public static void generateVegetation() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.WASTED_LANDS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.BURNT_BUSH_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.WASTED_LANDS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.WITHER_ROSE_PATCH_PLACED_KEY
        );
    }
}
