package de.lulkas.biome;

import de.lulkas.LulkasBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> WASTED_LANDS = createKey("wasted_lands");

    private static RegistryKey<Biome> createKey(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(LulkasBiomes.MOD_ID, name));
    }

    public static void bootstrap(Registerable<Biome> context) {
        context.register(WASTED_LANDS, wastedLands(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
    }

    public static Biome wastedLands(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, 2, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 3, 5));
        spawnBuilder.spawn(SpawnGroup.CREATURE, 5, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4));
        DefaultBiomeFeatures.addCaveMobs(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDesertDryVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(false)
                .downfall(0.0f)
                .temperature(0.6f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects(new BiomeEffects.Builder()
                        .waterColor(7768221)
                        .grassColor(7832178)
                        .foliageColor(8883574)
                        .dryFoliageColor(10528412).build())
                .addEnvironmentAttributes(EnvironmentAttributeMap.builder()
                        .with(EnvironmentAttributes.SKY_COLOR_VISUAL, -4605511)
                        .with(EnvironmentAttributes.FOG_COLOR_VISUAL, -8292496)
                        .with(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, -11179648)
                        .with(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, BackgroundMusic.EMPTY)
                        .with(EnvironmentAttributes.MUSIC_VOLUME_AUDIO, 0.0F)
                        .build())
                .build();
    }
}
