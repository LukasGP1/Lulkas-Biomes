package de.lulkas.datagen;

import de.lulkas.block.ModBlocks;
import de.lulkas.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WITHERED_DIRT);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.BURNT_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        registerGeneratedItemModelWithBlockTexture(ModBlocks.BURNT_BUSH, itemModelGenerator);
        itemModelGenerator.register(ModItems.WITHERED_EYE, Models.GENERATED);
    }

    private static void registerGeneratedItemModelWithBlockTexture(Block block, ItemModelGenerator generator) {
        TextureMap textureMap = TextureMap.layer0(block);
        Identifier identifier = Models.GENERATED.upload(ModelIds.getItemModelId(block.asItem()), textureMap, generator.modelCollector);
        generator.output.accept(block.asItem(), ItemModels.basic(identifier));
    }
}
