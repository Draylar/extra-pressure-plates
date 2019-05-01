package com.github.draylar.extraPP.common.blocks.poison;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PoisonedPressurePlate extends PressurePlateBlock
{
    public PoisonedPressurePlate(Type pressurePlateBlock$Type_1, Settings block$Settings_1)
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
                if(entity_1 instanceof LivingEntity)
                {
                    ((LivingEntity) entity_1).addPotionEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 5, 1));
                }
            }
        }
    }
}
