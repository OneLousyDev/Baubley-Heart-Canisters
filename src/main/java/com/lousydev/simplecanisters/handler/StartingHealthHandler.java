package com.lousydev.simplecanisters.handler;

import com.lousydev.simplecanisters.Reference;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Reference.MODID)
public class StartingHealthHandler
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void setStartingHealth(EntityJoinWorldEvent event)
    {
        //todo if (ConfigHandler.allowStartingHealthTweaks)

        if (event.getEntity() instanceof PlayerEntity && !(event.getEntity() instanceof FakePlayer))
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();

           //todo if (ConfigHandler.startingHealth > 0)

                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);

        }
    }
}
