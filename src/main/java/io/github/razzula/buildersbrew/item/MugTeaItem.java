package io.github.razzula.buildersbrew.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.razzula.buildersbrew.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MugTeaItem extends Item {
    public MugTeaItem(Properties properties) {
        super(properties
            .stacksTo(1)
            .food(new FoodProperties.Builder()
                .nutrition(0) // no hunger
                .saturationMod(0.3f) // 30% saturation
                .alwaysEat() // can be consumed when full
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

                ItemStack emptyMug = new ItemStack(ModItems.MUG.get());
                int existingMugCount = player.getInventory().countItem(ModItems.MUG.get());

                // consume
                stack.shrink(1);

                // add empty mug to inventory
                if (existingMugCount > 0) {
                    boolean addedToStack = player.getInventory().add(emptyMug);
                    if (addedToStack) {
                        return ItemStack.EMPTY;
                    }
                }
                return emptyMug;
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
        return UseAnim.DRINK; // drinking animation
    }

    @Override
    public int getUseDuration(@Nonnull ItemStack stack) {
        return 32; // ticks to drink
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        TeaType teaType = TeaType.getTeaType(stack);
        String name = (teaType == TeaType.BLACK)
            ? TeaFlavour.getTeaFlavour(stack).getName() // use tea flavour to tooltip for black tea
            : teaType.getDriedName(); // use type for other teas

        tooltip.add(Component.literal(name));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
