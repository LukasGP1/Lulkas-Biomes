package de.lulkas.item;

import de.lulkas.LulkasBiomes;
import de.lulkas.item.custom.WitheredEyeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item WITHERED_EYE = registerItem("withered_eye", WitheredEyeItem::new);

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(LulkasBiomes.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LulkasBiomes.MOD_ID, name)))));
    }
    public static void register() {
        LulkasBiomes.LOGGER.info("Registered Items for " + LulkasBiomes.MOD_ID);
    }
}
