package io.github.razzula.buildersbrew.item;

import javax.annotation.Nonnull;

import io.github.razzula.buildersbrew.registry.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class MugItem extends Item {
    public MugItem(Properties properties) {
        super(properties
            .stacksTo(16)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);

            if (!level.isClientSide) {
                if (state.getFluidState().isSource()) {

                    ItemStack filledMug = new ItemStack(ModItems.MUG_TEA.get());

                    // remove fluid
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

                    // add filled mug to inventory
                    if (stack.getCount() == 1) {
                        // replace the empty mug with a filled mug
                        return InteractionResultHolder.success(filledMug);
                    }

                    // else, remove one mug, and add a filled mug
                    stack.shrink(1); // consume
                    if (!player.getInventory().add(filledMug)) {
                        player.drop(filledMug, false); // drop if inventory is full
                    }

                    return InteractionResultHolder.success(stack);
                }
            }
        }
        return InteractionResultHolder.pass(stack);
    }
}
