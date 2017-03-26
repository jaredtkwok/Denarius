/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package denarius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jared
 */
public class BoardTile {
    // Mod 14 Tiles - repeated fortnightly

    public HashMap<Integer, ArrayList<String>> tile14 = new HashMap<>();
    // Mod 30 Tiles - repeated monthly
    public HashMap<Integer, ArrayList<String>> tile30 = new HashMap<>();

    public BoardTile() {
        int divider = 0;    // Decides which Hashmap to put tile in
        int key = 0;    // Remainder
        String[] splitText;     // Splits input from reader to be put into Hashmap
        String value;   //  Text in 3 sections that is split later
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/denarius/boardTileValues.txt"));
            String tileValue = "";
            while ((tileValue = br.readLine()) != null) {
                if (tileValue.contains("//")) {
                    if (tileValue.contains("Mod")) {
                        divider = Integer.parseInt(tileValue.split(" ")[2]);
                    }
                } else {
                    splitText = tileValue.split("-");
                    key = Integer.parseInt(splitText[0]);
                    value = splitText[1];
                    if (divider == 14) {

                        if (tile14.containsKey(key)) {
                            tile14.get(key).add(value);
                        } else {
                            tile14.put(key, new ArrayList<String>());
                            tile14.get(key).add(value);
                        }

                    } else {

                        if (tile30.containsKey(key)) {
                            tile30.get(key).add(value);
                        } else {
                            tile30.put(key, new ArrayList<String>());
                            tile30.get(key).add(value);
                        }
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Read line Error");
        }
    }
}
