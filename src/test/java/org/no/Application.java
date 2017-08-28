package org.no;

import org.no.pages.HomeScreen;
import org.no.pages.SettingsScreen;

/**
 * Class represents Application as container for Application pages
 * All its fields are initialized lazily and accessed directly
 */
public class Application {
    public static final HomeScreen homeScreen = new HomeScreen();
    public static final SettingsScreen settingsScreen = new SettingsScreen();
}
