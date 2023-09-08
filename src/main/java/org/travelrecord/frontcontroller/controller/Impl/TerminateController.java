package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.frontcontroller.controller.Controller;

public class TerminateController implements Controller {

    @Override
    public void process() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }
}
