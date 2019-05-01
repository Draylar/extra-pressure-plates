package com.github.draylar.extraPP;

import com.github.draylar.extraPP.common.Blocks;
import com.github.draylar.extraPP.common.Items;
import net.fabricmc.api.ModInitializer;

public class ExtraPressurePlates implements ModInitializer {
	@Override
	public void onInitialize() {
		Blocks.register();
		Items.register();
	}
}
