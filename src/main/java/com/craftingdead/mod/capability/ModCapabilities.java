package com.craftingdead.mod.capability;

import com.craftingdead.mod.capability.aimable.DefaultAimable;
import com.craftingdead.mod.capability.aimable.IAimable;
import com.craftingdead.mod.capability.animation.DefaultAnimationController;
import com.craftingdead.mod.capability.animation.IAnimationController;
import com.craftingdead.mod.capability.player.DefaultPlayer;
import com.craftingdead.mod.capability.player.IPlayer;
import com.craftingdead.mod.capability.shootable.DefaultShootable;
import com.craftingdead.mod.capability.shootable.IShootable;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

  @CapabilityInject(IPlayer.class)
  public static final Capability<IPlayer<?>> PLAYER = null;

  @CapabilityInject(IShootable.class)
  public static final Capability<IShootable> SHOOTABLE = null;

  @CapabilityInject(IAimable.class)
  public static final Capability<IAimable> AIMABLE = null;

  @CapabilityInject(IAnimationController.class)
  public static final Capability<IAnimationController> ANIMATION_CONTROLLER = null;

  public static void registerCapabilities() {
    CapabilityManager.INSTANCE.register(IPlayer.class, new EmptyStorage<>(), DefaultPlayer::new);
    CapabilityManager.INSTANCE
        .register(IShootable.class, new EmptyStorage<>(), DefaultShootable::new);
    CapabilityManager.INSTANCE.register(IAimable.class, new EmptyStorage<>(), DefaultAimable::new);
    CapabilityManager.INSTANCE
        .register(IAnimationController.class, new EmptyStorage<>(),
            DefaultAnimationController::new);
  }

  private static class EmptyStorage<C> implements Capability.IStorage<C> {

    @Override
    public INBT writeNBT(Capability<C> capability, C instance, Direction side) {
      return null;
    }

    @Override
    public void readNBT(Capability<C> capability, C instance, Direction side, INBT nbt) {
      ;
    }
  }
}
