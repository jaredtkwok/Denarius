/*  
 *******************************************************************************
 *  Denarius
 *  Insurance.java
 *  Different types of insurances in the game
 *  Linked to Player
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

public class Insurance {

    private String[] insuranceTypes = {"Life", "Medical", "House/Content", "Car"};
    private boolean owned;
    private int insurNum;

    public Insurance(int insurNum) throws Exception {
        if (insurNum < 0 || insurNum > 4) {
            throw new Exception("Insurance type not found");
        }
        this.insurNum = insurNum;
        owned = false;
    }

    public void setInsurance(boolean owned) {
        this.owned = owned;
    }

    public String getInsurance() {
        return insuranceTypes[insurNum];
    }

    public String getOwned() {
        if (owned) {
            return "Yes";
        } else {
            return "No";
        }
    }
}
