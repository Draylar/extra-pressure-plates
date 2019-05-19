package com.github.draylar.extraPP.common;

import com.github.draylar.extraPP.common.blocks.CustomInvisiblePressurePlateBlock;
import com.github.draylar.extraPP.common.blocks.CustomPressurePlateBlock;
import com.github.draylar.extraPP.common.blocks.blockUtils.CollisionCheck;
import com.github.draylar.extraPP.common.blocks.blockUtils.PressurePlateRenderType;
import com.github.draylar.extraPP.common.blocks.blockUtils.PressurePlateTask;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks
{
    static final CustomPressurePlateBlock OBSIDIAN = createPressurePlate(PressurePlateBlock.Type.STONE, Material.STONE, 1, null, PressurePlateRenderType.DEFAULT, t -> t instanceof PlayerEntity);
    static final CustomPressurePlateBlock GLASS = createPressurePlate(PressurePlateBlock.Type.WOOD, Material.GLASS, .5f, null, PressurePlateRenderType.GLASS);
    static final CustomPressurePlateBlock SLIME = createPressurePlate(PressurePlateBlock.Type.WOOD, Material.CLAY, .3f, BlockSoundGroup.SLIME, null, PressurePlateRenderType.SLIME);
    static final CustomPressurePlateBlock CACTUS = createPressurePlate(PressurePlateBlock.Type.WOOD, Material.CACTUS, .5f, (state, world, pos, entity) ->
    {
        if(entity instanceof HostileEntity) entity.damage(DamageSource.CACTUS, 4);
    }, PressurePlateRenderType.DEFAULT);

    static final CustomPressurePlateBlock FIERY = createPressurePlate(PressurePlateBlock.Type.STONE, Material.STONE, 1, (state, world, pos, entity) ->
    {
        if(entity instanceof HostileEntity) entity.setOnFireFor(5);
    }, PressurePlateRenderType.DEFAULT);
    static final CustomPressurePlateBlock OCEAN = createPressurePlate(PressurePlateBlock.Type.STONE, Material.STONE, .7f, (state, world, pos, entity) ->
    {
        if(entity instanceof LivingEntity)
        {
            entity.extinguish();
            ((LivingEntity) entity).addPotionEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 20 * 15, 2));
        }
    }, PressurePlateRenderType.DEFAULT);

    static final CustomPressurePlateBlock INVISIBLE_OBSIDIAN = createInvisiblePressurePlate(OBSIDIAN);
    static final CustomPressurePlateBlock INVISIBLE_CACTUS = createInvisiblePressurePlate(CACTUS);
    static final CustomPressurePlateBlock INVISIBLE_FIERY = createInvisiblePressurePlate(FIERY);
    static final CustomPressurePlateBlock INVISIBLE_SLIME = createInvisiblePressurePlate(SLIME);
    static final CustomPressurePlateBlock INVISIBLE_OCEAN = createInvisiblePressurePlate(OCEAN);

    private static CustomPressurePlateBlock createPressurePlate(PressurePlateBlock.Type type, Material material, float hardness, PressurePlateTask task, PressurePlateRenderType renderType)
    {
        return new CustomPressurePlateBlock(type, FabricBlockSettings.of(material).hardness(hardness).build(), task, renderType, null);
    }

    private static CustomPressurePlateBlock createPressurePlate(PressurePlateBlock.Type type, Material material, float hardness, BlockSoundGroup group, PressurePlateTask task, PressurePlateRenderType renderType)
    {
        return new CustomPressurePlateBlock(type, FabricBlockSettings.of(material).hardness(hardness).sounds(group).build(), task, renderType, null);
    }

    private static CustomPressurePlateBlock createPressurePlate(PressurePlateBlock.Type type, Material material, float hardness, PressurePlateTask task, PressurePlateRenderType renderType, CollisionCheck check)
    {
        return new CustomPressurePlateBlock(type, FabricBlockSettings.of(material).hardness(hardness).build(), task, renderType, check);
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
        register("ocean_pressure_plate", OCEAN);
        register("invisible_ocean_pressure_plate", INVISIBLE_OCEAN);
    }

    private static void register(String name, PressurePlateBlock block)
    {
        Registry.register(Registry.BLOCK, new Identifier("extra-pressure-plates", name), block);
    }
}
