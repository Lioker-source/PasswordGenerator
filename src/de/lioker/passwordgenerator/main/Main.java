package de.lioker.passwordgenerator.main;

import de.lioker.passwordgenerator.generator.Generator;
import de.lioker.passwordgenerator.gui.WindowManager;

public class Main {

    public static void main(String[] args) {
        WindowManager windowManager = new WindowManager();
        windowManager.createWindow();
    }
}
