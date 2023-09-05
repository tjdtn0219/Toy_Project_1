package org.frontcontroller.controller;

import org.frontcontroller.Controller;
import org.view.ConsoleView;

public class TerminateController implements Controller {

    @Override
    public void process() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }
}
