package com.craftingdead.immerse.client.gui.screen;

import com.craftingdead.immerse.CraftingDeadImmerse;
import com.craftingdead.immerse.client.gui.component.BlurComponent;
import com.craftingdead.immerse.client.gui.component.Colour;
import com.craftingdead.immerse.client.gui.component.Component;
import com.craftingdead.immerse.client.gui.component.ComponentScreen;
import com.craftingdead.immerse.client.gui.component.ContainerComponent;
import com.craftingdead.immerse.client.gui.component.ImageComponent;
import com.craftingdead.immerse.client.gui.component.LabelComponent;
import com.craftingdead.immerse.client.gui.component.PanoramaComponent;
import com.craftingdead.immerse.client.gui.component.RectangleComponent;
import com.craftingdead.immerse.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class MenuScreen extends ComponentScreen {

  protected MenuScreen(ITextComponent title) {
    super(title);

    final Minecraft mc = Minecraft.getInstance();

    this
        .getRoot()
        .addChild(
            new PanoramaComponent(new ResourceLocation("textures/gui/title/background/panorama")));

    ContainerComponent sideBar =
        new ContainerComponent().setX(0).setY(0).setWidth(25).setHeightPercent(1.0F);

    BlurComponent sideBarBlur = new BlurComponent();
    sideBar.addChild(sideBarBlur);

    RectangleComponent sideBarBackground =
        new RectangleComponent(new Colour(new float[] {0.5F, 0.5F, 0.5F, 0.25F}));
    sideBar.addChild(sideBarBackground);

    sideBar
        .addChild(new ImageComponent(
            new ResourceLocation(CraftingDeadImmerse.ID, "textures/gui/home.png"))
                .setXPercent(0.5F)
                .setCentre(true)
                .setY(10)
                .setWidthPercent(0.5F)
                .setAutoHeight()
                .addHoverAnimation(ImageComponent.COLOUR,
                    RenderUtil.getColour4f(RenderUtil.getColour4i(0xFF181818)), 150.0F)
                .addHoverAnimation(Component.X_SCALE, new float[] {1.25F}, 150.0F)
                .addHoverAnimation(Component.Y_SCALE, new float[] {1.25F}, 150.0F)
                .addClickSound(SoundEvents.UI_BUTTON_CLICK));

    sideBar
        .addChild(new RectangleComponent(new Colour(0x80888888))
            .setX(0)
            .setY(20)
            .setWidthPercent(1.0F)
            .setHeight(1)
            .setScaleHeight(false));

    sideBar
        .addChild(new ImageComponent(
            new ResourceLocation(CraftingDeadImmerse.ID, "textures/gui/play.png"))
                .setXPercent(0.5F)
                .setCentre(true)
                .setY(30)
                .setWidthPercent(0.5F)
                .setAutoHeight()
                .addHoverAnimation(ImageComponent.COLOUR,
                    RenderUtil.getColour4f(RenderUtil.getColour4i(0xFF181818)), 150.0F)
                .addHoverAnimation(Component.X_SCALE, new float[] {1.25F}, 150.0F)
                .addHoverAnimation(Component.Y_SCALE, new float[] {1.25F}, 150.0F)
                .addClickSound(SoundEvents.UI_BUTTON_CLICK));

    sideBar
        .addChild(new RectangleComponent(new Colour(0x80888888))
            .setX(0)
            .setY(40)
            .setWidthPercent(1.0F)
            .setScaleHeight(false)
            .setHeight(1));

    sideBar
        .addChild(new ImageComponent(
            new ResourceLocation(CraftingDeadImmerse.ID, "textures/gui/settings.png"))
                .setXPercent(0.5F)
                .setCentre(true)
                .setY(50)
                .setWidthPercent(0.5F)
                .setAutoHeight()
                .addHoverAnimation(ImageComponent.COLOUR,
                    RenderUtil.getColour4f(RenderUtil.getColour4i(0xFF181818)), 150.0F)
                .addHoverAnimation(Component.X_SCALE, new float[] {1.25F}, 150.0F)
                .addHoverAnimation(Component.Y_SCALE, new float[] {1.25F}, 150.0F)
                .addClickSound(SoundEvents.UI_BUTTON_CLICK)
                .addActionListener(c -> this.minecraft
                    .displayGuiScreen(new OptionsScreen(this, this.minecraft.gameSettings))));

    sideBar
        .addChild(new ImageComponent(
            new ResourceLocation(CraftingDeadImmerse.ID, "textures/gui/power.png"))
                .setXPercent(0.5F)
                .setCentre(true)
                .setYPercent(0.925F)
                .setWidthPercent(0.5F)
                .setAutoHeight()
                .addHoverAnimation(ImageComponent.COLOUR,
                    RenderUtil.getColour4f(RenderUtil.getColour4i(0xFF181818)), 150.0F)
                .addHoverAnimation(Component.X_SCALE, new float[] {1.25F}, 150.0F)
                .addHoverAnimation(Component.Y_SCALE, new float[] {1.25F}, 150.0F)
                .addClickSound(SoundEvents.UI_BUTTON_CLICK)
                .addActionListener(c -> this.minecraft.shutdown()));

    sideBar
        .addChild(new RectangleComponent(new Colour(0x80888888))
            .setXPercent(1.0F)
            .setY(0)
            .setWidth(1)
            .setScaleWidth(false)
            .setHeightPercent(1.0F));

    this
        .getRoot()
        .addChild(new LabelComponent(mc.fontRenderer, new StringTextComponent("Hello World!"),
            new Colour(0xFFFFFFFF), false)
                .setXPercent(0.5F)
                .setYPercent(0.5F)
                .setAutoWidth()
                .setAutoHeight()
                .setCentre(true)
                .addHoverAnimation(Component.X_SCALE, new float[] {2.0F}, 150.0F)
                .addHoverAnimation(Component.Y_SCALE, new float[] {2.0F}, 150.0F));

    this.getRoot().addChild(sideBar);
  }
}