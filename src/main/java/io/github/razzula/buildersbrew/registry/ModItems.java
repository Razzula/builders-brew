package io.github.razzula.buildersbrew.registry;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.item.DriedTeaLeafItem;
import io.github.razzula.buildersbrew.item.TeaLeafItem;
import io.github.razzula.buildersbrew.item.TeaFanningsItem;

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

    // TEA SEEDS
    public static final RegistryObject<Item> CAMELLIA_SINENSIS_SEEDS = ITEMS.register("camellia_sinensis_seeds",
        () -> new ItemNameBlockItem(ModBlocks.CAMELLIA_SINENSIS_CROP.get(), new Item.Properties())
    );

    // TEA LEAVES
    public static final RegistryObject<Item> TEA_LEAF = ITEMS.register("tea_leaf",
        () -> new TeaLeafItem(new Item.Properties())
    );
    public static final RegistryObject<Item> DRIED_TEA_LEAF = ITEMS.register("dried_tea_leaf",
        () -> new DriedTeaLeafItem(new Item.Properties())
    );

    // FANNINGS
    public static final RegistryObject<Item> TEA_FANNINGS = ITEMS.register("tea_fannings",
        () -> new TeaFanningsItem(new Item.Properties())
    );

    // register all of the items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
