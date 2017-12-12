//package sandbox;

import java.util.Scanner;

/**
 *
 * @author mitchmajure
 */
public class CaesarSalad {

    //the cipher that changes
    private static char[] mix = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
        ',', '.'};
    //the cipher that stays the same
    private static final char[] plainMix = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
        ',', '.'};
    //the message to be altered
    private final String message;
    
    public CaesarSalad(String str) {
        this.message = str;
    }
    
    public static int findInPlain(char q) {
        //finds the element of q in plain mix, allowing createNewSalad to 
        //to use the mix "shift" cipher.
        int place = 0;
        for (int i = 0; i < 28; i++) {
            if (q==plainMix[i]){
                place = i;
            }
        }
        return place;
    }
    
    public String createNewSalad() {
        /*The encoder itself. Every 3rd letter becomes the new beggining 
        shift for the super caesar*/
        StringBuffer tempSalad = new StringBuffer(message.toLowerCase());
        int i = 0;
        while (i < message.length()) {
            if(i%3 == 0){
                CaesarSalad.tossSalad(tempSalad.charAt(i));
            }
            tempSalad.setCharAt(i, mix[CaesarSalad.findInPlain(tempSalad.charAt(i))]);
            i++;
        }
        return tempSalad.toString();
    }

    public static void tossSalad(char q) {
        /*Makes mix begin with the char q, as long as it is contained in 
        plainMix, i.e. CaesarSalad.tossSalad(w) = {w,x,y,z .... ending with v}
        */
        int qPlace = 0;
        for (int i = 0; i < 28; i++) {
            if(q==mix[i]) {
                qPlace = i;
            }
        }
        int i = 0;
        while(i<29) {
            mix[i]=plainMix[(qPlace+i)%29];
            i++;
        }
    }
    public static void main(String[] args) {
//        Scanner kb = new Scanner(System.in);
//        String message = kb.next();
        CaesarSalad chicken = new CaesarSalad("gogan larland");
        System.out.print(chicken.createNewSalad());
    }
}
