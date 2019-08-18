package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.option.Option;
import cn.settile.sblog.model.option.Propertys.Properties;
import cn.settile.sblog.repository.OptionDao;
import cn.settile.sblog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzjyzq2
 * @date 2019/8/16
 */
@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionDao optionDao;

    @Override
    public List<Option> getOptionListByProperties(Properties properties) {
        List<Option> options = new ArrayList<>();
        Class<?> clazz = properties.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String value = null;
            try {
                value = String.valueOf(field.get(properties));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            options.add(new Option(clazz.getName(),fieldName,value));
        }
        return options;
    }

    @Override
    public Properties getPropertiesByClass(Class clazz) {
        return null;
    }

    @Override
    public void save(Option option) {

    }

    @Override
    public void save(List<Option> options) {

    }

    @Override
    public void save(Properties properties) {

    }
}
