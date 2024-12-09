package com.server.mods;

import com.server.mods.command.CommandRegister;
import com.server.mods.events.FishingHandler;
import com.server.mods.utils.RegistryHandler;
import com.server.mods.world.WorldGenerationHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Raft.MOD_ID)
public class Raft {
    public static final String MOD_ID = "raft";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Raft() {
        // Создаём EventBus для событий модов
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация объектов, таких как MenuType, Items, Blocks и т.д.
        RegistryHandler.register(modEventBus); // <<< ВАЖНО! Регистрируем все объекты (меню и другие)

        // Регистрируем ClientSetup и CommonSetup события
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);

        // Регистрируем события MinecraftForge
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new FishingHandler());
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Регистрируем GUI для меню
        RegistryHandler.registerScreens();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("\n" +
                " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  _______     | || |      __      | || |  _________   | || |  _________   | |\n" +
                "| | |_   __ \\    | || |     /  \\     | || | |_   ___  |  | || | |  _   _  |  | |\n" +
                "| |   | |__) |   | || |    / /\\ \\    | || |   | |_  \\_|  | || | |_/ | | \\_|  | |\n" +
                "| |   |  __ /    | || |   / ____ \\   | || |   |  _|      | || |     | |      | |\n" +
                "| |  _| |  \\ \\_  | || | _/ /    \\ \\_ | || |  _| |_       | || |    _| |_     | |\n" +
                "| | |____| |___| | || ||____|  |____|| || | |_____|      | || |   |_____|    | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'\n");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("\n" +
                " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  _______     | || |      __      | || |  _________   | || |  _________   | |\n" +
                "| | |_   __ \\    | || |     /  \\     | || | |_   ___  |  | || | |  _   _  |  | |\n" +
                "| |   | |__) |   | || |    / /\\ \\    | || |   | |_  \\_|  | || | |_/ | | \\_|  | |\n" +
                "| |   |  __ /    | || |   / ____ \\   | || |   |  _|      | || |     | |      | |\n" +
                "| |  _| |  \\ \\_  | || | _/ /    \\ \\_ | || |  _| |_       | || |    _| |_     | |\n" +
                "| | |____| |___| | || ||____|  |____|| || | |_____|      | || |   |_____|    | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'\n");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandRegister.registerAll(event);
    }
}
