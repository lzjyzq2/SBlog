package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.db.option.Option;
import cn.settile.sblog.model.db.option.Propertys.Properties;
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
    public  List<Option> getOptionsByProperties(Properties properties) {
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
            options.add(new Option(clazz.getSimpleName(),fieldName,value));
        }
        return options;
    }

    @Override
    public <T extends Properties> T getPropertiesByClass(Class<T> clazz) {
        T t = null;
        List<Option> options = optionDao.findOptionsByOptionKeyId(clazz.getSimpleName());
        try {
            t = clazz.newInstance();
            for(Field field : clazz.getDeclaredFields()) {
                for(Option option : options){
                    if(option.getOptionKey().getKey().equals(field.getName())){
                        boolean flag = field.isAccessible();
                        field.setAccessible(true);
                        Object object = option.getValue();
                        if (object!= null && field.getType().isAssignableFrom(object.getClass())) {
                            field.set(t, object);
                        }
                        field.setAccessible(flag);
                        break;
                    }
                }
            }
            return t;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void save(Option option) {
        optionDao.save(option);
    }

    @Override
    public void save(List<Option> options) {
        optionDao.saveAll(options);
    }

    @Override
    public void save(Properties properties) {
        List<Option> options = getOptionsByProperties(properties);
        save(options);
    }
}
