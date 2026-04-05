package de.lulkas.world.biome;

import com.mojang.datafixers.util.Pair;
import de.lulkas.LulkasBiomes;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils.*;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(int weight) {
        super(Identifier.of(LulkasBiomes.MOD_ID, "overworld"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterPointListBuilder()
                .temperature(Temperature.span(Temperature.COOL, Temperature.NEUTRAL))
                .humidity(Humidity.span(Humidity.DRY, Humidity.NEUTRAL))
                .continentalness(Continentalness.FAR_INLAND)
                .depth(Depth.SURFACE)
                .build().forEach(point -> builder.add(point, ModBiomes.WASTED_LANDS));

        builder.build().forEach(mapper);
    }
}
