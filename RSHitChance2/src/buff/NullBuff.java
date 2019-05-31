package buff;

import java.util.List;

import calculations.Hit;
import combatent.Combatent;
import javafx.scene.image.Image;
import resources.ImageFlyweight;

//null buff returns default values for buffs and does not affect combat at all
public class NullBuff implements Buff, DontAddToBuffs{

    private static NullBuff instance;
    
    private NullBuff() {
    }

    public static NullBuff getInstance() {
        if(instance==null){
            instance = new NullBuff();
        }
        return instance;
    }
    
    //this shouldn't be called
    @Override
    public Image getImage() {
        return ImageFlyweight.getImage(Buff.PATH_TO_IMAGES+getName().toString(), Buff.IMAGE_WIDTH, Buff.IMAGE_HEIGHT, true, true);
    }

    @Override
    public BuffName getName() {
        return BuffName.Null_Buff;
    }

    @Override
    public String getNiceName() {
        return getName().toString().replace('_', ' ');
    }
    
    @Override
    public String getDescription() {
        return "does nothing";
    }
    
    @Override
    public String getToolTipString() {
        return "does nothing";
    }
    
    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return 0;
    }

    @Override
    public double addPowerLevelsToOwner(Combatent owner, Combatent opponent) {
        return 0;
    }

    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return 0;
    }

    @Override
    public double addAccuracyLevelsToOpponent(Combatent owner, Combatent opponent) {
        return 0;
    }

    @Override
    public double addPowerLevelsToOpponent(Combatent owner, Combatent opponent) {
        return 0;
    }

    @Override
    public double addDefenseLevelsToOpponent(Combatent owner, Combatent opponent) {
        return 0;
    }
    
    @Override
    public int getOwnerAffinityDebuff(Combatent owner, Combatent opponent){
        return 0;
    }

    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        return 1.0;
    }

    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        return 0.0;
    }

    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        return 1.0;
    }
    
    @Override
    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
        return 1.0;
    }
    
    @Override
    public double getAutoAttackDamageMultiplier(Combatent owner, Combatent opponent) {
        return 1.0;
    }

    @Override
    public double getArmorMultiplier(Combatent owner, Combatent opponent) {
        return 1.0;
    }

    @Override
    public double addVisibleAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return 0;
    }

    @Override
    public void affectOwnerBaseHitList(List<Hit> list, Combatent owner, Combatent opponent) {
    }
    
    @Override
    public void affectOpponentFinalHitList(List<Hit> list,Combatent owner, Combatent opponent) {
    }

    

    

    
    

}
