package combatent;
/*
 * composite form of Attack. Acts like the attack of the current index. can be cycled through
 */
import java.util.ArrayList;
import java.util.List;

public class Attacks {
    
    private List<Attack> attackList;
    int index;
    
    public Attacks(Attack ... attacks){
        this.attackList = new ArrayList<>();
        for(Attack attack:attacks){
            attackList.add(attack);
        }
        index = 0;
    }
    public Attacks(List<Attack> attackList){
        this.attackList = attackList;
        index = 0;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void next(){
        index++;
        if(index>=attackList.size()){
            index = 0;
        }
    }
    public void previous(){
        index--;
        if(index<0){
            index = attackList.size()-1;
        }
    }
    public int numberOfAttacks(){
        return attackList.size();
    }
    
    public Attack getAttack(){
        return attackList.get(index);
    }
}
