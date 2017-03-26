/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package denarius;

/**
 *
 * @author Jared
 */
public class Dice {
    public int dice;
    
    public Dice(){  
    }
    
    public int roll(){
        dice = (int) ((Math.random()*100)%11)+2;
        return dice;
    }
    
    // Testing Dice
    public static void main(String[] args) {
        Dice die = new Dice();
        
        int dieValue = 0;
        while(dieValue != 12){
            dieValue = die.roll();
            System.out.println(dieValue);
        }
    }
}
