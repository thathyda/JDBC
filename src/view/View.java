package view;

import java.util.Scanner;

public class View {
    public static void ui(){
        String logo = """
                                
                   ______  _______  _____  _____  ______   \s
                 .' ___  ||_   __ \\|_   _||_   _||_   _ `. \s
                / .'   \\_|  | |__) | | |    | |    | | `. \\\s
                | |         |  __ /  | '    ' |    | |  | |\s
                \\ `.___.'\\ _| |  \\ \\_ \\ \\__/ /    _| |_.' /\s
                 `.____ .'|____| |___| `.__.'    |______.' \s
                                                           \s
                                
                """;
        System.out.println(logo);
        String menu = """
                1/ Display 
                2/ Add New 
                3/ Delete
                4/ Update
                5/ Search 
                6/ Exit : break 
                """;
        System.out.print(menu);
    }
    public static String option(){
        System.out.print(" ==>  Choose the option :");
        return new Scanner(System.in).nextLine();
    }
}
