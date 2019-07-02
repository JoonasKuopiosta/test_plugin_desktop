package jns.second_test;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PlayerManager{
	
	float maxDistForNear;
	
	ArrayList<Participant> participantList = new ArrayList<Participant>();
	// A list that contains all current participants
	
	public PlayerManager() {
		maxDistForNear = 10.0f;
	}
	
	public void commandParser(String[] argus, Player caster) {
		switch (argus[1]) {
		case "add":
			if (argus[2] != null) {
				boolean result = addParticipant(getPlayerByName(argus[2]));
				if (result) {
					caster.sendMessage(ChatColor.GREEN + "Pelaaja " + argus[2] + " lisätty!");
				} else {
					caster.sendMessage(ChatColor.RED + "Pelaajaa nimellä " + argus[2] + " ei löydy!");
				}
			}
			break;
		case "remove":
			boolean result = removeParticipant(getPlayerByName(argus[2]));
			if (result) {
				caster.sendMessage(ChatColor.GREEN + "Pelaaja " + argus[2] + " poistettu!");
			} else {
				caster.sendMessage(ChatColor.RED + "Pelaajaa nimellä " + argus[2] + " ei löydy!");
			}
			break;
		case "addnear": // Big N changed to n
			int num1 = addNearbyPlayersToList(caster, maxDistForNear);
			caster.sendMessage(ChatColor.GREEN + "Lisäsit " + num1 + " pelaajaa!");
			break;
		case "clear":
			int num2 = clearAllParticipants();
			caster.sendMessage(ChatColor.GREEN + "Kaikki " + num2 + " pelaajaa poistettu!");
			break;
		default:
			caster.sendMessage(ChatColor.LIGHT_PURPLE + "Sallitut player-komennot: add, remove, addnear, clear");
			break;
		}
	}
	
	
	// =========== PUBLIC METHODS START HERE ===========
	
	public int addNearbyPlayersToList(Player caster, double maxDist) {
		// Adds all player who are around the caster below or equal to max distance
		// Ignores the caster
		int playersAdded = 0;
		
		for (Player other : Bukkit.getOnlinePlayers()) {
			if(other != caster) {
				if(other.getLocation().distance(caster.getLocation()) <= maxDist) {
					addPlayerToParticipantList(other);
					playersAdded++;
				}
			}
		}
		return playersAdded;
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
		if (player != null) {
			addPlayerToParticipantList(player);
			return true;
		}
		return false;
	}
	
	public boolean removeParticipant(Player player) {
		// Calls method that tries to remove player from participant list
		// if false is returned player can't be found from the participant list
		return removePlayerFromParticipantList(player);
	}
	
	public int clearAllParticipants() {
		// Clears the list of players
		int amountRemoved = participantList.size();
		participantList.clear();
		return amountRemoved;
	}
	
	// =========== PRIVATE METHODS START HERE ===========
	
	private Player getPlayerByName(String playerName) {
		// Finds player from the SERVER by the given player name
		for (Player other : Bukkit.getOnlinePlayers()) {
			if (other.getName().equalsIgnoreCase(playerName)) {
				return other;
			}
		}
		return null;
	}
	
	private boolean removePlayerFromParticipantList(Player player) {
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
	
	private void addPlayerToParticipantList(Player player) {
		// Creates new Participant and give player as parameter, which is then added to the participant list
		Participant participant = new Participant(player);
		participantList.add(participant);
	}
}
