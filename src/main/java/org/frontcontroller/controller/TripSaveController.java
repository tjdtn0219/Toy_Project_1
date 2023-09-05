package org.frontcontroller.controller;

import org.dto.TripDTO;
import org.frontcontroller.Controller;
import org.view.ConsoleView;

import java.text.ParseException;

public class TripSaveController implements Controller {

    @Override
    public void process(ConsoleView consoleView) throws ParseException {
        TripDTO.Request tripDto = consoleView.writeTripInformation();
        System.out.println(tripDto.toString());     //TAG용으로 출력(지울 예정)
        //Model.저장(tripDto) 호출
    }
}
