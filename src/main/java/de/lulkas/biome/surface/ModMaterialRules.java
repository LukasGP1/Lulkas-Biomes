package de.lulkas.biome.surface;

import de.lulkas.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule GOLD_BLOCK = defaultStateRule(Blocks.GOLD_BLOCK);
    private static final MaterialRules.MaterialRule EMERALD_BLOCK = defaultStateRule(Blocks.EMERALD_BLOCK);
    private static final MaterialRules.MaterialRule DIAMOND_BLOCK = defaultStateRule(Blocks.DIAMOND_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isInWastedLands = MaterialRules.biome(ModBiomes.WASTED_LANDS);

        return MaterialRules.condition(
                isInWastedLands,
                createPlainsDirtAndGrassLike(DIAMOND_BLOCK, GOLD_BLOCK, EMERALD_BLOCK)
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
