package cn.settile.sblog.service;

import org.springframework.stereotype.Service;

public interface ThemeService {
    String THEME_PROPERTY_FILE_NAMES = "theme.json";
    String THEME_SETTINGS_FILE_NAMES = "settings.json";
    String THEME_FOLDER = "themes/";

    String render(String pagename);

    String getActivatedTheme();

    void deleteTheme(String themedir);

    void deleteTheme(int themeid);

    void activatedTheme(String themedir);

    void activatedTheme(int themeid);
}
