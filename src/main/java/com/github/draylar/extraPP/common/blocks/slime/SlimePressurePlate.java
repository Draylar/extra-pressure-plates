package com.github.draylar.extraPP.common.blocks.slime;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SlimePressurePlate extends PressurePlateBlock
{
    public SlimePressurePlate(Type pressurePlateBlock$Type_1, Settings block$Settings_1)
    {
        super(pressurePlateBlock$Type_1, block$Settings_1);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }


    @Environment(EnvType.CLIENT)
    @Override
    public boolean skipRenderingSide(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        return blockState_2.getBlock() == this || super.skipRenderingSide(blockState_1, blockState_2, direction_1);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1)
    {
        return Block.createCuboidShape(0, 0, 0, 16, 3, 16);
    }
}
