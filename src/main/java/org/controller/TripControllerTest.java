package org.controller;

import org.dto.ItineraryDTO;
import org.dto.TripDTO;
import org.view.ConsoleView;

public class TripControllerTest {

    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();

        //2022-01-01
        //2022-01-01 / 21:20:00
        while(true) {
            int num = consoleView.receiveInput();
            if (num == 1) {
                TripDTO trip = consoleView.writeTripInformation();
                System.out.println(trip.toString());
                //view 호출
            } else if (num == 2) {
                //여정을 저장할 여행 id 입력
                ItineraryDTO itineraryDTO = consoleView.writeItinerariesInformation();
                System.out.println(itineraryDTO.toString());
                //view 호출
            } else if (num == 3) {
                //여행 조회
//            consoleView.showTripInformation();
            } else if (num == 4) {
                //여정 조회
            } else if(num==5) break;
        }
    }
}
