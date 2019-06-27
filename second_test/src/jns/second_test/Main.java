package jns.second_test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	EventManager eventManager;
	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		getLogger().info("Joonas mod is enabled v1.1.1");
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("hello") && sender instanceof Player) {
			getLogger().info("Command worked, " + sender.getName() + ", with argument size: " + Integer.toString(args.length));
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("run")) {
					getLogger().info("Here you go: " + args[0]);
					eventManager.runnable();
				}
			} else {
				eventManager = new EventManager(this);
			}
		}
		
		
		return true;
	}
}