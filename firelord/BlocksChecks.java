/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firelord;

import java.util.ArrayList;

/**
 *
 * @author Fran
 */
public class BlocksChecks {
    private static ArrayList<Integer> halfBlocks = new ArrayList<Integer>() {{
             add(26);add(63);add(44);add(67);add(53);add(70);add(55);
             add(72);

        }};
    private static ArrayList<Integer> noFireStep = new ArrayList<Integer>() {{
             add(63);add(64);add(65);add(66);add(68);add(69);
             add(71);add(75);add(76);add(77);add(93);add(94);
        }};

    public static boolean isExcludedFromStep(int value) {
        return noFireStep.contains(value);
    }

    public static boolean isHalfBlock(int value) {
        return halfBlocks.contains(value);
    }

}
