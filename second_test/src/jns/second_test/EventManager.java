package jns.second_test;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class EventManager{

	Main main;
	
	public EventManager(Main reference) {
		main = reference;
	}
	
	public void test() {
		Bukkit.getLogger().info("Jeee jee");
	}
	
	public void runnable() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				test();
				
			}
		}.runTaskTimerAsynchronously(main, 0, 100);
	}
}
