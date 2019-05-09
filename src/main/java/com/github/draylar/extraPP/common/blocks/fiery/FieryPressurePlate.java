package com.github.draylar.extraPP.common.blocks.fiery;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FieryPressurePlate extends PressurePlateBlock
{
    public FieryPressurePlate(Type pressurePlateBlock$Type_1, Settings block$Settings_1)
    {
        super(pressurePlateBlock$Type_1, block$Settings_1);
    }

    @Override
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        if (!world_1.isClient)
        {
            int redstoneOutput = this.getRedstoneOutput(blockState_1);
            if (redstoneOutput == 0)
            {
                this.updatePlateState(world_1, blockPos_1, blockState_1, redstoneOutput);
                if(entity_1 instanceof HostileEntity) entity_1.setOnFireFor(5);
            }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1)
    {
        return VoxelShapes.empty();
    }
}
