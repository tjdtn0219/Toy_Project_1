package org.travelrecord.frontcontroller;

import org.travelrecord.constant.MenuOption;
import org.travelrecord.exception.ControllerException;
import org.travelrecord.frontcontroller.controller.*;
import org.travelrecord.frontcontroller.controller.Impl.*;
import org.travelrecord.frontcontroller.controller.Impl.TripListController;
import org.travelrecord.view.SelectOptionView;

import java.util.HashMap;
import java.util.Map;

import static org.travelrecord.constant.MenuOption.*;
import static org.travelrecord.exception.ControllerErrorCode.CONTROLLER_NOT_FOUND;

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
            throw new ControllerException(CONTROLLER_NOT_FOUND);
        }

        controller.process();

    }


}
