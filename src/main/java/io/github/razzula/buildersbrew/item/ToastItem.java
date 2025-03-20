package io.github.razzula.buildersbrew.item;

import javax.annotation.Nonnull;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ToastItem extends Item {

    private static final FoodProperties BREAD_FOOD = Items.BREAD.getFoodProperties();

    public ToastItem(Properties properties) {
        super(properties
            .food(new FoodProperties.Builder()
                .nutrition(BREAD_FOOD.getNutrition() + 2)
                .saturationMod(BREAD_FOOD.getSaturationModifier() + 0.1f)
                .alwaysEat()
                .build()
            )
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand)); // begin animation
    }

    @Override
    public ItemStack finishUsingItem(@Nonnull ItemStack stack, @Nonnull Level level, @Nonnull LivingEntity entity) {
        if (entity instanceof Player player) {
            if (!player.isCreative()) {
                // consume
                stack.shrink(1);
            }
        }
        return stack;
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public UseAnim getUseAnimation(@Nonnull ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(@Nonnull ItemStack stack) {
        return 32;
    }
}
