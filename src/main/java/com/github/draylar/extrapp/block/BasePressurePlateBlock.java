package com.github.draylar.extrapp.block;

import com.github.draylar.extrapp.ExtraPressurePlates;
import com.github.draylar.extrapp.utils.CollisionTask;
import com.github.draylar.extrapp.utils.CollisionValidator;
import com.github.draylar.extrapp.utils.PressurePlateRenderType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BasePressurePlateBlock extends PressurePlateBlock {

    private PressurePlateRenderType renderType;
    private CollisionTask collisionTask;
    private CollisionValidator collisionValidator;
    private boolean isInvisible;

    private BasePressurePlateBlock(Builder builder) {
        super(builder.activationRule, builder.settings);
        renderType = builder.renderType;
        collisionTask = builder.collisionTask;
        collisionValidator = builder.collisionValidator;
        isInvisible = builder.isInvisible;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient) {
            if (collisionValidator.canCollide(entity)) {
                int outputLevel = this.getRedstoneOutput(state);

                if (outputLevel == 0) {
                    this.updatePlateState(world, pos, state, outputLevel);

                    if (collisionTask != null) {
                        collisionTask.run(state, world, pos, entity);
                    }
                }
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean isSideInvisible(BlockState originalState, BlockState offsetState, Direction direction) {
        if (renderType == PressurePlateRenderType.GLASS) {
            return offsetState.getBlock() == this || super.isSideInvisible(originalState, offsetState, direction);
        }

        return false;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        if(isInvisible) {
            return BlockRenderType.INVISIBLE;
        }

        return super.getRenderType(state);
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView view, BlockPos pos) {
        return renderType == PressurePlateRenderType.GLASS || renderType == PressurePlateRenderType.SLIME;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState state, BlockView view, BlockPos pos) {
        return false;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView view, BlockPos pos) {
        return renderType == PressurePlateRenderType.GLASS ? 1.0f : super.getAmbientOcclusionLightLevel(state, view, pos);
    }

    public static final class Builder {

        private Settings settings = FabricBlockSettings.of(Material.STONE).hardness(1).build();
        private ActivationRule activationRule = ActivationRule.EVERYTHING;
        private PressurePlateRenderType renderType = PressurePlateRenderType.DEFAULT;
        private CollisionTask collisionTask = (state, world, pos, entity) -> { };
        private CollisionValidator collisionValidator = entity -> true;
        private boolean registerInvisibleVariant = true;
        private boolean isInvisible = false;

        public Builder() {
            // BUILDER NO-OP
        }

        public Builder withSettings(Settings settings) {
            this.settings = settings;
            return this;
        }

        public Builder withHardness(float hardness) {
            this.settings = FabricBlockSettings.copyOf(settings).hardness(hardness).build();
            return this;
        }

        public Builder withActivationRule(ActivationRule rule) {
            activationRule = rule;
            return this;
        }

        public Builder withRenderType(PressurePlateRenderType type) {
            renderType = type;
            return this;
        }

        public Builder onCollision(CollisionTask task) {
            collisionTask = task;
            return this;
        }

        public Builder withCollisionValidator(CollisionValidator validator) {
            collisionValidator = validator;
            return this;
        }

        public Builder registerInvisibleVariant(boolean registerInvisibleVariant) {
            this.registerInvisibleVariant = registerInvisibleVariant;
            return this;
        }

        private Builder isInvisible(boolean isInvisible) {
            this.isInvisible = isInvisible;
            return this;
        }

        public BasePressurePlateBlock buildAndRegister(String name) {
            BasePressurePlateBlock registered = Registry.register(Registry.BLOCK, ExtraPressurePlates.id(name + "_pressure_plate"), new BasePressurePlateBlock(this));
            Registry.register(Registry.ITEM, ExtraPressurePlates.id(name + "_pressure_plate"), new BlockItem(registered, new Item.Settings().group(ItemGroup.REDSTONE)));

            if(registerInvisibleVariant) {
                BasePressurePlateBlock registeredInvisible = Registry.register(Registry.BLOCK, ExtraPressurePlates.id("invisible_" + name + "_pressure_plate"), new BasePressurePlateBlock(this.isInvisible(true)));
                Registry.register(Registry.ITEM, ExtraPressurePlates.id("invisible_" + name + "_pressure_plate"), new BlockItem(registeredInvisible, new Item.Settings().group(ItemGroup.REDSTONE)));
            }

            return registered;
        }
    }
}