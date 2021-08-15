import java.io.*;

public class RageEnemy extends Enemy{
	int buffRate;
	int debuffRate;
	int hitRate;

	RageEnemy(String name, char rank, int heart, int attackPower, int defensePower, int reputation,
			int runAwayPercentage, int dropPercentage, Item dropItem, File scriptFile, int buffRate, int debuffRate, int hitRate) {
		super(name, rank, heart, attackPower, defensePower, reputation, runAwayPercentage, dropPercentage, dropItem,
				scriptFile);
		// TODO Auto-generated constructor stub
		
		this.buffRate = buffRate;
		this.debuffRate = debuffRate;
		this.hitRate = hitRate;
	}
	
	void rage() {
		attackPower = attackPower / 100 * (100-buffRate);
		defensePower = defensePower / 100 * (100 - debuffRate);
	}
}
