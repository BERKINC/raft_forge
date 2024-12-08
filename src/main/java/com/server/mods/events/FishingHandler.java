package com.server.mods.events;

import com.server.mods.Raft;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Raft.MOD_ID)
public class FishingHandler {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        if (!event.getEntity().level().isClientSide) {
            // Удаляем стандартные дропы
            for (ItemStack itemStack : event.getDrops()) {
                itemStack.setCount(0);
            }

            // Получаем игрока
            Player player = event.getEntity();
            if (player == null) {
                return;
            }

            // Добавляем случайные дропы
            ItemStack newItemStack;
            // Категории предметов
            Item[] veryCommonItems = {
                    Items.STICK, Items.OAK_LEAVES, Items.BIRCH_LEAVES, Items.SPRUCE_LEAVES,
                    Items.BAMBOO, Items.SEAGRASS, Items.KELP, Items.DANDELION, Items.POPPY
            };
            Item[] commonItems = {
                    Items.APPLE, Items.MELON_SLICE, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS,
                    Items.WHEAT_SEEDS, Items.SUGAR_CANE, Items.COBBLESTONE, Items.FLINT
            };
            Item[] uncommonItems = {
                    Items.DIRT, Items.SAND, Items.GRAVEL, Items.CLAY_BALL, Items.OAK_LOG,
                    Items.SPRUCE_LOG, Items.BIRCH_LOG, Items.MOSS_BLOCK, Items.VINE
            };
            Item[] rareItems = {
                    Items.COAL, Items.COPPER_INGOT, Items.IRON_INGOT, Items.GOLD_NUGGET,
                    Items.LAPIS_LAZULI, Items.REDSTONE, Items.OBSIDIAN, Items.BONE_MEAL
            };
            Item[] veryRareItems = {
                    Items.DIAMOND, Items.EMERALD, Items.GOLD_INGOT, Items.ENCHANTED_BOOK,
                    Items.NETHERITE_SCRAP, Items.AMETHYST_SHARD, Items.END_CRYSTAL
            };

            // Шансы выпадения предметов
            float chance = RANDOM.nextFloat();

            if (chance < 0.40) { // 40% шанс на очень обычные предметы
                newItemStack = new ItemStack(veryCommonItems[RANDOM.nextInt(veryCommonItems.length)]);
            } else if (chance < 0.70) { // 30% шанс на обычные предметы
                newItemStack = new ItemStack(commonItems[RANDOM.nextInt(commonItems.length)]);
            } else if (chance < 0.85) { // 15% шанс на необычные предметы
                newItemStack = new ItemStack(uncommonItems[RANDOM.nextInt(uncommonItems.length)]);
            } else if (chance < 0.95) { // 10% шанс на редкие предметы
                newItemStack = new ItemStack(rareItems[RANDOM.nextInt(rareItems.length)]);
            } else { // 5% шанс на очень редкие предметы
                newItemStack = new ItemStack(veryRareItems[RANDOM.nextInt(veryRareItems.length)]);
            }

            // Создаем предмет на месте рыбалки
            ItemEntity newItem = new ItemEntity(
                    player.level(),
                    event.getHookEntity().getX(),
                    event.getHookEntity().getY(),
                    event.getHookEntity().getZ(),
                    newItemStack
            );
            player.level().addFreshEntity(newItem);

            // Подбираем предмет
            newItem.playerTouch(player);
        }
    }
}
