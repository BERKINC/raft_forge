package com.server.mods.containers;

import com.server.mods.utils.RegistryHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class RaftMenu extends AbstractContainerMenu {

    // Серверный конструктор
    public RaftMenu(int containerId, Inventory playerInventory) {
        super(RegistryHandler.RAFT_MENU.get(), containerId);

        // Пример слота (0, 80, 35) — это координаты слота на экране
        this.addSlot(new Slot(playerInventory, 0, 80, 35));

        layoutPlayerInventorySlots(playerInventory, 8, 84);
    }

    // Клиентский конструктор
    public RaftMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    private void layoutPlayerInventorySlots(Inventory playerInventory, int leftCol, int topRow) {
        // Основные слоты инвентаря игрока
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = leftCol + col * 18;
                int y = topRow + row * 18;
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }

        // Горячая панель игрока (Hotbar)
        for (int col = 0; col < 9; ++col) {
            int x = leftCol + col * 18;
            int y = topRow + 58;
            this.addSlot(new Slot(playerInventory, col, x, y));
        }
    }
}
