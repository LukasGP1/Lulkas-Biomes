package de.lulkas.mixin;

import de.lulkas.item.ModItems;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EyeOfEnderEntity.class)
public abstract class EnderEyeEntityMixin {
    @Shadow public abstract ItemStack getStack();

    @Shadow private boolean dropsItem;

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if(getStack().getItem() == ModItems.WITHERED_EYE) dropsItem = true;
    }
}
