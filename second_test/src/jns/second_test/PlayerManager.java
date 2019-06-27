package jns.second_test;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager{
	
	ArrayList<Participant> participantList = new ArrayList<Participant>();
	// A list that contains all current participants
	
	protected Player getPlayerByName(String playerName) {
		// Finds player from the SERVER by the given player name
		for (Player other : Bukkit.getOnlinePlayers()) {
			if (other.getName().equalsIgnoreCase(playerName)) {
				return other;
			}
		}
		return null;
	}
	
	public void addNearbyPlayersToList(Player caster, double maxDist) {
		// Adds all player who are around the caster below or equal to max distance
		// Ignores the caster
		for (Player other : Bukkit.getOnlinePlayers()) {
			if(other != caster) {
				if(other.getLocation().distance(caster.getLocation()) <= maxDist) {
					addPlayerToParticipantList(other);
				}
			}
		}
	}
	
	public Participant getParticipant(Player player) {
		// Returns participant to given player, returns NULL if no match can be found from
		// the participant list
		for (Participant part : participantList) {
			if (part.getPlayer() == player) {
				return part;
			}
		}
		return null;
	}
	
	public boolean addParticipant(Player player) {
		// Calls method that adds player into participant list
		addPlayerToParticipantList(player);
		return true;
	}
	
	public boolean removeParticipant(Player player) {
		// Calls method that tries to remove player from participant list
		// if false is returned player can't be found from the participant list
		return removePlayerFromParticipantList(player);
	}
	
	public void clearAllParticipants() {
		// Clears the list of players
		participantList.clear();
	}
	
	protected boolean removePlayerFromParticipantList(Player player) {
		// Goes through the list of participants, and if NO participant matching the player
		// can be found returns false
		if (player != null) {
			for (Participant part : participantList) {
				if (part.getPlayer() == player) {
					participantList.remove(part);
					return true;
				}
			}
		}
		return false;
	}
	
	protected void addPlayerToParticipantList(Player player) {
		// Creates new Participant and give player as parameter, which is then added to the participant list
		Participant participant = new Participant(player);
		participantList.add(participant);
	}
}
