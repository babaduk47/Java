package com.stasiuk.program;

import java.util.Scanner;

public class Main {

    public static final String RESET = "\033[0m";  // Text Reset

    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN

    public static void main(String[] args) {
        System.out.println("Выберете программу для запуска:");
        System.out.println(PURPLE_BOLD+"[1] "+ProgramOnPC.GoogleChrome+" (W)"+RESET);
        System.out.println(PURPLE_BOLD+"[2] "+ProgramOnPC.uTorrent+" (W)"+RESET);
        System.out.println(GREEN_BOLD+"[3] "+ProgramOnPC.VisualStudioCode+" (L)"+RESET);

        Scanner in = new Scanner(System.in);
        System.out.println("Выбор : ");
        int menu = in.nextInt();
        ProgramOnPC programOnPC;
        switch (menu) {
            case 1:
                programOnPC = ProgramOnPC.GoogleChrome;
                break;
            case 2:
                programOnPC = ProgramOnPC.uTorrent;
                break;
            case 3:
                programOnPC = ProgramOnPC.VisualStudioCode;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menu);
        }
        System.out.println("Параметр работы :");
        System.out.println(CYAN_BOLD+"[1] без выключения"+RESET);
        System.out.println(RED_BOLD+"[2] с выключение"+RESET);
        in = new Scanner(System.in);
        System.out.println("Выбор : ");
        menu = in.nextInt();
        FacadePC facadePC = new FacadePC();
        if(menu==1) {
            System.out.println(facadePC.StartProgram(programOnPC));
        }
        else if(menu==2){
            System.out.println(facadePC.StartProgramAndEnd(programOnPC));
        }
    }
}
