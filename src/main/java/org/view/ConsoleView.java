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
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd / hh:mm:ss");



    public ConsoleView() {
        sc = new Scanner(System.in);
    }

    public int receiveInput() {
        while(true) {
            displayMenu();
            String input = sc.nextLine();

            if(validate(input)) {
                return Integer.parseInt(input);
            }
        }
    }

    public TripDTO.Request writeTripInformation() throws ParseException {

        String tripName;
        Date startDate;
        Date endDate;

        System.out.println("기록할 여행 정보를 입력 하세요.");
        System.out.print("여행 이름을 입력 하세요: ");
        tripName = sc.nextLine();
        System.out.print("여행 시작 날짜을 입력 하세요: ");
        startDate = dateFormat.parse(sc.nextLine());
        System.out.print("여행 종료 날짜을 입력 하세요: ");
        endDate = dateFormat.parse(sc.nextLine());

        return TripDTO.Request.builder()
                .tripName(tripName)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public ItineraryDTO.Request writeItinerariesInformation() throws ParseException {

        int tripId;
        String departurePlace;
        String destination;

        Date departureTime;
        Date arrivalTime;
        Date checkInTime;
        Date checkOutTime;

        System.out.println("기록할 여정 정보를 입력 하세요.");

        System.out.print("찾고 싶은 여정의 여행 Id를 입력 하세요: ");
        tripId = Integer.parseInt(sc.nextLine());
        System.out.print("여정 출발지를 입력하세요: ");
        departurePlace = sc.nextLine();
        System.out.print("여정 도착지를 입력하세요: ");
        destination = sc.nextLine();
        System.out.print("여정 시작시간을 입력하세요: ");
        departureTime = timeFormat.parse(sc.nextLine());
        System.out.print("여정 도착시간을 입력하세요: ");
        arrivalTime = timeFormat.parse(sc.nextLine());
        System.out.print("여정 체크인 시간을 입력하세요: ");
        checkInTime = timeFormat.parse(sc.nextLine());
        System.out.print("여정 체크아웃 시간을 입력하세요: ");
        checkOutTime = timeFormat.parse(sc.nextLine());


        return ItineraryDTO.Request.builder()
                .tripId(tripId)
                .departurePlace(departurePlace)
                .destination(destination)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .build();
    }

    // 나중에 예외처리 필요!!
    public boolean checkWriteContinue() {
        String input = sc.nextLine();
        return "Y".equals(input);
    }

    // 찾고 싶은 여정이 속해 있는 여행의 Id를 이용해서 여행을 조회
    public int receiveFindTripId() {
        System.out.print("찾고 싶은 여정의 여행 Id를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int receiveAddedTripId() {
        /* 입력 받은 여정을 어느 여행에 저장 할건지 */
        System.out.print("찾고 싶은 여정의 여행 Id를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void showTripInformation(List<TripDTO.Response> tripDtoList) {
        tripDtoList.forEach(this::showTripTable);
    }

    private void showTripTable(TripDTO.Response response) {
        String result = String.format(
                        "\n[여행기록정보 조회결과]\n" +
                                "여행 ID: %s \n" +
                                "여행 이름: %s\n" +
                                "여행 출발날짜: %s\n" +
                                "여행 도착날짜: %s\n",
                response.getId(),
                response.getTripName(),
                dateFormat.format(response.getStartDate()),
                dateFormat.format(response.getEndDate())
        );
        System.out.println(result);
    }

    public void showItinerariesInformation(List<ItineraryDTO.Response> itineraryDtoList) {
        itineraryDtoList.forEach(this::showItineraryTable);

    }

    private void showItineraryTable(ItineraryDTO.Response response) {
        String result = String.format(
                "\n[여정기록정보 조회결과]\n" +
                        "여정 ID: %s \n" +
                        "여정 출발지: %s\n" +
                        "여정 도착지: %s\n" +
                        "여정 출발시간: %s\n" +
                        "여정 도착시간: %s\n" +
                        "여정 체크인시간: %s\n" +
                        "여정 체크아웃시간: %s\n",
                response.getId(),
                response.getDeparturePlace(),
                response.getDestination(),
                timeFormat.format(response.getDepartureTime()),
                timeFormat.format(response.getArrivalTime()),
                timeFormat.format(response.getCheckInTime()),
                timeFormat.format(response.getCheckOutTime())
        );

        System.out.println(result);
    }

    public String chooseFileType() {
        System.out.print("파일 타입을 고르세요(JSON, CSV) : ");
        return sc.nextLine();
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