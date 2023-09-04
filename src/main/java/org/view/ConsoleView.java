package org.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public int receiveInput() {
        while(true) {
            displayMenu();
            String input = scanner.nextLine();

            if(validate(input)) {
                return Integer.parseInt(input);
            }
        }
    }

    private void displayMenu() {
        StringBuilder mainMenu = new StringBuilder();
        mainMenu.append("---------------------------------------------------------------\n")
                .append("            #여행 여정을 기록과 관리하는 SNS 서비스#          \n")
                .append("+-------------------------------------------------------------+\n")
                .append("|                         메뉴리스트                          |\n")
                .append("+-------------------------------------------------------------+\n")
                .append("| 여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5) |\n")
                .append("+-------------------------------------------------------------+\n")
                .append("| 시작하려면 메뉴 번호를 입력하세요: ");

        System.out.print(mainMenu);
    }

    private boolean validate(String input) {
        try {
            int tmpInput = Integer.parseInt(input);
            if (tmpInput < 1 || tmpInput > 5) {
                throw new InputMismatchException("\n [ERROR] 잘못된 메뉴 번호입니다. 1~5 사이의 번호를 입력하세요.\n");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("\n [ERROR] 잘못된 메뉴 번호입니다. 1~5 사이의 번호를 입력하세요.\n");
            return false;
        }
        return true;
    }
}