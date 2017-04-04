/*  
 *******************************************************************************
 *  Denarius
 *  ConstantCost.java
 *  Cards may give a cost/payment that lasts for a period of time
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

public class ConstantCost {
    
    private String constName;
    private int duration, value;
    
    public ConstantCost(){
        constName = "";
        duration = 0;
        value = 0;
    }
    
    public ConstantCost(String constName,int duration) {
        this.constName = constName;
        this.duration = duration;
        value = 0;
    }
    
    public ConstantCost(String constName,int duration, int value) {
        this.constName = constName;
        this.duration = duration;
        this.value = value;
    }

    public void reduceDuration(int diceRoll) {
        duration -= diceRoll;
    }

    public void incDuration(int incDuration) {
        duration += incDuration;
    }
    
    public void setDuration(int duration){
        this.duration = duration; 
    }
    
    public String getConstName(){
        return constName;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getValue(){
        return value;
    }
}
