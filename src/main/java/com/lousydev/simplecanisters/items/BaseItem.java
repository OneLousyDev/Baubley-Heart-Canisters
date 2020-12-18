package com.lousydev.simplecanisters.items;

import com.lousydev.simplecanisters.SimpleCanisters;
import net.minecraft.item.Item;

public class BaseItem extends Item
{
    public BaseItem()
    {
        super(new Item.Properties().group(SimpleCanisters.CREATIVE_TAB));
    }
}
