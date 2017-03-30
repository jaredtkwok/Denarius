/*  
 *******************************************************************************
 *  Denarius
 *  ConstantEffect.java
 *  Cards may give an effect that lasts for a period of time
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

public class ConstantEffect {

    private int duration, value;

    public ConstantEffect(int duration, int value) {
        this.duration = duration;
        this.value = value;
    }

    public void reduceDuration(int diceRoll) {
        duration -= diceRoll;
    }

    public void increaseDuration(int incDuration) {
        duration += incDuration;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getValue(){
        return value;
    }
}
