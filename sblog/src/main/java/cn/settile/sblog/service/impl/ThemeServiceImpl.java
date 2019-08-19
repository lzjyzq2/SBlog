package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.db.option.Propertys.ThemeProperties;
import cn.settile.sblog.service.OptionService;
import cn.settile.sblog.service.ThemeService;
import org.springframework.stereotype.Service;

/** ThemeService的实现，用以进行主题相关的操作
 * @author lzjyz
 * @date 2019/8/16
 */
@Service
public class ThemeServiceImpl implements ThemeService {

    OptionService optionService;
    ThemeProperties themeProperties;

    public ThemeServiceImpl(OptionService optionService) {
        this.optionService = optionService;
        this.themeProperties = optionService.getPropertiesByClass(ThemeProperties.class);
    }

    @Override
    public String render(String pagename) {
        StringBuffer themeStr = new StringBuffer("themes/");
        themeStr.append(getActivatedTheme());
        themeStr.append("/");
        return themeStr.append(pagename).toString();
    }

    @Override
    public String getActivatedTheme() {
        return themeProperties.getActivatedTheme();
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
