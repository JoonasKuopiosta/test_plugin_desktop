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