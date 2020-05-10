package cn.settile.sblog.model.param;

import cn.settile.sblog.utils.CommonUtil;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : lzjyz
 * @date : 2020-05-02 19:08
 */
@Getter
@Setter
@ApiModel
public class BookParam {
    private long id;
    private String name;
    private String info = "";

    public boolean checkIsRight(){
        boolean flag = true;
        if(CommonUtil.isEmpty(name.trim())){
            flag = false;
        }
        return flag;
    }
}
