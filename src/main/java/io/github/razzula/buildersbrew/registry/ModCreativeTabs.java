package io.github.razzula.buildersbrew.registry;

import io.github.razzula.buildersbrew.BuildersBrew;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), BuildersBrew.MODID);

    public static final RegistryObject<CreativeModeTab> BUILDERS_BREW_TAB = CREATIVE_TABS.register("buildersbrew_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.buildersbrew"))
            .icon(() -> new ItemStack(ModItems.TEA_LEAVES.get()))
            .displayItems((parameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            }
        ).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
