package net.muluk.legendaryicefire.item.drink;

import net.minecraft.world.item.ItemStack;
import net.muluk.legendaryicefire.LegendaryIcefireIntegrationMod;
import org.jetbrains.annotations.NotNull;
import sfiomn.legendarysurvivaloverhaul.api.thirst.HydrationEnum;
import sfiomn.legendarysurvivaloverhaul.api.thirst.ThirstUtil;
import sfiomn.legendarysurvivaloverhaul.common.items.drink.CanteenItem;
import sfiomn.legendarysurvivaloverhaul.config.Config;

public class IceDragonCanteenItem extends CanteenItem {

    public IceDragonCanteenItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxCapacity() {
        return Config.Baked.largeCanteenCapacity;
    }

    @Override
    public @NotNull String getDescriptionId(ItemStack stack) {
        if(ThirstUtil.getCapacityTag(stack) == 0)
            return "item."+ LegendaryIcefireIntegrationMod.MOD_ID+"."+"ice_dragon_canteen_empty";

        if (ThirstUtil.getHydrationEnumTag(stack) == HydrationEnum.PURIFIED)
            return "item."+ LegendaryIcefireIntegrationMod.MOD_ID+"."+"ice_dragon_canteen_purified";
        else
            return "item."+ LegendaryIcefireIntegrationMod.MOD_ID+"."+"ice_dragon_canteen";
    }

    @Override
    public void fill(ItemStack stack) {
        ThirstUtil.setCapacityTag(stack,  Math.min(getMaxCapacity(), ThirstUtil.getCapacityTag(stack) + 1));
        ThirstUtil.setHydrationEnumTag(stack, HydrationEnum.PURIFIED);
    }

}
