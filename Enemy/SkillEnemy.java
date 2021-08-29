import java.io.*;

public class SkillEnemy extends Enemy{
	Skill skill;
	int useSkillRate;

	SkillEnemy(String name, char rank, int heart, int attackPower, int defensePower, int reputation,
			int runAwayPercentage, int dropPercentage, Item dropItem, File scriptFile, Skill skill, int useSkillRate) {
		super(name, rank, heart, attackPower, defensePower, reputation, runAwayPercentage, dropPercentage, dropItem,
				scriptFile);
		// TODO Auto-generated constructor stub
		this.skill = skill;
		this.useSkillRate = useSkillRate;
	}
	
	Skill useSkill() {
		if(skill instanceof HealSkill) {
			this.heart += ((HealSkill) skill).getHealling();
			return null;
		} else if(skill instanceof DebuffSkill) {
			return skill;
		} else if(skill instanceof DamageSkill) {
			return skill;
		} else {
			return null;
		}
	}
}
