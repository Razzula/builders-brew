package io.github.razzula.buildersbrew.registry;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.item.TeaLeafItem;
import io.github.razzula.buildersbrew.item.DriedTeaLeafItem;
import io.github.razzula.buildersbrew.item.TeaFanningsItem;
import io.github.razzula.buildersbrew.item.TeaBoxItem;
import io.github.razzula.buildersbrew.item.TeaBagItem;
import io.github.razzula.buildersbrew.item.MugTeaItem;
import io.github.razzula.buildersbrew.item.TeaType;
import io.github.razzula.buildersbrew.item.TeaFlavour;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), BuildersBrew.MODID);

    public static final RegistryObject<CreativeModeTab> BUILDERS_BREW_TAB = CREATIVE_TABS.register("buildersbrew_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.buildersbrew"))
            .icon(() -> new ItemStack(ModItems.TEA_BOX_ITEM.get()))
            .displayItems((parameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(item -> {
                    ItemStack baseStack = item.get().getDefaultInstance();
                    Item itemInstance = item.get();

                    // handle NBT variants
                    if (itemInstance instanceof TeaLeafItem
                        || itemInstance instanceof DriedTeaLeafItem
                    ) {
                        for (TeaType type : TeaType.values()) {
                            ItemStack variantStack = baseStack.copy();
                            TeaType.setTeaType(variantStack, type);
                            output.accept(variantStack);
                        }
                    }
                    else if (itemInstance instanceof TeaFanningsItem
                        || itemInstance instanceof TeaBagItem
                        || itemInstance instanceof MugTeaItem
                    ) {
                        for (TeaType type : TeaType.values()) {
                            ItemStack variantStack = baseStack.copy();
                            TeaType.setTeaType(variantStack, type);
                            if (type == TeaType.BLACK) {
                                for (TeaFlavour flavour : TeaFlavour.values()) {
                                    ItemStack flavourStack = variantStack.copy();
                                    TeaFlavour.setTeaFlavour(flavourStack, flavour);
                                    output.accept(flavourStack);
                                }
                            }
                            else {
                                output.accept(variantStack);
                            }
                        }
                    }
                    else if (itemInstance instanceof TeaBoxItem) {
                        for (TeaFlavour type : TeaFlavour.values()) {
                            ItemStack variantStack = baseStack.copy();
                            TeaFlavour.setTeaFlavour(variantStack, type);
                            output.accept(variantStack);
                        }
                    }
                    else {
                        output.accept(baseStack);
                    }
                });
            }
        ).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
