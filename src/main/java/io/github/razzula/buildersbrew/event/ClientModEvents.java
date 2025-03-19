package io.github.razzula.buildersbrew.event;

import org.jetbrains.annotations.Nullable;

import io.github.razzula.buildersbrew.item.TeaType;
import io.github.razzula.buildersbrew.registry.ModBlocks;
import io.github.razzula.buildersbrew.registry.ModItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "buildersbrew", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CAMELLIA_SINENSIS_CROP.get(), RenderType.cutout());


        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.TEA_LEAF.get(), new ResourceLocation("tea_type"),
                (ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) -> {
                    return TeaType.getTeaType(stack).ordinal();
                }
            );
        });

        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.DRIED_TEA_LEAF.get(), new ResourceLocation("tea_type"),
                (ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) -> {
                    return TeaType.getTeaType(stack).ordinal();
                }
            );
        });

        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.TEA_FANNINGS.get(), new ResourceLocation("tea_type"),
                (ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) -> {
                    return TeaType.getTeaType(stack).ordinal();
                }
            );
        });
    }

}
