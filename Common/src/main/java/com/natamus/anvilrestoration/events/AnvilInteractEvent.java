package com.natamus.anvilrestoration.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AnvilInteractEvent {
	public static InteractionResult onAnvilClick(Player player, Level world, InteractionHand hand, BlockHitResult blockHitResult) {
		if (world.isClientSide) {
			return InteractionResult.PASS;
		}

		ItemStack itemStack = player.getItemInHand(hand);
		Item item = itemStack.getItem();
		if (!item.equals(Items.IRON_INGOT) && !item.equals(Items.OBSIDIAN)) {
			return InteractionResult.PASS;
		}

		BlockPos pos = blockHitResult.getBlockPos();
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		
		BlockState newstate;
		if (block.equals(Blocks.ANVIL) && item.equals(Items.OBSIDIAN)) {
			newstate = Blocks.CHIPPED_ANVIL.defaultBlockState();
		}
		else if (block.equals(Blocks.CHIPPED_ANVIL)) {
			if (item.equals(Items.IRON_INGOT)) {
				newstate = Blocks.ANVIL.defaultBlockState();
			}
			else { // obsidian
				newstate = Blocks.DAMAGED_ANVIL.defaultBlockState();
			}
		}
		else if (block.equals(Blocks.DAMAGED_ANVIL) && item.equals(Items.IRON_INGOT)) {
			newstate = Blocks.CHIPPED_ANVIL.defaultBlockState();
		}
		else {
			return InteractionResult.PASS;
		}
		
		Direction rotation = state.getValue(AnvilBlock.FACING);
		world.setBlock(pos, newstate.setValue(AnvilBlock.FACING, rotation), 3);
		
		if (item.equals(Items.IRON_INGOT)) {
			if (!player.isCreative()) {
				itemStack.shrink(1);
			}
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 0.5F, 1.0F);
		}
		else {
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS, 0.5F, 1.0F);
		}
		
		return InteractionResult.SUCCESS;
	}
}
