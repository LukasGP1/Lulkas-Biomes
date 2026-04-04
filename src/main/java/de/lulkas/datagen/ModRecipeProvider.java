package de.lulkas.datagen;

import de.lulkas.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                createShapelessRecipe(RecipeCategory.MISC, ModBlocks.WITHERED_DIRT, Blocks.WITHER_ROSE,
                        Map.of(Blocks.DIRT, 3, Blocks.WITHER_ROSE, 1), exporter);
            }

            private void createShapelessRecipe(RecipeCategory category, ItemConvertible output, ItemConvertible criterion,
                                               Map<ItemConvertible, Integer> ingredientCountMap, RecipeExporter exporter) {
                ShapelessRecipeJsonBuilder builder = this.createShapeless(category, output);
                for(ItemConvertible ingredient : ingredientCountMap.keySet()) {
                    for (int i = 0; i < ingredientCountMap.get(ingredient); i++) {
                        builder.input(ingredient);
                    }
                }
                builder.criterion(hasItem(criterion), this.conditionsFromItem(criterion)).offerTo(exporter, convertBetween(output, criterion));
            }
        };
    }

    @Override
    public String getName() {
        return "Lulkas Biomes Recipes";
    }
}
