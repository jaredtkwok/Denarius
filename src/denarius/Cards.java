/*  
 *******************************************************************************
 *  Denarius
 *  Cards.java
 *  After landing on a specific tile the player draws a card
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cards {

    private String[] cardText;

    public Cards() {
        int k = 0;             // Card Number
        String text;           // Value/Text of Card
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/denarius/gameValues/cards.txt"));
            while ((text = br.readLine()) != null) {
                if (text.contains("//")) {
                    if (text.contains("Number of Cards")) {
                        String[] splitText = text.split(" ");
                        cardText = new String[Integer.parseInt(splitText[4])];
                    }
                } else {
                    cardText[k] = text;
                    k++;
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Read line Error");
        }
    }
    
    public void shuffleCards(){
        try {
            for (int i = cardText.length; i >= 0; i--) {
                int selectIndex = (int) ((Math.random()*100));
                String temp = cardText[i];
                cardText[i] = cardText[selectIndex];
                cardText[selectIndex] = temp;
            }
        } catch (Exception e) {
            System.err.println("Shuffle Error");
        }
    }
    
}
