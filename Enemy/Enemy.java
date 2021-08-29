import java.io.*;
import java.util.Random;

public class Enemy {
	
	protected String name;	//이름
	protected char rank;	//직급
	protected int heart; //생명
	protected int attackPower; //공격력
	protected int defensePower; //방어력
	protected int reputation; //주는 평판도
	protected int runAwayPercentage; //도망치기 확률
	protected int dropPercentage; //아이템 드랍 확률
	protected Item dropItem; //드랍하는 아이템
	protected String Script[]; //스트립트
	
	Enemy(String name, char rank, int heart, int attackPower, 
			int defensePower, int reputation, int runAwayPercentage, 
			int dropPercentage, Item dropItem, File scriptFile){
		this.name = name;
		this.rank = rank;
		this.heart = heart;
		this.attackPower = attackPower;
		this.defensePower = defensePower;
		this.reputation = reputation;
		this.runAwayPercentage = runAwayPercentage;
		this.dropPercentage = dropPercentage;
		this.dropItem = dropItem;
		
		//script 받기
	}
	
	//setter
	void setName(String name) {
		this.name = name;
	}
	void setRank(char rank) {
		this.rank = rank;
	}
	void setHeart(int heart) {
		this.heart = heart;
	}
	void setAttackPower(int attack) {
		this.attackPower = attack;
	}
	void setDefensePower(int defense) {
		this.defensePower = defense;
	}
	void setReputation(int reputation) {
		this.reputation = reputation;
	}
	void setRunAwayPercentage(int runAway) {
		this.runAwayPercentage = runAway;
	}
	void setDropPercentage(int drop) {
		this.dropPercentage = drop;
	}
	void setItem(Item item) {
		this.dropItem = item;
	}
	void setScript(File scriptFile) {
		//script 받기
	}
	
	//getter
	String getName() {
		return name;
	}
	char getRank() {
		return rank;
	}
	int getHeart() {
		return heart;
	}
	int getAttackPower() {
		return attackPower;
	}
	int getDefensePower() {
		return defensePower;
	}
	int getReputation() {
		return reputation;
	}
	int getRunAwayPercentage() {
		return runAwayPercentage;
	}
	int getDropPercentage() {
		return dropPercentage;
	}
	
	//공격 받기
	void beAttacked(int damage) {
		damage = damage/100 * defensePower;
		heart -= damage;
	}
	//상호작용
	Item interaction(int skillCode, char playerRank) {
		if(playerRank >= rank) {
			if(skillCode == 1) {
				return death();
			} else {
				return disappear();
			}
		}
		else
			return null;
	}
	
	Item death() {
		heart = 0;
		return (new Random().nextInt(100) < dropPercentage) ? null:dropItem;
	}
	
	Item disappear() {
		return (new Random().nextInt(100) < dropPercentage) ? null:dropItem;
	}
}
