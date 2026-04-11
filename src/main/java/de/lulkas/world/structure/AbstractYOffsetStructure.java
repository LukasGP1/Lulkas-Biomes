package de.lulkas.world.structure;

import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractYOffsetStructure extends Structure {
    protected AbstractYOffsetStructure(Config config) {
        super(config);
    }

    protected abstract void addPieces(StructurePiecesCollector collector, Structure.Context context);
    public abstract Heightmap.Type getHeightmapType();
    public abstract HeightProvider getOffset();

    @Override
    protected Optional<StructurePosition> getStructurePosition(Context context) {
        return getOffsetStructurePosition(
                context,
                getHeightmapType(),
                getOffset(),
                collector -> addPieces(collector, context)
        );
    }

    private Optional<StructurePosition> getOffsetStructurePosition(Structure.Context context,
                                                                   Heightmap.Type heightmap,
                                                                   HeightProvider offset,
                                                                   Consumer<StructurePiecesCollector> generator) {
        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getCenterX();
        int z = chunkPos.getCenterZ();
        int y = context.chunkGenerator().getHeightInGround(x, z, heightmap, context.world(), context.noiseConfig());
        y += offset.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));
        return Optional.of(new Structure.StructurePosition(new BlockPos(x, y, z), generator));
    }
}
