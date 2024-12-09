package com.server.mods.utils;

import com.server.mods.client.RaftScreen;
import com.server.mods.containers.RaftMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {
    // Регистрация всех меню
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "raft");

    // Регистрация типа меню (RAFT_MENU)
    public static final RegistryObject<MenuType<RaftMenu>> RAFT_MENU = MENUS.register("raft_menu",
            () -> IForgeMenuType.create(RaftMenu::new)
    );

    // Регистрация меню в EventBus
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

    // Регистрация экранов (MenuScreens)
    public static void registerScreens() {
        MenuScreens.register(RAFT_MENU.get(), RaftScreen::new);
    }
}
