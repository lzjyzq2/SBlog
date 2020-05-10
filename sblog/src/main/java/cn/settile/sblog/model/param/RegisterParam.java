package cn.settile.sblog.model.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/** Register Mapping Params
 * @author : lzjyz
 * @date : 2020-03-04 11:24
 */
@Data
@ApiModel
public class RegisterParam {
    private String username;
    private String nickname;
    private String password;
    private String conpassword;
    private String email;
    private String captcha;

}
