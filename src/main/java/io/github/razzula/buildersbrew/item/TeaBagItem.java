package io.github.razzula.buildersbrew.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;

public class TeaBagItem extends Item {
    public TeaBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        TeaType teaType = TeaType.getTeaType(stack);
        String name = (teaType == TeaType.BLACK)
            ? TeaFlavour.getTeaFlavour(stack).getName() // use tea flavour to tooltip for black tea
            : teaType.getDriedName(); // use type for other teas

        tooltip.add(Component.literal("(" + name + ")"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
