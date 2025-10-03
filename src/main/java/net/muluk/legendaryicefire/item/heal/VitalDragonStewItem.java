package net.muluk.legendaryicefire.item.heal;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;
import sfiomn.legendarysurvivaloverhaul.common.items.heal.BodyHealingItem;
import sfiomn.legendarysurvivaloverhaul.config.Config;

public class VitalDragonStewItem extends BodyHealingItem {
    public VitalDragonStewItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return Config.Baked.bandageUseTime;
    }
}