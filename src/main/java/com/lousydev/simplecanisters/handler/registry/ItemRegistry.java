package com.lousydev.simplecanisters.handler.registry;

import com.lousydev.simplecanisters.Reference;
import com.lousydev.simplecanisters.items.*;
import com.lousydev.simplecanisters.items.canisters.*;
import com.lousydev.simplecanisters.items.hearts.*;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemRegistry
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<Item> RED_HEART_CANISTER = ITEMS.register("red_heart_canister", RedHeartCanister::new);
    public static final RegistryObject<Item> ORANGE_HEART_CANISTER = ITEMS.register("orange_heart_canister", OrangeHeartCanister::new);
    public static final RegistryObject<Item> GREEN_HEART_CANISTER = ITEMS.register("green_heart_canister", GreenHeartCanister::new);
    public static  final RegistryObject<Item> BLUE_HEART_CANISTER = ITEMS.register("blue_heart_canister", BlueHeartCanister::new);
    public static  final RegistryObject<Item> WITHER_BONE = ITEMS.register("wither_bone", BaseItem::new);
    public static  final RegistryObject<Item> CANISTER = ITEMS.register("canister", BaseItem::new);
    public static  final RegistryObject<Item> RELIC_APPLE = ITEMS.register("relic_apple", ItemFoodRelicApple::new);
    public static final RegistryObject<Item> HEART_AMULET = ITEMS.register("heart_amulet", ItemHeartAmulet::new);
    public static final RegistryObject<Item> RED_HEART = ITEMS.register("red_heart", RedHeartItem::new);
    public static final RegistryObject<Item> ORANGE_HEART = ITEMS.register("orange_heart", OrangeHeartItem::new);
    public static final RegistryObject<Item> GREEN_HEART = ITEMS.register("green_heart", GreenHeartItem::new);
    public static final RegistryObject<Item> BLUE_HEART = ITEMS.register("blue_heart", BlueHeartItem::new);

    public static void registerItems()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
