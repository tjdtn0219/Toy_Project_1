package org.view;

import org.dto.ItineraryDTO;
import org.dto.TripDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
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

    public String chooseFileType() {
        System.out.print("파일 타입을 고르세요(JSON, CSV) : ");
        return sc.nextLine();
    }

}