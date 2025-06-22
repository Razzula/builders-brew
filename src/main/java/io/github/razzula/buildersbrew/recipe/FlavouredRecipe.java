package io.github.razzula.buildersbrew.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.Container;

public class FlavouredRecipe {

    public static class Serializer implements RecipeSerializer<Recipe<?>> {
        private final ShapedRecipe.Serializer shapedSerializer = new ShapedRecipe.Serializer();
        private final ShapelessRecipe.Serializer shapelessSerializer = new ShapelessRecipe.Serializer();
        private final RecipeSerializer<StonecutterRecipe> stonecuttingSerializer = RecipeSerializer.STONECUTTER;
        private final RecipeSerializer<SmeltingRecipe> smeltingSerializer = RecipeSerializer.SMELTING_RECIPE;

        @Override
        public Recipe<?> fromJson(@Nonnull ResourceLocation id, @Nonnull JsonObject json) {
            if (json.has("pattern")) {
                // SHAPED CRAFTING
                ShapedRecipe recipe = shapedSerializer.fromJson(id, json);
                return new FlavouredShapedRecipe(id, recipe.getGroup(), recipe.category(),
                    recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(),
                    recipe.getResultItem(RegistryAccess.EMPTY)
                );
            }
            else if (json.has("cookingtime")) {
                // SMELTING
                SmeltingRecipe recipe = smeltingSerializer.fromJson(id, json);
                return new FlavouredSmeltingRecipe(id, recipe.getGroup(), recipe.getIngredients().get(0),
                    recipe.getResultItem(RegistryAccess.EMPTY), recipe.getExperience(), recipe.getCookingTime()
                );
            }
            else if (json.has("ingredient")) {
                // STONECUTTER
                StonecutterRecipe recipe = stonecuttingSerializer.fromJson(id, json);
                return new FlavouredStonecuttingRecipe(id, recipe.getGroup(),
                    recipe.getIngredients().get(0), recipe.getResultItem(RegistryAccess.EMPTY)
                );
            }
            else {
                // SHAPELESS CRAFTING
                ShapelessRecipe recipe = shapelessSerializer.fromJson(id, json);
                return new FlavouredShapelessRecipe(id, recipe.getGroup(), recipe.category(),
                    recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY)
                );
            }
        }

        @Override
        public Recipe<?> fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            int recipeType = buffer.readVarInt(); // read a flag to determine the type

            switch (recipeType) {
                case 0 -> {
                    ShapedRecipe recipe = shapedSerializer.fromNetwork(id, buffer);
                    return new FlavouredShapedRecipe(id, recipe.getGroup(), recipe.category(),
                        recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(),
                        recipe.getResultItem(RegistryAccess.EMPTY)
                    );
                }
                case 2 -> {
                    StonecutterRecipe recipe = stonecuttingSerializer.fromNetwork(id, buffer);
                    return new FlavouredStonecuttingRecipe(id, recipe.getGroup(),
                        recipe.getIngredients().get(0), recipe.getResultItem(RegistryAccess.EMPTY)
                    );
                }
                case 3 -> {
                    SmeltingRecipe recipe = smeltingSerializer.fromNetwork(id, buffer);
                    return new FlavouredSmeltingRecipe(id, recipe.getGroup(), recipe.getIngredients().get(0),
                        recipe.getResultItem(RegistryAccess.EMPTY), recipe.getExperience(), recipe.getCookingTime()
                    );
                }
                default -> {
                    ShapelessRecipe recipe = shapelessSerializer.fromNetwork(id, buffer);
                    return new FlavouredShapelessRecipe(id, recipe.getGroup(), recipe.category(),
                        recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY)
                    );
                }
            }
        }

        @Override
        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull Recipe<?> recipe) {
            if (recipe instanceof FlavouredShapedRecipe) {
                buffer.writeVarInt(0);
                shapedSerializer.toNetwork(buffer, (ShapedRecipe) recipe);
            }
            else if (recipe instanceof FlavouredSmeltingRecipe) {
                buffer.writeVarInt(3);
                smeltingSerializer.toNetwork(buffer, (SmeltingRecipe) recipe);
            }
            else if (recipe instanceof FlavouredStonecuttingRecipe) {
                buffer.writeVarInt(2);
                stonecuttingSerializer.toNetwork(buffer, (StonecutterRecipe) recipe);
            }
            else {
                buffer.writeVarInt(1);
                shapelessSerializer.toNetwork(buffer, (ShapelessRecipe) recipe);
            }
        }
    }
}
