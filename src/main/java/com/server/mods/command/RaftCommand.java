package com.server.mods.command;

import com.server.mods.containers.RaftMenu;
import com.server.mods.world.WorldGenerationHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.event.RegisterCommandsEvent;

public class RaftCommand implements ICommand {

    public void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("raft").executes(context -> {
            CommandSourceStack source = context.getSource();
            if (source.getEntity() instanceof ServerPlayer player) {
                player.openMenu(new SimpleMenuProvider(
                        (id, inventory, playerEntity) -> new RaftMenu(id, inventory),
                        Component.literal("Raft GUI")
                ));
            }
            return 1;
        }));

        event.getDispatcher().register(Commands.literal("rf").executes(context -> {
            CommandSourceStack source = context.getSource();
            if (source.getEntity() instanceof ServerPlayer player) {
                player.openMenu(new SimpleMenuProvider(
                        (id, inventory, playerEntity) -> new RaftMenu(id, inventory),
                        Component.literal("Raft GUI")
                ));
            }
            return 1;
        }));
    }
}
