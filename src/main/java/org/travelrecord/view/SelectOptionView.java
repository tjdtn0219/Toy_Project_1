package org.travelrecord.view;

import org.travelrecord.exception.ViewErrorCode;
import org.travelrecord.exception.ViewException;

import java.util.Scanner;

import static org.travelrecord.constant.ViewMessage.*;

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
        System.out.print(SERVICE_NAME);
    }

    private void displayMenu() {
        System.out.print(MAIN_MENU);
    }
}