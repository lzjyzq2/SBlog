package cn.settile.sblog.model.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/** LoginController 参数
 * @author : lzjyz
 * @date : 2020-03-04 11:21
 */
@Data
@ApiModel
public class LoginParam {
    private String username;
    private String password;
}
