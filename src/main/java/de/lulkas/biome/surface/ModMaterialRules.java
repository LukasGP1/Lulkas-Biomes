package de.lulkas.biome.surface;

import de.lulkas.biome.ModBiomes;
import de.lulkas.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule WITHERED_DIRT = defaultStateRule(ModBlocks.WITHERED_DIRT);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isInWastedLands = MaterialRules.biome(ModBiomes.WASTED_LANDS);

        return MaterialRules.condition(
                isInWastedLands,
                createPlainsDirtAndGrassLike(WITHERED_DIRT, WITHERED_DIRT, WITHERED_DIRT)
        );
    }

    private static MaterialRules.MaterialRule createPlainsDirtAndGrassLike(MaterialRules.MaterialRule surface,
                                                                           MaterialRules.MaterialRule floorBelowLiquids,
                                                                           MaterialRules.MaterialRule fewBlocksBelowSurface) {
        return MaterialRules.condition(
                MaterialRules.surface(),
                MaterialRules.sequence(
                        MaterialRules.condition(
                                MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR),
                                MaterialRules.sequence(
                                        MaterialRules.condition(
                                                MaterialRules.water(0, 0),
                                                surface
                                        ),
                                        floorBelowLiquids
                                )
                        ),
                        MaterialRules.condition(
                                MaterialRules.stoneDepth(0, true, VerticalSurfaceType.FLOOR),
                                fewBlocksBelowSurface
                        )
                )
        );
    }

    private static MaterialRules.MaterialRule defaultStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
