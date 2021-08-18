
public class DebuffSkill extends Skill{
	int debuffRate;
	
	DebuffSkill(String skillName, int debuffRate) {
		super(skillName);
		// TODO Auto-generated constructor stub
	}

	int debuff(int playerDefense) {
		return playerDefense / 100 * (100 - debuffRate);
	}

}
