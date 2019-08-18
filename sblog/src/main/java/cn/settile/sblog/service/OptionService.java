package cn.settile.sblog.service;

import cn.settile.sblog.model.option.Option;
import cn.settile.sblog.model.option.Propertys.Properties;
import org.springframework.stereotype.Service;

import java.util.List;

/** OptionService的实现，用以进行Option相关的操作
 * @author lzjyzq2
 * @date 2019/8/16
 */
public interface OptionService {
    /**
     * @param properties 需要转换为List<Option>的Properties对象
     * @return List<Option>
     */
    List<Option> getOptionListByProperties(Properties properties);

    /** 根据Properties类取出Properties
     * @param clazz 需要取出的Properties类
     * @return Properties 对象
     */
    Properties getPropertiesByClass(Class clazz);

    /** 存储Option
     * @param option 待存储的Option对象
     */
    void save(Option option);

    /** 存储List<Option>
     * @param options 待存储的List<Option>对象
     */
    void save(List<Option> options);

    /** 存储properties
     * @param properties 待存储的properties对象
     */
    void save(Properties properties);
}
