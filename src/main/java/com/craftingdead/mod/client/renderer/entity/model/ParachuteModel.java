package com.craftingdead.mod.client.renderer.entity.model;

import static com.craftingdead.mod.client.util.RenderUtil.renderModel;

import com.craftingdead.mod.CraftingDead;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.BasicState;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ParachuteModel extends EntityModel {

  public IBakedModel modelParachute;

  public ParachuteModel() {
    try {

      ResourceLocation location = new ResourceLocation(CraftingDead.ID,
          "/models/block/parachute.obj");

      IUnbakedModel model = OBJLoader.INSTANCE.loadModel(location);

      modelParachute = model
          .bake(null, ModelLoader.defaultTextureGetter(),
              new BasicState(model.getDefaultState(), true),
              DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    if (!entity.onGround) {
      GlStateManager.translated(-0.5, .65, .5);
      renderModel(modelParachute);
    }
  }


}
