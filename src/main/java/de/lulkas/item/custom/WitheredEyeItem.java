package de.lulkas.item.custom;

import de.lulkas.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class WitheredEyeItem extends EnderEyeItem {
    public WitheredEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (blockHitResult.getType() == HitResult.Type.BLOCK && world.getBlockState(blockHitResult.getBlockPos()).isOf(Blocks.END_PORTAL_FRAME)) {
            return ActionResult.PASS;
        } else {
            user.setCurrentHand(hand);
            if (world instanceof ServerWorld serverWorld) {
                BlockPos blockPos = serverWorld.locateStructure(ModTags.Structures.WITHERED_EYE_LOCATABLE, user.getBlockPos(), 100, false);
                if (blockPos == null) {
                    return ActionResult.CONSUME;
                }

                EyeOfEnderEntity eyeOfEnderEntity = new EyeOfEnderEntity(world, user.getX(), user.getBodyY(0.5), user.getZ());
                eyeOfEnderEntity.setItem(itemStack);
                eyeOfEnderEntity.initTargetPos(Vec3d.of(blockPos));
                world.emitGameEvent(GameEvent.PROJECTILE_SHOOT, eyeOfEnderEntity.getEntityPos(), GameEvent.Emitter.of(user));
                world.spawnEntity(eyeOfEnderEntity);

                float f = MathHelper.lerp(world.random.nextFloat(), 0.33F, 0.5F);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, f);
                itemStack.decrementUnlessCreative(1, user);
                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }

            return ActionResult.SUCCESS_SERVER;
        }
    }
}
