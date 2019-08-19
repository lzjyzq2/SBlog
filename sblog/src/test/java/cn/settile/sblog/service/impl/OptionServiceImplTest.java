package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.db.option.Propertys.ThemeProperties;
import cn.settile.sblog.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionServiceImplTest {
    @Autowired
    OptionService optionService;
    @Test
    public void getOptionsByProperties() {

    }

    @Test
    public void getPropertiesByClass() {
        ThemeProperties themeProperties = optionService.getPropertiesByClass(ThemeProperties.class);
        log.info(themeProperties.toString());
    }

    @Test
    public void save() {
        ThemeProperties themeProperties = new ThemeProperties();
        themeProperties.setId("settile_author");
        themeProperties.setActivatedTheme("SBlog");
        optionService.save(optionService.getOptionsByProperties(themeProperties));
    }

    @Test
    public void save1() {
    }

    @Test
    public void save2() {
    }
}