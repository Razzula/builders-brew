package io.github.razzula.buildersbrew.registry;

import io.github.razzula.buildersbrew.BuildersBrew;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BuildersBrew.MODID);

    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block",
        () -> new BlockItem(ModBlocks.TEST_BLOCK.get(), new Item.Properties())
    );

    // Camellia sinensis seeds
    public static final RegistryObject<Item> CAMELLIA_SINENSIS_SEEDS = ITEMS.register("camellia_sinensis_seeds",
        () -> new ItemNameBlockItem(ModBlocks.CAMELLIA_SINENSIS_CROP.get(), new Item.Properties())
    );

    // Tea leaves
    public static final RegistryObject<Item> TEA_LEAVES = ITEMS.register("tea_leaves",
        () -> new Item(new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
