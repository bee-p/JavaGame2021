import java.io.*;
import java.util.Random;

public class Enemy {
	
	protected String name;	//�̸�
	protected int rank;	//����
	protected int heart; //����
	protected int attackPower; //���ݷ�
	protected int defensePower; //����
	protected int reputation; //�ִ� ���ǵ�
	protected int runAwayPercentage; //����ġ�� Ȯ��
	protected int dropPercentage; //������ ��� Ȯ��
	protected Item dropItem; //����ϴ� ������
	protected String nomalScript[]; //�⺻(������� ������ �ÿ� ����ϴ�)��ũ��Ʈ
	protected String knowELDScript[]; //(������� �˾��� �ÿ� ����ϴ�) ��ũ��Ʈ
	
	//��ũ��Ʈ
	/*
	 * ##��ü ��ũ��Ʈ�� String �迭�� ��
	 * 0��: ������ ��Ÿ���� ��ũ��Ʈ
	 * 1��: ���ݹ��� �� ��Ÿ���� ��ũ��Ʈ
	 * 2��: ���� �� �� ��Ÿ���� ��Ʈ��Ʈ
	 * 3��: ��� �� ��Ÿ���� ��Ʈ��Ʈ
	 * 4��: ����ĥ �� ��Ÿ���� ��Ʈ��Ʈ
	 */
	
	Enemy(String name, int rank, int heart, int attackPower, 
			int defensePower, int reputation, int runAwayPercentage, 
			int dropPercentage, Item dropItem, File Scripts){
		this.name = name;
		this.rank = rank;
		this.heart = heart;
		this.attackPower = attackPower;
		this.defensePower = defensePower;
		this.reputation = reputation;
		this.runAwayPercentage = runAwayPercentage;
		this.dropPercentage = dropPercentage;
		this.dropItem = dropItem;
		
		//scripts �ޱ�
	}
	
	//setter
	void setName(String name) {
		this.name = name;
	}
	void setRank(int rank) {
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
	int getRank() {
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
	void beAttacked(int damage) {
		damage = damage/100 * defensePower;
		heart -= damage;
	}
	//��ȣ�ۿ�
	Item interaction(int skillCode, int playerRank) {
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
