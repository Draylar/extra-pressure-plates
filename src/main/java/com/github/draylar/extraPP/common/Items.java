package com.github.draylar.extraPP.common;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items
{
    private static final BlockItem OBSIDIAN = new BlockItem(Blocks.OBSIDIAN, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem INVISIBLE_OBSIDIAN = new BlockItem(Blocks.INVISIBLE_OBSIDIAN, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem CACTUS = new BlockItem(Blocks.CACTUS, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem INVISIBLE_CACTUS = new BlockItem(Blocks.INVISIBLE_CACTUS, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem FIERY = new BlockItem(Blocks.FIERY, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem INVISIBLE_FIERY = new BlockItem(Blocks.INVISIBLE_FIERY, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem GLASS = new BlockItem(Blocks.GLASS, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem SLIME = new BlockItem(Blocks.SLIME, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem INVISIBLE_SLIME = new BlockItem(Blocks.INVISIBLE_SLIME, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem OCEAN = new BlockItem(Blocks.OCEAN, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
    private static final BlockItem INVISIBLE_OCEAN = new BlockItem(Blocks.INVISIBLE_OCEAN, new Item.Settings().itemGroup(ItemGroup.REDSTONE));

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

    private static void register(String name, Item item)
    {
        Registry.register(Registry.ITEM, new Identifier("extra-pressure-plates", name), item);
    }
}
