package com.serilum.anvilrestoration.forge.events;

import com.serilum.anvilrestoration.events.AnvilInteractEvent;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeAnvilInteractEvent {
	@SubscribeEvent
	public static void onAnvilClick(PlayerInteractEvent.RightClickBlock e) {
		if (AnvilInteractEvent.onAnvilClick(e.getEntity(), e.getLevel(), e.getHand(), e.getHitVec()).equals(InteractionResult.SUCCESS)) {
			e.setCanceled(true);
		}
	}
}
