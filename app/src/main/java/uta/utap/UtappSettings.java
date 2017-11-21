package uta.utap;

import android.graphics.Color;

/**
 * Created by emiko on 11/20/2017.
 */

public class UtappSettings
{
    private ColorSettings m_ColorSettings;
    private LotSettings m_LotSettings;
    private NotificationSettings m_NotificationSettings;

    // Constructor
    public UtappSettings()
    {
        m_ColorSettings = new ColorSettings();
        m_LotSettings = new LotSettings();
        m_NotificationSettings = new NotificationSettings();
    }

    // Accessor methods
    public ColorSettings getColorSettings()
    {
        return m_ColorSettings;
    }

    public LotSettings getLotSettings()
    {
        return m_LotSettings;
    }

    public NotificationSettings getNotificationSettings()
    {
        return m_NotificationSettings;
    }

    public void setColorSettings(ColorSettings colorSettings)
    {
        m_ColorSettings = colorSettings;
    }

    public void setLotSettings(LotSettings lotSettings)
    {
        m_LotSettings = lotSettings;
    }

    public void setNotificationSettings(NotificationSettings notificationSettings)
    {
        m_NotificationSettings = notificationSettings;
    }

}
