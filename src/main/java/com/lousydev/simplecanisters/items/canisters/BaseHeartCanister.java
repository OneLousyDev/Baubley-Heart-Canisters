package com.lousydev.simplecanisters.items.canisters;

import com.lazy.baubles.api.bauble.BaubleType;
import com.lazy.baubles.api.bauble.IBauble;

import com.lousydev.simplecanisters.SimpleCanisters;
import com.lousydev.simplecanisters.handler.ConfigHandler;
import com.lousydev.simplecanisters.util.HeartType;

import net.minecraft.entity.Entity;;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class BaseHeartCanister extends Item implements IBauble
{

    public final HeartType type;
    public final BaubleType bauble;

    public BaseHeartCanister(HeartType type, BaubleType bauble)
    {
        super(new Item.Properties().group(SimpleCanisters.CREATIVE_TAB));
        this.bauble = bauble;
        this.type = type;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return bauble;
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EquipmentSlotType armorType,  Entity player)
    {
        //TODO if(ConfigHandler.canEquipHeartCanisterWithoutAmulet)

        return false;
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        //todo remove hardcoded limit
        return 10;
    }
}

