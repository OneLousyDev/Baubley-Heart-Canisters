package com.lousydev.simplecanisters.items;

import com.lousydev.simplecanisters.SimpleCanisters;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ItemFoodRelicApple extends Item
{
    public ItemFoodRelicApple()
    {
        super( new Item.Properties().group(SimpleCanisters.CREATIVE_TAB).food(new Food.Builder()
                .effect(new EffectInstance(Effects.RESISTANCE, 20*60, 1), 1)
                .effect(new EffectInstance(Effects.STRENGTH, 20*60, 1), 1)
                .effect(new EffectInstance(Effects.HASTE, 20*60, 1), 1)
                .setAlwaysEdible()
                .build())
        );
    }
}
