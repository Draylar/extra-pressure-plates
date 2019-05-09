package com.github.draylar.extraPP.common;

import com.github.draylar.extraPP.common.blocks.cactus.CactusPressurePlate;
import com.github.draylar.extraPP.common.blocks.cactus.InvisibleCactusPressurePlate;
import com.github.draylar.extraPP.common.blocks.fiery.FieryPressurePlate;
import com.github.draylar.extraPP.common.blocks.fiery.InvisibleFieryPressurePlate;
import com.github.draylar.extraPP.common.blocks.glass.GlassPressurePlate;
import com.github.draylar.extraPP.common.blocks.obsidian.InvisibleObsidianPressurePlate;
import com.github.draylar.extraPP.common.blocks.obsidian.ObsidianPressurePlate;
import com.github.draylar.extraPP.common.blocks.slime.InvisibleSlimePressurePlate;
import com.github.draylar.extraPP.common.blocks.slime.SlimePressurePlate;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks
{
    static final ObsidianPressurePlate OBSIDIAN = new ObsidianPressurePlate(PressurePlateBlock.Type.STONE, FabricBlockSettings.of(Material.STONE).hardness(1f).build());
    static final InvisibleObsidianPressurePlate INVISIBLE_OBSIDIAN = new InvisibleObsidianPressurePlate(PressurePlateBlock.Type.STONE, FabricBlockSettings.of(Material.STONE).hardness(1f).build());

    static final CactusPressurePlate CACTUS = new CactusPressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.CACTUS).hardness(.5f).build());
    static final InvisibleCactusPressurePlate INVISIBLE_CACTUS = new InvisibleCactusPressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.CACTUS).hardness(.5f).build());

    static final FieryPressurePlate FIERY = new FieryPressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.STONE).hardness(1f).build());
    static final InvisibleFieryPressurePlate INVISIBLE_FIERY = new InvisibleFieryPressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.STONE).hardness(1f).build());

    static final GlassPressurePlate GLASS = new GlassPressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.GLASS).hardness(.5f).build());

    static final SlimePressurePlate SLIME = new SlimePressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.CLAY).friction(0.8F).sounds(BlockSoundGroup.SLIME).hardness(.3f).build());
    static final InvisibleSlimePressurePlate INVISIBLE_SLIME = new InvisibleSlimePressurePlate(PressurePlateBlock.Type.WOOD, FabricBlockSettings.of(Material.CLAY).friction(0.8F).sounds(BlockSoundGroup.SLIME).hardness(.3f).build());

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
