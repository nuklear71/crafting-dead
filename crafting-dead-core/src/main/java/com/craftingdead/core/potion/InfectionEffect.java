package com.craftingdead.core.potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.craftingdead.core.item.ModItems;
import com.craftingdead.core.util.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class InfectionEffect extends Effect {

  private static final Random random = new Random();

  protected InfectionEffect() {
    super(EffectType.HARMFUL, 0x4e9331);
  }

  @Override
  public void performEffect(LivingEntity livingEntity, int amplifier) {
    if (livingEntity.getHealth() > 1.0F && random.nextFloat() < 0.5F) {
      livingEntity.attackEntityFrom(ModDamageSource.INFECTION, 1.0F);
    }
  }

  @Override
  public boolean isReady(int duration, int amplifier) {
    return true;
  }

  @Override
  public List<ItemStack> getCurativeItems() {
    List<ItemStack> items = new ArrayList<ItemStack>();
    items.add(new ItemStack(ModItems.CURE_SYRINGE::get));
    return items;
  }
}