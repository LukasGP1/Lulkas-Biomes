package de.lulkas.world.structure;

import com.mojang.serialization.MapCodec;
import de.lulkas.LulkasBiomes;
import de.lulkas.world.structure.dungeon.DungeonStructure;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class ModStructureTypes {
    public static final StructureType<DungeonStructure> DUNGEON_STRUCTURE = registerStructureType("dungeon_structure", DungeonStructure.CODEC);

    private static <T extends Structure> StructureType<T> registerStructureType(String name, MapCodec<T> codec) {
        return Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(LulkasBiomes.MOD_ID, name), () -> codec);
    }

    public static void register() {
        LulkasBiomes.LOGGER.info("Registered Structure Types for " + LulkasBiomes.MOD_ID);
    }
}
