package de.lulkas;

import de.lulkas.block.ModBlocks;
import de.lulkas.item.ModItemGroups;
import de.lulkas.item.ModItems;
import de.lulkas.world.gen.ModWorldGeneration;
import de.lulkas.world.structure.ModStructureTypes;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LulkasBiomes implements ModInitializer {
	public static final String MOD_ID = "lulkas_biomes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModBlocks.register();
		ModItemGroups.register();
		ModWorldGeneration.generateModWorldGen();
		ModStructureTypes.register();
		LOGGER.info("Initialized " + MOD_ID);
	}
}