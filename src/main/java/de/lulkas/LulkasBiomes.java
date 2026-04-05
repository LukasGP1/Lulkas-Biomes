package de.lulkas;

import de.lulkas.block.ModBlocks;
import de.lulkas.item.ModItemGroups;
import de.lulkas.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LulkasBiomes implements ModInitializer {
	public static final String MOD_ID = "lulkas_biomes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModItemGroups.register();
		ModWorldGeneration.generateModWorldGen();
		LOGGER.info("Initialized " + MOD_ID);
	}
}