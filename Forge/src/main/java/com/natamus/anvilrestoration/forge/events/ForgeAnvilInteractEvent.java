package com.natamus.anvilrestoration.forge.events;

import com.natamus.anvilrestoration.events.AnvilInteractEvent;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeAnvilInteractEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeAnvilInteractEvent.class);

		PlayerInteractEvent.RightClickBlock.BUS.addListener(ForgeAnvilInteractEvent::onAnvilClick);
	}

	@SubscribeEvent
	public static boolean onAnvilClick(PlayerInteractEvent.RightClickBlock e) {
		if (AnvilInteractEvent.onAnvilClick(e.getEntity(), e.getLevel(), e.getHand(), e.getHitVec()).equals(InteractionResult.SUCCESS)) {
			return true;
		}
		return false;
	}
}
