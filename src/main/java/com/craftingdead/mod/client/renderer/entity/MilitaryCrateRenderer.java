package com.craftingdead.mod.client.renderer.entity;

import com.craftingdead.mod.CraftingDead;
import com.craftingdead.mod.client.renderer.entity.model.MilitaryModel;
import com.craftingdead.mod.client.renderer.entity.model.ParachuteModel;
import com.craftingdead.mod.entity.MilitaryCrateEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MilitaryCrateRenderer extends EntityRenderer<MilitaryCrateEntity> {


  private EntityModel modelMilitary;
  private EntityModel modelParachute;

  public MilitaryCrateRenderer(EntityRendererManager renderManager) {
    super(renderManager);
    this.modelMilitary = new MilitaryModel();
    this.modelParachute = new ParachuteModel();
  }

  @Override
  public void doRender(MilitaryCrateEntity entity, double x, double y, double z, float entityYaw,
      float partialTicks) {

    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    GL11.glRotatef(180.0F - entityYaw, 180.0F, 1.0F, 0.0F);

    float f4 = 0.75F;
    GL11.glScalef(f4, f4, f4);
    GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
    this.bindEntityTexture(entity);

    GL11.glScalef(-1.0F, -1.0F, 1.0F);
    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    modelMilitary.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
    modelParachute.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
  }

  @Override
  protected ResourceLocation getEntityTexture(MilitaryCrateEntity entity) {
    return new ResourceLocation(CraftingDead.ID, "textures/block/yellow.png");
  }

}