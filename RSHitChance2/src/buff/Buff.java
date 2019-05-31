package buff;
import java.util.List;

import calculations.Hit;
/*
 * defines all methods for a general buff. Most buffs should only return a non-default number for some of the methods
 */
import combatent.Combatent;
import resources.Imageable;

public interface Buff extends Imageable{
    public static final String PATH_TO_IMAGES = "images/Buffs/";
    public static final int IMAGE_WIDTH = 32;
    public static final int IMAGE_HEIGHT = 32;
    
    BuffName getName();
    String getNiceName();
    String getDescription();
    String getToolTipString();
    
    //potions, prayers, ect
    double addAccuracyLevelsToOwner(Combatent owner,Combatent opponent);
    double addPowerLevelsToOwner(Combatent owner,Combatent opponent);///////////don't need opponent?
    double addDefenseLevelsToOwner(Combatent owner,Combatent opponent);
    //opponent methods are expected to return a negative number   these are mainly for curse draining effects
    double addAccuracyLevelsToOpponent(Combatent owner,Combatent opponent);
    double addPowerLevelsToOpponent(Combatent owner,Combatent opponent);////////////delete?
    double addDefenseLevelsToOpponent(Combatent owner,Combatent opponent);
    
    int getOwnerAffinityDebuff(Combatent owner,Combatent opponent);//expect a positive number, do nothing is 0, guthix staff spec is 2
    double getAccuracyMultiplier(Combatent owner,Combatent opponent);// multiplies the owner's total accuracy. do nothing: return 1.0
    double getAddToFinalHitChance(Combatent owner,Combatent opponent);//+3% hit chance would return 3. Added at very end of hitchance calculation. ex: reaper necklace.  Do nothing value is 0
    double getDamageMultiplier(Combatent owner,Combatent opponent);//do nothing: return 1.0  used for abilities and auto attacks
    double getAutoAttackDamageMultiplier(Combatent owner,Combatent opponent);//do nothing: return 1.0  for player auto attacks. this stacks with DamageMultiplier, so adjust accordingly. Ex: if auto attacks are boosted by 30% and everything (abilities) are boosted by 10%, this should be 1.3/1.1
    double getDamageRecievedMultiplier(Combatent owner,Combatent opponent);//do nothing: return 1.0. Ex: Zerk auras use this
    double getArmorMultiplier(Combatent owner,Combatent opponent);//ex: blackstone arrows max: 15% reduction will return 0.85
    
    double addVisibleAccuracyLevelsToOwner(Combatent owner,Combatent opponent);//this is used to determine if a player can cast a spell at their magic level. Should usually be equal to addAccuracyLevelsToOwner except for prayers
    
    void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent);
    void affectOpponentFinalHitList(List<Hit> list,Combatent owner, Combatent opponent);
}
