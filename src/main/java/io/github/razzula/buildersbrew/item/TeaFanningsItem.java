package io.github.razzula.buildersbrew.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeaFanningsItem extends Item {
    public TeaFanningsItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        TeaType type = TeaType.getTeaType(stack);
        tooltip.add(Component.literal("(" + type.getDriedName() + ")"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
