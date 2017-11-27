package uta.utap;

/**
 * Created by emiko on 11/26/2017.
 */

public class SettingsController
{
    private static final SettingsController settingsController = new SettingsController();

    private SettingsController(){}

    public static SettingsController getInstance()
    {
        return settingsController;
    }

    public UtappSettings getSettings()
    {
        return AccountController.getInstance().getUser().getUserSettings();
    }
}
