package org.frontcontroller.controller;

import org.dto.TripDTO;
import org.frontcontroller.Controller;
import org.view.ConsoleView;

public class TripSaveController implements Controller {

    @Override
    public void process(ConsoleView consoleView) {
        TripDTO tripDTO = consoleView.writeTripInformation();
        System.out.println(tripDTO.toString());     //TAG용으로 출력(지울 예정)
        //Model.저장(tripDto) 호출
    }
}
