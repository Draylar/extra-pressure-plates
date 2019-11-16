package com.github.draylar.extrapp.utils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface CollisionTask {
    void run(BlockState state, World world, BlockPos pos, Entity entity);
}
