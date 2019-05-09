package com.github.draylar.extraPP.common;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PressurePlateTask
{
    void run(BlockState state, World world, BlockPos pos, Entity entity);
}
