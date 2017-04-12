/*  
 *******************************************************************************
 *  Denarius
 *  Assets.java
 *  Assets which players can obtain during the game
 *  NOT to be confused with image/animation assets
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Assets {

    // Assets gain while playing
    private HashMap<String, String> assets = new HashMap<>();

    public Assets(String file) {
        String[] splitText;     // Splits input from reader to be put into Hashmap
        String key;             // Name of asset
        String value;           // Value of assest and FSV
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String assetValue;
            while ((assetValue = br.readLine()) != null) {
                if (assetValue.contains("//")) {
                    if (assetValue.contains("Number of Assets")) {
                        splitText = assetValue.split(" ");
                    }
                } else {
                    splitText = assetValue.split("-");
                    key = splitText[0];
                    value = splitText[1];
                    assets.put(key, value);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found - Assets");
        } catch (IOException ex) {
            System.out.println("Read line Error - Assets");
        }
    }

    public HashMap<String, String> getAssets() {
        return assets;
    }
    
    
}
