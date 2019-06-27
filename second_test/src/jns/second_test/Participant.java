package jns.second_test;

import org.bukkit.entity.Player;

public class Participant {
	
	Player player;
	String team;
	int score;
	
	Participant(Player play) {
		player = play;
		team = "survivor";
		score = 0;
	}
	
	Player getPlayer() {return player;}
	String getTeam() {return team;}
	int getScore() {return score;}
	
	boolean setTeam(String newTeam) {
		// Requires string that needs to be either "survivor" or "monster" and assigns that to the
		// participant
		switch(newTeam) {
		case "survivor":
			team = "survivor";
			return true;
		case "monster":
			team = "monster";
			return true;
		default:
			return false;
		}
	}
	
	String changeTeam() {
		// Changes the team of the participant to the opposite of what it currently is
		// Returns the new team assigned to the participant
		if (team == "survivor") {
			team = "monster";
		} else {
			team = "survior";
		}
		return team;
	}
	
	boolean addScore(int scoreToAdd) {
		score += scoreToAdd;
		return true;
	}
	
	boolean setScore(int newScore) {
		score = newScore;
		return true;
	}
}