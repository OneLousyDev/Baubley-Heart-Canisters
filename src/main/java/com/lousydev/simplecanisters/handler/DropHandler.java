package com.lousydev.simplecanisters.handler;

import com.lousydev.simplecanisters.Reference;
import com.lousydev.simplecanisters.handler.registry.ItemRegistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class DropHandler
{
    @SubscribeEvent
    public static void onEntityDrop(LivingDropsEvent event)
    {
        LivingEntity entity = event.getEntityLiving();

        if (entity.world.isRemote)
        {
            return;
        }

        if (entity instanceof IMob)
        {
            Random random = entity.getRNG();
            if (!entity.isNonBoss())
            {
                if (entity instanceof EnderDragonEntity)
                {
                    if (random.nextDouble() < 1.00)
                    {
                        entity.entityDropItem(ItemRegistry.GREEN_HEART.get(), 1);
                    }
                }
                else if (random.nextDouble() < 1.00)
                {
                    entity.entityDropItem(ItemRegistry.ORANGE_HEART.get(), 1);
                }
            }

            else
            {
                if (entity instanceof EvokerEntity)
                {
                    if (random.nextDouble() < 1.00)
                    {
                        entity.entityDropItem(ItemRegistry.BLUE_HEART.get(), 1);
                    }
                }

                else if(!ModList.get().isLoaded("tinkersconstruct") && entity instanceof WitherSkeletonEntity)
                {
                    if (random.nextDouble() < 0.15)
                    {
                        entity.entityDropItem(ItemRegistry.WITHER_BONE.get(), 1);
                    }
                }

                else
                {
                    if (random.nextDouble() < 0.05)
                    {
                        entity.entityDropItem(ItemRegistry.RED_HEART.get(), 1);
                    }
                }
            }
        }
    }
}
