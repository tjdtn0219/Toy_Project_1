package org.view;

import org.dto.ItinerariesDTO;
import org.dto.ItinerariesDTO;
import org.dto.TripDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public TripDTO writeTripInformation() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TripDTO tripDTO = new TripDTO();

        System.out.println("기록할 여행 정보를 입력하세요.");
              /* 여행 Id는 따로 생성해야 합니다. */
        System.out.print("여행 이름을 입력하세요: ");
        tripDTO.setTripName(scanner.nextLine());
        System.out.print("여행 시작날짜을 입력하세요: ");
        try {
            Date startDate = dateFormat.parse(scanner.nextLine());
            tripDTO.setStartDate(startDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.print("여행 종료날짜을 입력하세요: ");
        try {
            Date endDate = dateFormat.parse(scanner.nextLine());
            tripDTO.setEndDate(endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return tripDTO;
    }

    public ItinerariesDTO writeItinerariesInformation() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd / hh:mm:ss");
        ItinerariesDTO itinerariesDTO = new ItinerariesDTO();

        System.out.println("기록할 여정 정보를 입력하세요.");
              /* 여정 Id는 따로 생성해야 합니다. */
        System.out.print("여정 출발지를 입력하세요: ");
        itinerariesDTO.setDeparturePlace(scanner.nextLine());
        System.out.print("여정 도착지를 입력하세요: ");
        itinerariesDTO.setDestination(scanner.nextLine());
        System.out.print("여정 시작시간을 입력하세요: ");
        try {
            Date departureTime = dateFormat.parse(scanner.nextLine());
            itinerariesDTO.setDepartureTime(departureTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.print("여정 도착시간을 입력하세요: ");
        try {
            Date arrivalTime = dateFormat.parse(scanner.nextLine());
            itinerariesDTO.setArrivalTime(arrivalTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.print("여정 체크인 시간을 입력하세요: ");
        try {
            Date checkInTime = dateFormat.parse(scanner.nextLine());
            itinerariesDTO.setCheckInTime(checkInTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.print("여정 체크아웃 시간을 입력하세요: ");
        try {
            Date checkOutTime = dateFormat.parse(scanner.nextLine());
            itinerariesDTO.setCheckOutTime(checkOutTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return itinerariesDTO;
    }

    // 나중에 예외처리 필요!!
    public boolean checkWriteContinue() {
        String input = scanner.nextLine();
        return "Y".equals(input);
    }

    // 찾고 싶은 여정이 속해있는 여행의 Id를 이용해서 여행을 조회
    public String receiveFindTripId() {
        System.out.print("찾고 싶은 여정의 여행 Id를 입력하세요: ");
        return scanner.nextLine();
    }

    public void showTripInformation(TripDTO tripDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = String.format(
                "\n[여행기록정보 조회결과]\n" +
                "여행 ID: %s \n" +
                "여행 이름: %s\n" +
                "여행 출발날짜: %s\n" +
                "여행 도착날짜: %s\n",
                tripDTO.getTripId(),
                tripDTO.getTripName(),
                dateFormat.format(tripDTO.getStartDate()),
                dateFormat.format(tripDTO.getEndDate())
        );

        System.out.println(result);
    }

    public void showItinerariesInformation(ItinerariesDTO intItinerariesDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd / hh:mm:ss");
        String result = String.format(
                "\n[여정기록정보 조회결과]\n" +
                        "여정 ID: %s \n" +
                        "여정 출발지: %s\n" +
                        "여정 도착지: %s\n" +
                        "여정 출발시간: %s\n" +
                        "여정 도착시간: %s\n" +
                        "여정 체크인시간: %s\n" +
                        "여정 체크아웃시간: %s\n",
                intItinerariesDTO.getItineraryId(),
                intItinerariesDTO.getDeparturePlace(),
                intItinerariesDTO.getDestination(),
                dateFormat.format(intItinerariesDTO.getDepartureTime()),
                dateFormat.format(intItinerariesDTO.getArrivalTime()),
                dateFormat.format(intItinerariesDTO.getCheckInTime()),
                dateFormat.format(intItinerariesDTO.getCheckOutTime())
        );

        System.out.println(result);
    }

    public String chooseFileType() {
        System.out.print("파일 타입을 고르세요(JSON, CSV) : ");
        return scanner.nextLine();
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