package io.github.razzula.buildersbrew.registry;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.item.DriedTeaLeafItem;
import io.github.razzula.buildersbrew.item.TeaLeafItem;
import io.github.razzula.buildersbrew.item.TeaFanningsItem;
import io.github.razzula.buildersbrew.item.MugItem;
import io.github.razzula.buildersbrew.item.MugTeaItem;
import io.github.razzula.buildersbrew.item.TeaBoxItem;
import io.github.razzula.buildersbrew.item.TeaBagItem;
import io.github.razzula.buildersbrew.item.ToastItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.BlockItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BuildersBrew.MODID);

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

    // MUG
    public static final RegistryObject<Item> MUG = ITEMS.register("mug",
        () -> new MugItem(new Item.Properties())
    );
    public static final RegistryObject<Item> MUG_TEA = ITEMS.register("mug_tea",
        () -> new MugTeaItem(new Item.Properties())
    );

    //TEAPOT
    public static final RegistryObject<Item> TEAPOT_ITEM = ITEMS.register("teapot",
        () -> new Item(new Item.Properties())
    );

    // TEABAGS
    public static final RegistryObject<Item> TEA_BAG_EMPTY = ITEMS.register("tea_bag_empty",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> TEA_BAG = ITEMS.register("tea_bag",
        () -> new TeaBagItem(new Item.Properties())
    );

    // BOXES
    public static final RegistryObject<Item> TEA_BOX_ITEM = ITEMS.register("tea_box",
        () -> new TeaBoxItem(ModBlocks.TEA_BOX.get(), new Item.Properties())
    );

    // LEMON
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> LEMON_LOG_ITEM = ITEMS.register("lemon_log",
        () -> new BlockItem(ModBlocks.LEMON_LOG.get(), new Item.Properties())
    );
    public static final RegistryObject<Item> LEMON_PLANKS_ITEM = ITEMS.register("lemon_planks",
        () -> new BlockItem(ModBlocks.LEMON_PLANKS.get(), new Item.Properties())
    );
    public static final RegistryObject<Item> LEMON_LEAVES_ITEM = ITEMS.register("lemon_leaves",
        () -> new BlockItem(ModBlocks.LEMON_LEAVES.get(), new Item.Properties())
    );
    public static final RegistryObject<Item> LEMON_SAPLING_ITEM = ITEMS.register("lemon_sapling",
        () -> new BlockItem(ModBlocks.LEMON_SAPLING.get(), new Item.Properties())
    );

    // MISC. ITEMS
    public static final RegistryObject<Item> TOAST = ITEMS.register("toast",
        () -> new ToastItem(new Item.Properties())
    );
    public static final RegistryObject<Item> JAM = ITEMS.register("jam",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> CARAMEL = ITEMS.register("caramel",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> NUTS = ITEMS.register("nuts",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> NUTMEG = ITEMS.register("nutmeg",
        () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> CAFFEINE = ITEMS.register("caffeine",
        () -> new Item(new Item.Properties())
    );

    // register all of the items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
