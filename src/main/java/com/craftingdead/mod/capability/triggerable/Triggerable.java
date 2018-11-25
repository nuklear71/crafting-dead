package com.craftingdead.mod.capability.triggerable;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface Triggerable {

	void update(ItemStack itemStack, Entity entity);

	void setTriggerPressed(boolean triggerPressed, ItemStack itemStack, Entity entity);

}
