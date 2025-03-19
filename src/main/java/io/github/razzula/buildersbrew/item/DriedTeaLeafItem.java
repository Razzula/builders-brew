package io.github.razzula.buildersbrew.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import java.util.List;

public class DriedTeaLeafItem extends Item {
    public DriedTeaLeafItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        TeaType type = TeaType.getTeaType(stack);
        tooltip.add(Component.literal(type.getDriedName()));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
