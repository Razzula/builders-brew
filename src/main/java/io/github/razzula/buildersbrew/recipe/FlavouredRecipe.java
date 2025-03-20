package io.github.razzula.buildersbrew.recipe;

import javax.annotation.Nonnull;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;

public class FlavouredRecipe {

    public static class Serializer implements RecipeSerializer<Recipe<?>> {
        private final ShapedRecipe.Serializer shapedSerializer = new ShapedRecipe.Serializer();
        private final ShapelessRecipe.Serializer shapelessSerializer = new ShapelessRecipe.Serializer();

        @Override
        public Recipe<?> fromJson(@Nonnull ResourceLocation id, @Nonnull JsonObject json) {
            if (json.has("pattern")) {
                // detect if it's a shaped recipe
                ShapedRecipe recipe = shapedSerializer.fromJson(id, json);
                return new FlavouredShapedRecipe(id, recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY));
            } else {
                // otherwise, assume it's a shapeless recipe
                ShapelessRecipe recipe = shapelessSerializer.fromJson(id, json);
                return new FlavouredShapelessRecipe(id, recipe.getGroup(), recipe.category(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY));
            }
        }

        @Override
        public Recipe<?> fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            boolean isShaped = buffer.readBoolean(); // read a flag to determine the type

            if (isShaped) {
                ShapedRecipe recipe = shapedSerializer.fromNetwork(id, buffer);
                return new FlavouredShapedRecipe(id, recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY));
            } else {
                ShapelessRecipe recipe = shapelessSerializer.fromNetwork(id, buffer);
                return new FlavouredShapelessRecipe(id, recipe.getGroup(), recipe.category(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY));
            }
        }

        @Override
        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull Recipe<?> recipe) {
            boolean isShaped = recipe instanceof FlavouredShapedRecipe;
            buffer.writeBoolean(isShaped); // write a flag to determine the type

            if (isShaped) {
                shapedSerializer.toNetwork(buffer, (ShapedRecipe) recipe);
            } else {
                shapelessSerializer.toNetwork(buffer, (ShapelessRecipe) recipe);
            }
        }
    }
}
