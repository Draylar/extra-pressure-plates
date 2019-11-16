package com.github.draylar.extrapp;

import com.github.draylar.extrapp.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ExtraPressurePlates implements ModInitializer {

	private static final String MODID = "extra-pressure-plates";

	@Override
	public void onInitialize() {
		ModBlocks.init();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
