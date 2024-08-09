package com.natamus.anvilrestoration;

import com.natamus.anvilrestoration.events.AnvilInteractEvent;
import com.natamus.anvilrestoration.util.Reference;
import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			return AnvilInteractEvent.onAnvilClick(player, world, hand, hitResult);
		});
	}

	private static void setGlobalConstants() {

	}
}
