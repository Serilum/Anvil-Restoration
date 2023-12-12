package com.natamus.anvilrestoration.forge.events;

import com.natamus.anvilrestoration.events.AnvilInteractEvent;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeAnvilInteractEvent {
	@SubscribeEvent
	public void onAnvilClick(PlayerInteractEvent.RightClickBlock e) {
		if (AnvilInteractEvent.onAnvilClick(e.getPlayer(), e.getWorld(), e.getHand(), e.getHitVec()).equals(InteractionResult.SUCCESS)) {
			e.setCanceled(true);
		}
	}
}
