package com.server.mods;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

import java.util.*;

public class WorldGenerationHandler {
    private static final int AREA_SIZE = 11; // Размер области в чанках
    private static final Set<BlockPos> occupiedAreas = new HashSet<>();

    public static BlockPos generateRaftForPlayer(ServerLevel world, ServerPlayer player) {
        BlockPos areaCenter = findFreeArea(world);
        generateRaft(world, areaCenter);
        return areaCenter;
    }

    private static BlockPos findFreeArea(ServerLevel world) {
        Random random = new Random();
        BlockPos areaCenter;
        int attempt = 0;

        do {
            int centerX = random.nextInt(1000) - 500;
            int centerZ = random.nextInt(1000) - 500;
            centerX = (centerX / 16 / AREA_SIZE) * AREA_SIZE * 16 + (AREA_SIZE * 16 / 2);
            centerZ = (centerZ / 16 / AREA_SIZE) * AREA_SIZE * 16 + (AREA_SIZE * 16 / 2);
            areaCenter = new BlockPos(centerX, 106, centerZ);
            attempt++;
        } while (occupiedAreas.contains(areaCenter) && attempt < 1000);

        occupiedAreas.add(areaCenter);
        return areaCenter;
    }

    private static void generateRaft(ServerLevel world, BlockPos center) {
        int centerY = 105; // Устанавливаем высоту на уровне поверхности океана

        // Создание плота в форме прямоугольника 5x7 блоков
        int width = 5;
        int length = 7;

        for (int dx = -width / 2; dx <= width / 2; dx++) {
            for (int dz = -length / 2; dz <= length / 2; dz++) {
                BlockPos newPos = new BlockPos(center.getX() + dx, centerY, center.getZ() + dz);
                world.setBlock(newPos, Blocks.BAMBOO_BLOCK.defaultBlockState(), 3);
            }
        }
        // Установка сундука в центре плота
        BlockPos chestPos = new BlockPos(center.getX(), centerY + 1, center.getZ() + 1);
        world.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 3);

        // Установка блока забора, на котором стоит факел
        BlockPos fencePos = new BlockPos(center.getX(), centerY + 1, center.getZ() + 2);
        world.setBlock(fencePos, Blocks.OAK_FENCE.defaultBlockState(), 3);

        BlockPos torchPos = new BlockPos(center.getX(), centerY + 2, center.getZ() + 2);
        world.setBlock(torchPos, Blocks.TORCH.defaultBlockState(), 3);

        // Наполнение сундука базовыми предметами
        BlockEntity blockEntity = world.getBlockEntity(chestPos);
        if (blockEntity instanceof ChestBlockEntity) {
            ChestBlockEntity chest = (ChestBlockEntity) blockEntity;

            // Создание зачарованного предмета
            ItemStack enchantedPickaxe = new ItemStack(Items.FISHING_ROD);
            enchantedPickaxe.enchant(Enchantments.UNBREAKING, 3);

            chest.setItem(0, enchantedPickaxe);
            chest.setItem(1, new ItemStack(Items.APPLE, 5));
            chest.setItem(2, new ItemStack(Items.BREAD, 3));
            chest.setItem(3, new ItemStack(Items.STONE_AXE));
        }
    }
}
