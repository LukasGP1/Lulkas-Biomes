package de.lulkas.world.structure.dungeon;

import com.mojang.serialization.MapCodec;
import de.lulkas.world.structure.AbstractYOffsetStructure;
import de.lulkas.world.structure.ModStructureTypes;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.structure.StructureType;

public class DungeonStructure extends AbstractYOffsetStructure {
    public static final MapCodec<DungeonStructure> CODEC = createCodec(DungeonStructure::new);

    public DungeonStructure(Config config) {
        super(config);
    }

    @Override
    protected void addPieces(StructurePiecesCollector collector, Context context) {

    }

    @Override
    public Heightmap.Type getHeightmapType() {
        return Heightmap.Type.OCEAN_FLOOR_WG;
    }

    @Override
    public HeightProvider getOffset() {
        return UniformHeightProvider.create(
                YOffset.fixed(-40),
                YOffset.fixed(-20)
        );
    }

    @Override
    public StructureType<?> getType() {
        return ModStructureTypes.DUNGEON_STRUCTURE;
    }
}
