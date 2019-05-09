package com.github.draylar.extraPP.common.blocks;

import com.github.draylar.extraPP.common.blocks.blockUtils.PressurePlateRenderType;
import com.github.draylar.extraPP.common.blocks.blockUtils.PressurePlateTask;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CustomPressurePlateBlock extends PressurePlateBlock
{
    final PressurePlateBlock.Type TYPE;
    final Settings SETTINGS;
    final PressurePlateTask TASK;
    final PressurePlateRenderType RENDER;

    public CustomPressurePlateBlock(Type pressurePlateBlock$Type_1, Settings block$Settings_1, PressurePlateTask task, PressurePlateRenderType type)
    {
        super(pressurePlateBlock$Type_1, block$Settings_1);
        this.TYPE = pressurePlateBlock$Type_1;
        this.SETTINGS = block$Settings_1;
        this.TASK = task;
        this.RENDER = type;
    }

    @Override
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1)
    {
        if (!world_1.isClient)
        {
            int redstoneOutput = this.getRedstoneOutput(blockState_1);
            if (redstoneOutput == 0)
            {
                this.updatePlateState(world_1, blockPos_1, blockState_1, redstoneOutput);
                if(TASK != null) TASK.run(blockState_1, world_1, blockPos_1, entity_1);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public boolean skipRenderingSide(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        if(RENDER == PressurePlateRenderType.GLASS)
        {
            return blockState_2.getBlock() == this || super.skipRenderingSide(blockState_1, blockState_2, direction_1);
        }

        return false;
    }

    @Override
    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
    {
        return RENDER == PressurePlateRenderType.GLASS || RENDER == PressurePlateRenderType.SLIME;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
    {
        return false;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
    {
        return RENDER == PressurePlateRenderType.GLASS ? 1.0f : super.getAmbientOcclusionLightLevel(blockState_1, blockView_1, blockPos_1);
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        if(RENDER == PressurePlateRenderType.GLASS) return BlockRenderLayer.CUTOUT;
        if(RENDER == PressurePlateRenderType.SLIME) return BlockRenderLayer.TRANSLUCENT;
        return BlockRenderLayer.SOLID;
    }
}
