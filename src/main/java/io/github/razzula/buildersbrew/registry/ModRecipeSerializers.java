package io.github.razzula.buildersbrew.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.recipe.FlavouredRecipe;
import io.github.razzula.buildersbrew.recipe.FlavouringShapelessRecipe;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BuildersBrew.MODID);

    public static final RegistryObject<RecipeSerializer<?>> FLAVOURED_RECIPE_SERIALISER =
        SERIALIZERS.register("crafting_flavoured", FlavouredRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> FLAVOURING_RECIPE_SERIALIZER =
        SERIALIZERS.register("crafting_flavouring", FlavouringShapelessRecipe.Serializer::new);
}
