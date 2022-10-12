package com.aor.hero;


import com.aor.hero.controller.Controller;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) {

        try {
            Controller controller = new Controller();
            controller.run() ;
        }catch (IOException | URISyntaxException | FontFormatException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}