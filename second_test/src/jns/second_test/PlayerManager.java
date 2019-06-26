package jns.second_test;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerManager extends JavaPlugin {
	
	ArrayList<Participant> participantList = new ArrayList<Participant>();
	
	public void setNearbyPlayersToList(Player caster, double maxDist) {
		participantList.clear();
		for (Player other : Bukkit.getOnlinePlayers()) {
			if(other != caster) {
				if(other.getLocation().distance(caster.getLocation()) <= maxDist) {
					Participant participant = new Participant(other);
					participantList.add(participant);
				}
			}
		}
	}
	
	public Participant getParticipant(Player player) {
		for (Participant part : participantList) {
			if (part.getPlayer() == player) {
				return part;
			}
		}
		return null;
	}
}
