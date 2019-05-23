package buff;

import java.util.List;

//defines many default behaviors for buffs. Most buffs extend from this and only override one or two methods
import combatent.Combatent;
import javafx.scene.image.Image;
import main.Hit;
import resources.ImageFlyweight;

public class ConstantFillInBuff implements Buff{
    
    private BuffName name;
    private String pictureName;//may be null
    
    //these are initialized to their default (no effect) values in the constructor
    //to change, use a setter after object initialization
    private double addAccuracyLevelsToOwner;
    private double addPowerLevelsToOwner;
    private double addDefenceLevelsToOwner;
    private double addAccuracyLevelsToOpponent;
    private double addPowerLevelsToOpponent;
    private double addDefenceLevelsToOpponent;
    private int ownerAffinityDebuff;
    private double accuracyMultiplier;
    private double addToFinalHitChance;
    private double damageMultiplier;
    private double autoAttackDamageMultiplier;
    private double damageRecievedMultiplier;
    private double armorMultiplier;
    private double addVisibleAccuracyLevelsToOwner;
    
    public ConstantFillInBuff(BuffName name) {
        this.name = name;
        
        NullBuff nullBuff = NullBuff.getInstance();
        this.addAccuracyLevelsToOwner = nullBuff.addAccuracyLevelsToOwner(null,null);
        this.addPowerLevelsToOwner = nullBuff.addPowerLevelsToOwner(null,null);
        this.addDefenceLevelsToOwner = nullBuff.addDefenseLevelsToOwner(null,null);
        this.addAccuracyLevelsToOpponent = nullBuff.addAccuracyLevelsToOpponent(null,null);
        this.addPowerLevelsToOpponent = nullBuff.addPowerLevelsToOpponent(null,null);
        this.addDefenceLevelsToOpponent = nullBuff.addDefenseLevelsToOpponent(null,null);
        this.ownerAffinityDebuff = nullBuff.getOwnerAffinityDebuff(null,null);
        this.accuracyMultiplier = nullBuff.getAccuracyMultiplier(null,null);
        this.addToFinalHitChance = nullBuff.getAddToFinalHitChance(null,null);
        this.damageMultiplier = nullBuff.getDamageMultiplier(null,null);
        this.damageRecievedMultiplier = nullBuff.getDamageRecievedMultiplier(null,null);
        this.autoAttackDamageMultiplier = nullBuff.getAutoAttackDamageMultiplier(null,null);
        this.armorMultiplier = nullBuff.getArmorMultiplier(null,null);
        this.addVisibleAccuracyLevelsToOwner = nullBuff.addVisibleAccuracyLevelsToOwner(null, null);
    }

    @Override
    public BuffName getName() {
        return name;
    }
    
    @Override
    public String getNiceName() {
        return name.toString().replace('_', ' ');
    }

    //if picture name was set, uses that, otherwise default to the picture with the same name as name
    @Override
    public Image getImage() {
        if(pictureName==null){
            return ImageFlyweight.getImage(Buff.PATH_TO_IMAGES+name.toString(), Buff.IMAGE_WIDTH, Buff.IMAGE_HEIGHT, true, true);
        }else{
            return ImageFlyweight.getImage(Buff.PATH_TO_IMAGES+pictureName, Buff.IMAGE_WIDTH, Buff.IMAGE_HEIGHT, true, true);
        }
    }
    
    public void setPictureName(String newPictureName){
        this.pictureName = newPictureName;
    }
    
    //getters
    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return addAccuracyLevelsToOwner;
    }

    @Override
    public double addPowerLevelsToOwner(Combatent owner, Combatent opponent) {
        return addPowerLevelsToOwner;
    }

    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return addDefenceLevelsToOwner;
    }

    @Override
    public double addAccuracyLevelsToOpponent(Combatent owner, Combatent opponent) {
        return addAccuracyLevelsToOpponent;
    }

    @Override
    public double addPowerLevelsToOpponent(Combatent owner, Combatent opponent) {
        return addPowerLevelsToOpponent;
    }

    @Override
    public double addDefenseLevelsToOpponent(Combatent owner, Combatent opponent) {
        return addDefenceLevelsToOpponent;
    }
    
    @Override
    public int getOwnerAffinityDebuff(Combatent owner, Combatent opponent){
        return ownerAffinityDebuff;
    }

    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        return accuracyMultiplier;
    }

    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        return addToFinalHitChance;
    }

    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        return damageMultiplier;
    }
    
    @Override
    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
        return damageRecievedMultiplier;
    }
    
    @Override
    public double getAutoAttackDamageMultiplier(Combatent owner, Combatent opponent) {
        return autoAttackDamageMultiplier;
    }

    @Override
    public double getArmorMultiplier(Combatent owner, Combatent opponent) {
        return armorMultiplier;
    }
    
    @Override
    public double addVisibleAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return addVisibleAccuracyLevelsToOwner;
    }
    
    @Override
    public void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent) {
    }
    
    @Override
    public void affectOpponentFinalHitList(List<Hit> list,Combatent owner, Combatent opponent) {
    }

    //setters. uses these instead of overriding the getters if the buff returns a constant for that method
    public void setAddAccuracyLevelsToOwner(double addAccuracyLevelsToOwner) {
        this.addAccuracyLevelsToOwner = addAccuracyLevelsToOwner;
    }

    public void setAddPowerLevelsToOwner(double addPowerLevelsToOwner) {
        this.addPowerLevelsToOwner = addPowerLevelsToOwner;
    }

    public void setAddDefenceLevelsToOwner(double addDefenceLevelsToOwner) {
        this.addDefenceLevelsToOwner = addDefenceLevelsToOwner;
    }

    public void setAddAccuracyLevelsToOpponent(double addAccuracyLevelsToOpponent) {
        this.addAccuracyLevelsToOpponent = addAccuracyLevelsToOpponent;
    }

    public void setAddPowerLevelsToOpponent(double addPowerLevelsToOpponent) {
        this.addPowerLevelsToOpponent = addPowerLevelsToOpponent;
    }

    public void setAddDefenceLevelsToOpponent(double addDefenceLevelsToOpponent) {
        this.addDefenceLevelsToOpponent = addDefenceLevelsToOpponent;
    }

    public void setOwnerAffinityDebuff(int getOwnerAffinityDebuff) {
        this.ownerAffinityDebuff = getOwnerAffinityDebuff;
    }

    public void setAccuracyMultiplier(double getAccuracyMultiplier) {
        this.accuracyMultiplier = getAccuracyMultiplier;
    }

    public void setAddToFinalHitChance(double getAddToFinalHitChance) {
        this.addToFinalHitChance = getAddToFinalHitChance;
    }

    public void setDamageMultiplier(double getDamageMultiplier) {
        this.damageMultiplier = getDamageMultiplier;
    }
    
    public void setDamageRecievedMultiplier(double getDamageRecievedMultiplier) {
        this.damageRecievedMultiplier = getDamageRecievedMultiplier;
    }
    
    public void setAutoAttackDamageMultiplier(double autoAttackDamageMultiplier) {
        this.autoAttackDamageMultiplier = autoAttackDamageMultiplier;
    }

    public void setArmorMultiplier(double getArmorMultiplier) {
        this.armorMultiplier = getArmorMultiplier;
    }
    
    public void setAddVisibleAccuracyLevelsToOwner(int addVisibleAccuracyLevelsToOwner){
        this.addVisibleAccuracyLevelsToOwner = addVisibleAccuracyLevelsToOwner;
    }

    
    
    @Override
    public String toString() {
        return name.toString();
    }

    

    

    

    
    
}
