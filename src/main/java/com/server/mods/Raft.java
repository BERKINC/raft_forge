package com.server.mods;

import com.server.mods.events.FishingHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Raft.MOD_ID)
public class Raft {
    public static final String MOD_ID = "raft";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Raft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

       //  ModBlocks.register(modEventBus); // Регистрация блоков

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new FishingHandler());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(net.minecraft.commands.Commands.literal("start").executes(context -> {
            net.minecraft.commands.CommandSourceStack source = context.getSource();
            if (source.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
                net.minecraft.server.level.ServerLevel oceanWorld = source.getServer().getLevel(net.minecraft.server.level.ServerLevel.OVERWORLD);
                if (oceanWorld != null) {
                    net.minecraft.core.BlockPos spawnPos = WorldGenerationHandler.generateRaftForPlayer(oceanWorld, player);
                    player.teleportTo(oceanWorld, spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, player.getYRot(), player.getXRot());
                }
            }
            return 1;
        }));
    }
}
