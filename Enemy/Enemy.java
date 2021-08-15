import java.io.*;
import java.util.Random;

public class Enemy {
	
	String name;	//�̸�
	char rank;	//����
	int heart; //����
	int attackPower; //���ݷ�
	int defensePower; //����
	int reputation; //�ִ� ���ǵ�
	int runAwayPercentage; //����ġ�� Ȯ��
	int dropPercentage; //������ ��� Ȯ��
	Item dropItem; //����ϴ� ������
	String Script[]; //��Ʈ��Ʈ
	
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
		
		//script �ޱ�
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
		//script �ޱ�
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
	
	//���� �ޱ�
	boolean beAttacked(int damage) {
		damage = damage/100 * defensePower;
		heart -= damage;
		return heart <= 0;
	}
	//��ȣ�ۿ�
	boolean interaction(int skillCode, char playerRank) {
		if(playerRank >= rank) {
			if(skillCode == 1) {
				death();
			} else  if(skillCode == 2) {
				disappear();
			}
			return true;
		}
		else 
			return false;
	}
	
	Item death() {
		heart = 0;
		return (new Random().nextInt(100) < dropPercentage) ? dropItem : null;
	}
	
	Item disappear() {
		return (new Random().nextInt(100) < dropPercentage) ? dropItem : null;
	}
}
