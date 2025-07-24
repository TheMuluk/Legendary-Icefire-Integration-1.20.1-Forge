package net.muluk.legendaryicefire.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.muluk.legendaryicefire.LegendaryIcefireIntegrationMod;
import net.muluk.legendaryicefire.item.drink.FireDragonCanteenItem;
import net.muluk.legendaryicefire.item.drink.IceDragonCanteenItem;
import net.muluk.legendaryicefire.item.drink.LightningDragonCanteenItem;
import net.muluk.legendaryicefire.item.heal.VitalDragonStewItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LegendaryIcefireIntegrationMod.MOD_ID);

    public static final RegistryObject<Item> FIRE_CANTEEN = ITEMS.register("fire_dragon_canteen",
            () -> new FireDragonCanteenItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ICE_CANTEEN = ITEMS.register("ice_dragon_canteen",
            () -> new IceDragonCanteenItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> LIGHTNING_CANTEEN = ITEMS.register("lightning_dragon_canteen",
            () -> new LightningDragonCanteenItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> VITAL_DRAGON_STEW = ITEMS.register("vital_dragon_stew",
            () -> new VitalDragonStewItem(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
