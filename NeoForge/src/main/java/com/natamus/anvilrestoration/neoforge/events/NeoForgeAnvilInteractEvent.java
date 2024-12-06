package com.natamus.anvilrestoration.neoforge.events;

import com.natamus.anvilrestoration.events.AnvilInteractEvent;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeAnvilInteractEvent {
	@SubscribeEvent
	public static void onAnvilClick(PlayerInteractEvent.RightClickBlock e) {
		if (AnvilInteractEvent.onAnvilClick(e.getEntity(), e.getLevel(), e.getHand(), e.getHitVec()).equals(InteractionResult.SUCCESS)) {
			e.setCanceled(true);
		}
	}
}
