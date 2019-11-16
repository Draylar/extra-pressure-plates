package com.github.draylar.extrapp.utils;

import net.minecraft.entity.Entity;

public interface CollisionValidator {
    boolean canCollide(Entity entity);
}
