/**
 * Crafting Dead
 * Copyright (C) 2020  Nexus Node
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.craftingdead.immerse.client.gui.component;

import java.util.List;
import java.util.Optional;

import com.craftingdead.core.util.Text;
import com.craftingdead.immerse.client.gui.component.type.MeasureMode;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;

public class TextBlockComponent extends Component<TextBlockComponent> {

  private  ITextComponent text;
  private FontRenderer fontRenderer;
  private boolean shadow;
  private boolean centered;
  private boolean customWidth = false;
  private List<IReorderingProcessor> lines;

  public TextBlockComponent(ITextComponent text) {
    super();
    this.text = text;
    this.fontRenderer = super.minecraft.fontRenderer;
    this.shadow = true;
    this.centered = false;
    // Auto adjust width to size of text
    super.setWidth(this.fontRenderer.getStringPropertyWidth(text));
  }

  public TextBlockComponent(String text) {
    this(Text.of(text));
  }

  public TextBlockComponent setFontRenderer(FontRenderer fontRenderer) {
    this.fontRenderer = fontRenderer;
    return this;
  }

  public TextBlockComponent setShadow(boolean shadow) {
    this.shadow = shadow;
    return this;
  }

  public TextBlockComponent setCentered(boolean centered) {
    this.centered = centered;
    return this;
  }

  @Override
  public TextBlockComponent setWidth(float width) {
    this.customWidth = true;
    return super.setWidth(width);
  }

  public void changeText(ITextComponent text) {
    this.lines = null;
    this.text = text;
    if (!customWidth) {
      super.setWidth(this.fontRenderer.getStringPropertyWidth(text));
    }
    this.layout();
  }

  @Override
  public void layout() {
    super.layout();
    if (this.lines == null) {
      this.generateLines((int) this.getContentWidth());
    }
  }

  @Override
  protected Vector2f measure(MeasureMode widthMode, float width, MeasureMode heightMode,
                             float height) {
    if (widthMode == MeasureMode.UNDEFINED) {
      width = this.fontRenderer.getStringWidth(this.text.getString());
    }

    this.generateLines((int) width);
    return new Vector2f(width, this.lines.size() * this.fontRenderer.FONT_HEIGHT);
  }

  private void generateLines(int width) {
    this.lines = RenderComponentsUtil.func_238505_a_(this.text, width, this.fontRenderer);
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    return this.getMouseOverText(mouseX, mouseY).map(this.getScreen()::handleComponentClicked)
        .orElse(false) || super.mouseClicked(mouseX, mouseY, button);
  }

  @Override
  public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    super.render(matrixStack, mouseX, mouseY, partialTicks);
    matrixStack.push();
    {
      matrixStack.translate(this.getContentX(), this.getContentY(), 0.0D);
      matrixStack.scale(this.getXScale(), this.getYScale(), 1.0F);
      IRenderTypeBuffer.Impl renderTypeBuffer =
          IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
      for (int i = 0; i < this.lines.size(); i++) {
        IReorderingProcessor line = this.lines.get(i);
        matrixStack.push();
        {
          matrixStack.translate(0.0D, i * this.fontRenderer.FONT_HEIGHT, 0.0D);
          float x = this.centered ? (this.getWidth()  - fontRenderer.func_243245_a(line)) / 2F : 0;
          this.fontRenderer.func_238416_a_(line, x, 0, 0xFFFFFFFF, this.shadow,
              matrixStack.getLast().getMatrix(), renderTypeBuffer, false, 0, 0xF000F0);
        }
        matrixStack.pop();
      }
      renderTypeBuffer.finish();
    }
    matrixStack.pop();
  }

  public Optional<Style> getMouseOverText(double mouseX, double mouseY) {
    int offsetMouseX = MathHelper.floor(mouseX - this.getScaledContentX());
    int offsetMouseY = MathHelper.floor(mouseY - this.getScaledContentY());
    if (offsetMouseX >= 0 && offsetMouseY >= 0) {
      final int lines = this.lines.size();
      if (offsetMouseX <= this.getScaledContentWidth()
          && offsetMouseY < this.fontRenderer.FONT_HEIGHT * lines + lines) {
        int maxLines = offsetMouseY / this.fontRenderer.FONT_HEIGHT;
        if (maxLines >= 0 && maxLines < this.lines.size()) {
          IReorderingProcessor line = this.lines.get(maxLines);
          return Optional.ofNullable(
              this.fontRenderer.getCharacterManager().func_243239_a(line, offsetMouseX));
        }
      }
    }
    return Optional.empty();
  }
}
