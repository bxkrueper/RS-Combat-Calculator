package buff;

/*
 * composite form of Buff
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import calculations.Hit;
import combatent.Combatent;
import javafx.scene.image.Image;
import resources.ImageFlyweight;

public class Buffs implements Buff, Iterable<Buff>{
    
    private List<Buff> buffList;
    
    public Buffs() {
        this.buffList = new ArrayList<>();;
    }

    //does not add buffs that implement DontAddToBuffs
    public void addBuff(Buff buff){
        if(!(buff instanceof DontAddToBuffs) && buff!=null){
            buffList.add(buff);
        }
    }
    
    //removes the buff with the given buffName. Does nothing if the buffName was never there
    public void removeBuff(BuffName buffName){
        for(int i=0;i<buffList.size();i++){
            if(buffList.get(i).getName().equals(buffName)){
                buffList.remove(i);
                break;
            }
        }
    }
    
    //removes a buffs by its reference id
    public void removeBuffs(Buffs buffs) {
        buffList.remove(buffs);
    }
    
    public int getListSize(){
        return buffList.size();
    }
    
    public Buff getBuff(int i) {
        return buffList.get(i);
    }

    ///this should be no need to call this
    @Override
    public BuffName getName() {
        return BuffName.Buff_Group;
    }
    
    ///this should be no need to call this
    @Override
    public String getNiceName() {
        return "Buff Group";
    }
    
    ///this should be no need to call this
    @Override
    public String getDescription() {
        return "this is a Buff Group";
    }
    
  ///this should be no need to call this
    @Override
    public String getToolTipString() {
        return "this is a Buff Group";
    }

    ///this shouldn't be used
    @Override
    public Image getImage() {
        return ImageFlyweight.getImage(Buff.PATH_TO_IMAGES+getName().toString(), Buff.IMAGE_WIDTH, Buff.IMAGE_HEIGHT, true, true);
    }

    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
//            System.out.println("Buffs: i: " + i + " " + buffList.get(i));
            total+=buffList.get(i).addAccuracyLevelsToOwner(owner, opponent);
        }
        return total;
    }

    @Override
    public double addPowerLevelsToOwner(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).addPowerLevelsToOwner(owner, opponent);
        }
        return total;
    }

    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).addDefenseLevelsToOwner(owner, opponent);
        }
        return total;
    }

    @Override
    public double addAccuracyLevelsToOpponent(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).addAccuracyLevelsToOpponent(owner, opponent);
        }
        return total;
    }

    @Override
    public double addPowerLevelsToOpponent(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).addPowerLevelsToOpponent(owner, opponent);
        }
        return total;
    }

    @Override
    public double addDefenseLevelsToOpponent(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).addDefenseLevelsToOpponent(owner, opponent);
        }
        return total;
    }

    @Override
    public int getOwnerAffinityDebuff(Combatent owner, Combatent opponent) {
        int total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).getOwnerAffinityDebuff(owner, opponent);
        }
        return total;
    }
    
    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        double product = 1.0;
        for(int i=0;i<buffList.size();i++){
            product*=buffList.get(i).getAccuracyMultiplier(owner, opponent);
        }
        return product;
    }

    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).getAddToFinalHitChance(owner, opponent);
        }
        return total;
    }

    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        double product = 1.0;
        for(int i=0;i<buffList.size();i++){
            product*=buffList.get(i).getDamageMultiplier(owner, opponent);
        }
        return product;
    }
    
    @Override
    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
        double product = 1.0;
        for(int i=0;i<buffList.size();i++){
            product*=buffList.get(i).getDamageRecievedMultiplier(owner, opponent);
        }
        return product;
    }
    
    @Override
    public double getAutoAttackDamageMultiplier(Combatent owner, Combatent opponent) {
        double product = 1.0;
        for(int i=0;i<buffList.size();i++){
            product*=buffList.get(i).getAutoAttackDamageMultiplier(owner, opponent);
        }
        return product;
    }

    @Override
    public double getArmorMultiplier(Combatent owner, Combatent opponent) {
        double product = 1.0;
        for(int i=0;i<buffList.size();i++){
            product*=buffList.get(i).getArmorMultiplier(owner, opponent);
        }
        return product;
    }
    
    @Override
    public double addVisibleAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        double total = 0;
        for(int i=0;i<buffList.size();i++){
            total+=buffList.get(i).addVisibleAccuracyLevelsToOwner(owner, opponent);
        }
        return total;
    }
    
    @Override
    public void affectOwnerBaseHitList(List<Hit> list, Combatent owner, Combatent opponent) {
        for(int i=0;i<buffList.size();i++){
            buffList.get(i).affectOwnerBaseHitList(list,owner, opponent);
        }
    }
    
    @Override
    public void affectOpponentFinalHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        for(int i=0;i<buffList.size();i++){
            buffList.get(i).affectOpponentFinalHitList(list,owner, opponent);
        }
    }

    public boolean contains(BuffName buffName) {
        for(Buff currentBuff:this){
            if(currentBuff.getName().equals(buffName)){
                return true;
            }
        }
        return false;
    }
    
    //returns null if not found
    public Buff getSpecificBuff(BuffName buffName) {
        for(Buff currentBuff:this){
            if(currentBuff.getName().equals(buffName)){
                return currentBuff;
            }
        }
        return null;
    }
    

    @Override
    public String toString(){
        return "buffs: " + buffList.toString();
    }

    @Override
    public Iterator<Buff> iterator() {
        return new BuffsIterator();
    }
    
    //returns only actual Buffs. Goes into other BuffGroups instead of returning them
    private class BuffsIterator implements Iterator<Buff>{
        
        private Buff nextUp;
        private int currentIndex;
        private Iterator<Buff> currentIterator;//is null if there is no sub-iterator wording through a nested BuffGroup

        public BuffsIterator(){
            this.currentIndex = -1;
            this.currentIterator = null;
            this.nextUp = getNextUp();
        }
        
        //prepares the next non-group Buff to return
        private Buff getNextUp() {
            if(currentIterator!=null){//if it is working through a nested-group
                if(currentIterator.hasNext()){
                    return currentIterator.next();
                }else{
                    //current iterator has run out
                    currentIterator = null;
                }
            }
            
            currentIndex++;//move to next index
            if(currentIndex>=buffList.size()){
                return null;//out of buffs
            }
            
            Buff currentBuff = buffList.get(currentIndex);
            
            if(currentBuff instanceof Buffs){
                //use that buff's iterator until it runs out
                currentIterator = ((Buffs) currentBuff).iterator();
                if(currentIterator.hasNext()){
                    return currentIterator.next();
                }else{
                    //buff group was empty from the start
                    currentIterator = null;
                    return getNextUp();//still need to return a buff. Move on recursively
                }
            }else{
                return currentBuff;//simply return the buff at the currentIndex
            }
        }

        @Override
        public boolean hasNext() {
            return nextUp!=null;
        }

        @Override
        public Buff next() {
            Buff toReturn = nextUp;
            nextUp = getNextUp();//prepares the next buff to return before returning the one that was ready
            return toReturn;
        }
        
    }

    

    

    

    

    

    

    

    

    
    
}
