package org.travelrecord;


import org.travelrecord.frontcontroller.FrontController;

public class Application {
    public static void main(String[] args) throws Exception {
        FrontController frontController = new FrontController();
        while(true) {
            frontController.service();
        }
    }
}
