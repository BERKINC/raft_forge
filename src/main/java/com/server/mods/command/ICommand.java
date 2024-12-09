package com.server.mods.command;

import net.minecraftforge.event.RegisterCommandsEvent;

public interface ICommand {
    void register(RegisterCommandsEvent event);
}
