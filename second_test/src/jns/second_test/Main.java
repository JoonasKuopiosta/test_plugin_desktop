package jns.second_test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	EventManager eventManager;
	boolean eventActive;
	boolean matchRunning;
	
	//TODO: Systeemi joka automaattisesti karsii poissaolevat pelaajat
	
	@Override
	public void onEnable() {
		// Informs the server console that the plugin is active
		// and sets gameRunning to false
		getLogger().info("Joonas plugin is enabled v1.3.0");
		matchRunning = false;
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public boolean getGameActivated() {return eventActive;}
	public boolean getGameRunning() {return matchRunning;}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player caster = (Player) sender;
		if (label.equalsIgnoreCase("hello") && caster instanceof Player) {
			getLogger().info("Command worked, " + caster.getName() + ", with argument size: " + Integer.toString(args.length));
			
			if(args.length >= 1) {
				String[] argus = toLenghtOf5(args);
				
				switch (argus[0]) {
				case "startup":
					if (!eventActive) {
						eventActive = true;
						eventManager = new EventManager(this);
						eventManager.startUp();
						caster.sendMessage(ChatColor.GREEN + "Aktivoit eventin! Muista lis�t� pelaajat!");
					} else {
						sender.sendMessage(ChatColor.RED + "Event on jo aktiivinen!");
					}
					break;
				case "player":
					if (eventActive) {
						if (!matchRunning) {
							//TODO: kutsu eventtiin
							eventManager.commandToPlayerManager(argus, caster);
						} else {
							caster.sendMessage(ChatColor.RED + "Kesken matsin et voi vaikuttaa pelaajiin!");
						}
					} else {
						caster.sendMessage(ChatColor.RED + "Muista aktivoida event /[anime] startup!"); //TODO: Korjaa viestit
					}
					break;
				case "start":
					if (eventActive) {
						//TODO: kutsu eventtiin
						caster.sendMessage(ChatColor.GREEN + "Er� on nyt k�ynniss�!");
					} else {
						caster.sendMessage(ChatColor.RED + "Muista aktivoida event jaada jaada!");
					}
					break;
				default:
					caster.sendMessage(ChatColor.LIGHT_PURPLE + "Sallitut komennot: startup, player, start");
					break;
				}
			}
		}
		return true;
	}
	
	// =========== PRIVATE METHODS START HERE ===========
	
	private String[] toLenghtOf5(String[] args) {
		String[] argus = new String[5];
		
		for (int i = 0; i < args.length; i++) {
			argus[i] = args[i];
		}
		return argus;
	}
}