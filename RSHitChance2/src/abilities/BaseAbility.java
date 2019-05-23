package abilities;

import buff.Buff;

public abstract class BaseAbility implements Ability{
	
	private String name;
	private AbilityCategory category;
	private int level;
	private AbilityType type;
	private AbilityRequirement requirement;
	private int cooldown;
	private Buff buff;

	public BaseAbility(String name,AbilityCategory category, int level, AbilityType type, AbilityRequirement requirement,int cooldown,Buff buff) {
		this.name = name;
		this.category = category;
		this.level = level;
		this.type = type;
		this.requirement = requirement;
		this.cooldown = cooldown;
		this.buff = buff;
	}
	
	
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public AbilityCategory getCategory() {
		return category;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public AbilityType getType() {
		return type;
	}

	@Override
	public AbilityRequirement getRequirement() {
		return requirement;
	}

	@Override
	public int getCooldown() {
		return cooldown;
	}
	
	@Override
	public Buff getBuff() {
	    return buff;
	}

}
