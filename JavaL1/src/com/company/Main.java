package com.company;

import java.lang.reflect.Array;

/**
 * this class demonstrate first java application
 * @author  Stasiuk Vladislav
 * @version 1.0
 * @since  2020-02-09
 */
public class Main {
    /**
     * This method shows main information about me.
     * @param args unsued.
     */
    public static void main(String[] args) {
        System.out.println("Group  : 535st1");
        System.out.println("Student  : Stasiuk Vladislav Olegovych");
        System.out.println("Variant  : 18");
        String[] ar = {
                "**               **       *********         ************   \n",
                " **             **      **         **      **           ** \n",
                "  **           **       **                 **           ** \n",
                "   **         **          **               **           ** \n",
                "    **       **             **             **           ** \n",
                "     **     **                 **          **           ** \n",
                "      **   **                     **       **           ** \n",
                "       ** **                        **     **           ** \n",
                "        ***              **         **     **           ** \n",
                "         *                **********        *************  \n"
        };

        for (String s :ar) {
            System.out.print(s);
        }

    }
}
