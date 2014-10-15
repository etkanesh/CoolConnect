package com.example.dummywifi.Messenger.MessengerCommands;

import com.example.dummywifi.Messenger.ChatSession;
import com.example.dummywifi.models.Client;

// so we can add commands to the command executor function in the worker threads

public interface CommandExecutor {
	public boolean executeCommand(ChatSession session, Client caller, String command, String[] args);
}
