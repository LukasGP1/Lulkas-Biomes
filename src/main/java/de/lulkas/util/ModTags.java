package de.lulkas.util;

import de.lulkas.LulkasBiomes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public class ModTags {
    public static class Structures {
        public static final TagKey<Structure> WITHERED_EYE_LOCATABLE = createTag("withered_eye_locatable");

        private static TagKey<Structure> createTag(String name) {
            return TagKey.of(RegistryKeys.STRUCTURE, Identifier.of(LulkasBiomes.MOD_ID, name));
        }
    }
}
