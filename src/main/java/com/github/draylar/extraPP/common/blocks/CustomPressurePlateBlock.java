package com.github.draylar.extraPP.common.blocks;

import com.github.draylar.extraPP.common.blocks.blockUtils.PressurePlateTask;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomPressurePlateBlock extends PressurePlateBlock
{
    public final PressurePlateBlock.Type TYPE;
    public final Settings SETTINGS;
    public final PressurePlateTask TASK;

    protected CustomPressurePlateBlock(Type pressurePlateBlock$Type_1, Settings block$Settings_1, PressurePlateTask task)
    {
        super(pressurePlateBlock$Type_1, block$Settings_1);
        this.TYPE = pressurePlateBlock$Type_1;
        this.SETTINGS = block$Settings_1;
        this.TASK = task;
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

}
