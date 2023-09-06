package org.frontcontroller;

import org.frontcontroller.controller.*;
import org.view.ConsoleView;
import org.view.SelectOptionView;

import java.util.HashMap;
import java.util.Map;

public class FrontController {

    private Map<Integer, Controller> controllerMap;

    public FrontController() {
        controllerMap = new HashMap<>();
        initControllerMap();
    }

    private void initControllerMap() {
        controllerMap.put(1, new TripSaveController());
        controllerMap.put(2, new ItinerarySaveController());
        controllerMap.put(3, new TripListController());
        controllerMap.put(4, new ItineraryListController());
        controllerMap.put(5, new TerminateController());
    }

    public void service() throws Exception {
        SelectOptionView selectOptionView = new SelectOptionView();
        int input = selectOptionView.receiveInput();

        Controller controller = controllerMap.get(input);

        if(controller==null) {
            //예외 처리 예정
            return ;
        }

        controller.process();

    }

    public static void main(String[] args) throws Exception {
        FrontController frontController = new FrontController();
        while(true) {
            frontController.service();
        }


    }
}
