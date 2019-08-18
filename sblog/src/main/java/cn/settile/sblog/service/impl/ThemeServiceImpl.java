package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.theme.Theme;
import cn.settile.sblog.service.OptionService;
import cn.settile.sblog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** ThemeService的实现，用以进行主题相关的操作
 * @author lzjyz
 * @date 2019/8/16
 */
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    OptionService optionService;

    @Override
    public String render(String pagename) {
        StringBuffer themeStr = new StringBuffer("themes/");
//        themeStr.append(THEME_);
        themeStr.append("/");
        return themeStr.append(pagename).toString();
    }

    @Override
    public String getActivatedTheme() {
        return null;
    }

    @Override
    public void deleteTheme(String themedir) {

    }

    @Override
    public void deleteTheme(int themeid) {

    }

    @Override
    public void activatedTheme(String themedir) {

    }

    @Override
    public void activatedTheme(int themeid) {

    }
}
