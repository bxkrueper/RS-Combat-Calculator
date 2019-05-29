package abilities;

import java.util.List;

import buff.Buff;
import calculations.Hit;
import combatStyle.CombatStyle;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.None;
import combatStyle.OffensiveCombatStyle;
import combatStyle.Ranged;
import combatent.Player;
import javafx.scene.image.Image;
import main.DamageMode;
import resources.ImageFlyweight;
import resources.Imageable;

public interface Ability extends Imageable{
	public static final String PATH_TO_IMAGES = "images/Abilities/";
    public static final int IMAGE_WIDTH = 30;
    public static final int IMAGE_HEIGHT = 30;
    
    String getName();
	AbilityCategory getCategory();
	AbilityType getType();
	AbilityRequirement getRequirement();
	int getCooldown();//in seconds
	int getLevel();
	int getNumberOfHits(DamageMode damageMode);
	double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode);
	List<Hit> generateBaseDamageHitList(double baseDamage, OffensiveCombatStyle combatStyle);
	Buff getBuff();
	
	
	@Override
    default Image getImage() {
		return ImageFlyweight.getImage(PATH_TO_IMAGES+getName(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
    }
	//uses None for defense and constitution
	default CombatStyle getCombatStyle() {
		AbilityCategory category = getCategory();
		switch(getCategory()) {
		case ATTACK:
		case STRENGTH:
			return Melee.getInstance();
		case RANGED:
			return Ranged.getInstance();
		case MAGIC:
			return Magic.getInstance();
		default:
			return None.getInstance();
		}
	}
	default boolean canBeUsedByPlayer(Player player) {
		return getRequirement().playerCanUseAbility(player, this);
	}
    
    
    
	
	
}
