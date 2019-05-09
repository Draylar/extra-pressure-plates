package com.github.draylar.extraPP.common;

import com.github.draylar.extraPP.common.blocks.CustomInvisiblePressurePlateBlock;
import com.github.draylar.extraPP.common.blocks.CustomPressurePlateBlock;
import com.github.draylar.extraPP.common.blocks.blockUtils.PressurePlateTask;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks
{
    static final CustomPressurePlateBlock OBSIDIAN = createPressurePlate(PressurePlateBlock.Type.STONE, Material.STONE, 1, null);
    static final CustomPressurePlateBlock GLASS = createPressurePlate(PressurePlateBlock.Type.WOOD, Material.GLASS, .5f, null);
    static final CustomPressurePlateBlock SLIME = createPressurePlate(PressurePlateBlock.Type.WOOD, Material.CLAY, .3f, BlockSoundGroup.SLIME, null);
    static final CustomPressurePlateBlock CACTUS = createPressurePlate(PressurePlateBlock.Type.WOOD, Material.CACTUS, .5f, (state, world, pos, entity) ->
    {
        if(entity instanceof HostileEntity) entity.damage(DamageSource.CACTUS, 4);
    });

    static final CustomPressurePlateBlock FIERY = createPressurePlate(PressurePlateBlock.Type.STONE, Material.STONE, 1, (state, world, pos, entity) ->
    {
        if(entity instanceof HostileEntity) entity.setOnFireFor(5);
    });

    static final CustomPressurePlateBlock INVISIBLE_OBSIDIAN = createInvisiblePressurePlate(OBSIDIAN);
    static final CustomPressurePlateBlock INVISIBLE_CACTUS = createInvisiblePressurePlate(CACTUS);
    static final CustomPressurePlateBlock INVISIBLE_FIERY = createInvisiblePressurePlate(FIERY);
    static final CustomPressurePlateBlock INVISIBLE_SLIME = createInvisiblePressurePlate(SLIME);

    private static CustomPressurePlateBlock createPressurePlate(PressurePlateBlock.Type type, Material material, float hardness, PressurePlateTask task)
    {
        return new CustomPressurePlateBlock(type, FabricBlockSettings.of(material).hardness(hardness).build(), task);
    }

    private static CustomPressurePlateBlock createPressurePlate(PressurePlateBlock.Type type, Material material, float hardness, BlockSoundGroup group, PressurePlateTask task)
    {
        return new CustomPressurePlateBlock(type, FabricBlockSettings.of(material).hardness(hardness).sounds(group).build(), task);
    }

    private static CustomPressurePlateBlock createInvisiblePressurePlate(CustomPressurePlateBlock block)
    {
        return new CustomInvisiblePressurePlateBlock(block);
    }

    public static void register()
    {
        register("obsidian_pressure_plate", OBSIDIAN);
        register("invisible_obsidian_pressure_plate", INVISIBLE_OBSIDIAN);
        register("cactus_pressure_plate", CACTUS);
        register("invisible_cactus_pressure_plate", INVISIBLE_CACTUS);
        register("fiery_pressure_plate", FIERY);
        register("invisible_fiery_pressure_plate", INVISIBLE_FIERY);
        register("glass_pressure_plate", GLASS);
        register("slime_pressure_plate", SLIME);
        register("invisible_slime_pressure_plate", INVISIBLE_SLIME);
    }

    private static void register(String name, PressurePlateBlock block)
    {
        Registry.register(Registry.BLOCK, new Identifier("extra-pressure-plates", name), block);
    }
}
