package cn.settile.sblog.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/** 国际化工具类
 * @author : lzjyz
 * @date : 2020-01-26 21:07
 */
@Component
public class MessagesUtil {

    private static MessageSource messageSource;

    public MessagesUtil(MessageSource messageSource) {
        MessagesUtil.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }



}