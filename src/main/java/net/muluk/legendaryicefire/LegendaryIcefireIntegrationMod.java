package net.muluk.legendaryicefire;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.muluk.legendaryicefire.item.ModItems;
import org.slf4j.Logger;
import sfiomn.legendarysurvivaloverhaul.LegendarySurvivalOverhaul;
import sfiomn.legendarysurvivaloverhaul.api.config.json_old.JsonPropertyValue;
import sfiomn.legendarysurvivaloverhaul.api.config.json_old.thirst.JsonEffectParameter;
import sfiomn.legendarysurvivaloverhaul.api.thirst.HydrationEnum;
import sfiomn.legendarysurvivaloverhaul.client.itemproperties.CanteenProperty;
import sfiomn.legendarysurvivaloverhaul.config.json_old.JsonConfig;
import sfiomn.legendarysurvivaloverhaul.registry.CreativeTabRegistry;

import static sfiomn.legendarysurvivaloverhaul.util.internal.ThirstUtilInternal.HYDRATION_ENUM_TAG;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LegendaryIcefireIntegrationMod.MOD_ID)
public class LegendaryIcefireIntegrationMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "legendaryicefire";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public LegendaryIcefireIntegrationMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        JsonConfig.registerDefaultConsumableThirst(LegendaryIcefireIntegrationMod.MOD_ID + ":fire_dragon_canteen", 3, 0.0f, new JsonEffectParameter[]{new JsonEffectParameter(LegendarySurvivalOverhaul.MOD_ID + ":thirst", 0.75f, 300, 0)}, new JsonPropertyValue(HYDRATION_ENUM_TAG, HydrationEnum.NORMAL.getName()));
        JsonConfig.registerDefaultConsumableThirst(LegendaryIcefireIntegrationMod.MOD_ID + ":fire_dragon_canteen", 6, 1.5f, new JsonPropertyValue(HYDRATION_ENUM_TAG, HydrationEnum.PURIFIED.getName()));

        JsonConfig.registerDefaultConsumableThirst(LegendaryIcefireIntegrationMod.MOD_ID + ":ice_dragon_canteen", 3, 0.0f, new JsonEffectParameter[]{new JsonEffectParameter(LegendarySurvivalOverhaul.MOD_ID + ":thirst", 0.75f, 300, 0)}, new JsonPropertyValue(HYDRATION_ENUM_TAG, HydrationEnum.NORMAL.getName()));
        JsonConfig.registerDefaultConsumableThirst(LegendaryIcefireIntegrationMod.MOD_ID + ":ice_dragon_canteen", 6, 1.5f, new JsonPropertyValue(HYDRATION_ENUM_TAG, HydrationEnum.PURIFIED.getName()));

        JsonConfig.registerDefaultConsumableThirst(LegendaryIcefireIntegrationMod.MOD_ID + ":lightning_dragon_canteen", 3, 0.0f, new JsonEffectParameter[]{new JsonEffectParameter(LegendarySurvivalOverhaul.MOD_ID + ":thirst", 0.75f, 300, 0)}, new JsonPropertyValue(HYDRATION_ENUM_TAG, HydrationEnum.NORMAL.getName()));
        JsonConfig.registerDefaultConsumableThirst(LegendaryIcefireIntegrationMod.MOD_ID + ":lightning_dragon_canteen", 6, 1.5f, new JsonPropertyValue(HYDRATION_ENUM_TAG, HydrationEnum.PURIFIED.getName()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == CreativeTabRegistry.LEGENDARY_CREATURES_TAB.get()) {
            event.accept(ModItems.FIRE_CANTEEN);
            event.accept(ModItems.ICE_CANTEEN);
            event.accept(ModItems.LIGHTNING_CANTEEN);
            event.accept(ModItems.VITAL_DRAGON_STEW);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemProperties.register(ModItems.FIRE_CANTEEN.get(),
                    ResourceLocation.fromNamespaceAndPath(LegendarySurvivalOverhaul.MOD_ID, "thirstenum"),
                    new CanteenProperty());
            ItemProperties.register(ModItems.ICE_CANTEEN.get(),
                    ResourceLocation.fromNamespaceAndPath(LegendarySurvivalOverhaul.MOD_ID, "thirstenum"),
                    new CanteenProperty());
            ItemProperties.register(ModItems.LIGHTNING_CANTEEN.get(),
                    ResourceLocation.fromNamespaceAndPath(LegendarySurvivalOverhaul.MOD_ID, "thirstenum"),
                    new CanteenProperty());
        }
    }
}
