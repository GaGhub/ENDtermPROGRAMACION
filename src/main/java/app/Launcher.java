package app;

import console.HelloConsole;
import gui.HelloApplication;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        HelloConsole console = new HelloConsole();
        console.start();
        //Application.launch(HelloApplication.class, args);
    }
}
