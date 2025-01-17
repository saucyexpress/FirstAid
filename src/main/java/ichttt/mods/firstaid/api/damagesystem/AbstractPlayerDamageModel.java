/*
 * FirstAid API
 * Copyright (c) 2017-2023
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package ichttt.mods.firstaid.api.damagesystem;

import ichttt.mods.firstaid.api.enums.EnumPlayerPart;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

public abstract class AbstractPlayerDamageModel implements Iterable<AbstractDamageablePart>, INBTSerializable<CompoundTag> {
    public final AbstractDamageablePart HEAD;
    public final AbstractDamageablePart LEFT_ARM;
    public final AbstractDamageablePart LEFT_LEG;
    public final AbstractDamageablePart LEFT_FOOT;
    public final AbstractDamageablePart BODY;
    public final AbstractDamageablePart RIGHT_ARM;
    public final AbstractDamageablePart RIGHT_LEG;
    public final AbstractDamageablePart RIGHT_FOOT;
    public boolean hasTutorial;

    public AbstractPlayerDamageModel(AbstractDamageablePart head, AbstractDamageablePart leftArm, AbstractDamageablePart leftLeg, AbstractDamageablePart leftFoot, AbstractDamageablePart body, AbstractDamageablePart rightArm, AbstractDamageablePart rightLeg, AbstractDamageablePart rightFoot) {
        this.HEAD = head;
        this.LEFT_ARM = leftArm;
        this.LEFT_LEG = leftLeg;
        this.LEFT_FOOT = leftFoot;
        this.BODY = body;
        this.RIGHT_ARM = rightArm;
        this.RIGHT_LEG = rightLeg;
        this.RIGHT_FOOT = rightFoot;
    }

    public AbstractDamageablePart getFromEnum(EnumPlayerPart part) {
        switch (part) {
            case HEAD:
                return HEAD;
            case LEFT_ARM:
                return LEFT_ARM;
            case LEFT_LEG:
                return LEFT_LEG;
            case BODY:
                return BODY;
            case RIGHT_ARM:
                return RIGHT_ARM;
            case RIGHT_LEG:
                return RIGHT_LEG;
            case LEFT_FOOT:
                return LEFT_FOOT;
            case RIGHT_FOOT:
                return RIGHT_FOOT;
            default:
                throw new RuntimeException("Unknown enum " + part);
        }
    }

    /**
     * Updates the part.
     * Should not be called by other mods!
     */
    public abstract void tick(Level world, Player player);

    /**
     * @deprecated Migrated to a potion effect, pass it in to directly apply
     */
    @Deprecated
    public abstract void applyMorphine();

    public abstract void applyMorphine(Player player);

    /**
     * @deprecated Migrated to a potion effect
     */
    @Deprecated
    public abstract int getMorphineTicks();

    /**
     * Checks if the player is dead.
     * This does not mean that the player cannot be revived.
     *
     * @param player The player to check. If null, it will not be checked if the player can be revived (Using PlayerRevival)
     * @return true if dead, false otherwise
     */
    public abstract boolean isDead(@Nullable Player player);

    public abstract Float getAbsorption();

    public abstract void setAbsorption(float absorption);

    public abstract int getCurrentMaxHealth();

    @OnlyIn(Dist.CLIENT)
    public abstract int getMaxRenderSize();

    public abstract void sleepHeal(Player player);

    public abstract void revivePlayer(Player player);

    public abstract void runScaleLogic(Player player);

    public abstract void scheduleResync();

    public abstract boolean hasNoCritical();
}
