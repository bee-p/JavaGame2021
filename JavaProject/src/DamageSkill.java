
public class DamageSkill extends Skill{
	int skillDamage;
	
	DamageSkill(String skillName, int skillDamage) {
		super(skillName);
		// TODO Auto-generated constructor stub
	}

	int useSkill() {
		return skillDamage;
	}

}
