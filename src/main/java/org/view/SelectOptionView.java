package org.view;

import org.exception.ViewErrorCode;
import org.exception.ViewException;

import java.util.Scanner;

public class SelectOptionView {
    private final Scanner sc;

    public SelectOptionView() {
        this.sc = new Scanner(System.in);
    }

    public int receiveInput() {
        displayServiceName();
        while (true) {
            displayMenu();
            try {
                int input = getInput();
                return input;
            } catch (ViewException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getInput() throws RuntimeException {
        int input;

        try {
            input = Integer.parseInt(sc.nextLine());
        } catch (RuntimeException e) {
            throw new ViewException(ViewErrorCode.NO_MENU_OPTION);
        }

        if (input < 1 || input > 5) {
            throw new ViewException(ViewErrorCode.NO_MENU_OPTION);
        }
        return input;
    }

    private void displayServiceName() {
        StringBuilder mainMenu = new StringBuilder();
        mainMenu.append("---------------------------------------------------------------\n")
                .append("           # 여행 여정을 기록과 관리하는 SNS 서비스 #         \n")
                .append("---------------------------------------------------------------\n");

        System.out.print(mainMenu);
    }

    private void displayMenu() {
        StringBuilder mainMenu = new StringBuilder();
        mainMenu.append("---------------------------------------------------------------\n")
                .append("                          메뉴리스트                           \n")
                .append("---------------------------------------------------------------\n")
                .append("  여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5)  \n")
                .append("---------------------------------------------------------------\n")
                .append("  시작하려면 메뉴 번호를 입력하세요: ");

        System.out.print(mainMenu);
    }
}