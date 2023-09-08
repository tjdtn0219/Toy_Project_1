package org.view;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner sc;

    public ConsoleView() {
        sc = new Scanner(System.in);
    }


    // 나중에 예외처리 필요!!
    public boolean checkWriteContinue() {
        String input = sc.nextLine();
        return "Y".equals(input);
    }
}