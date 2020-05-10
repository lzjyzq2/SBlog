package cn.settile.sblog.model.param;

import cn.settile.sblog.utils.CommonUtil;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : lzjyz
 * @date : 2020-05-02 23:32
 */
@Getter
@Setter
@Builder
@ApiModel
public class SubsectionParam {
    private long id;
    private long bookId;
    private String name;

    public boolean checkIsRight(){
        boolean flag = true;
        if(CommonUtil.isEmpty(name.trim())){
            flag = false;
        }
        return flag;
    }
}
