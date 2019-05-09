package com.github.draylar.extraPP.common.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;

public class CustomInvisiblePressurePlateBlock extends CustomPressurePlateBlock
{
    public CustomInvisiblePressurePlateBlock(CustomPressurePlateBlock block)
    {
        super(block.TYPE, block.SETTINGS, block.TASK);
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1)
    {
        return BlockRenderType.INVISIBLE;
    }
}
