package org.frontcontroller;

import org.constant.MenuOption;
import org.frontcontroller.controller.*;
import org.frontcontroller.controller.Impl.*;
import org.frontcontroller.controller.Impl.TripListController;
import org.view.SelectOptionView;

import java.util.HashMap;
import java.util.Map;

import static org.constant.MenuOption.*;

public class FrontController {

    private Map<MenuOption, Controller> controllerMap;

    public FrontController() {
        controllerMap = new HashMap<>();
        initControllerMap();
    }

    private void initControllerMap() {
        controllerMap.put(TRIP_SAVE, new TripSaveController());
        controllerMap.put(ITINERARY_SAVE, new ItinerarySaveController());
        controllerMap.put(TRIP_LIST, new TripListController());
        controllerMap.put(ITINERARY_LIST, new ItineraryListController());
        controllerMap.put(TERMINATE, new TerminateController());
    }

    public void service() throws Exception {
        SelectOptionView selectOptionView = new SelectOptionView();
        int input = selectOptionView.receiveInput();

        Controller controller = controllerMap.get(MenuOption.of(input));

        if(controller==null) {
            
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
