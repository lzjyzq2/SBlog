package cn.settile.sblog.model.param;

import cn.settile.sblog.model.entity.*;
import cn.settile.sblog.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-27 22:41
 */
@Getter
@Setter
@ApiModel
public class ArticleParam {

    private long id;

    private long userId;
    /**
     * 所在分卷
     */
    private long subsectionId;
    /**
     * 文章标题
     */
    private String title;

    private boolean autoSummary = true;

    private String summary;

    /**
     * 文章内容
     */
    private String content;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date publishTime;

    private boolean isTaskPublish = false;
    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 能否评论：false-不能，true-能
     */
    private boolean canComment = true;

    /**
     * 能否查看
     */
    private boolean canView = true;

    /**
     * 能否复制：false-不能，true-能
     */
    private boolean canCopy = false;

    /**
     * 浏览量
     */
    private long views = 0;

    /**
     * 日前阅读量
     */
    private long dayView = 0;

    /**
     * 赞同数
     */
    private long approve = 0;

    private int state = 0;
    /**
     * 文章所具有的标签
     */
    @ManyToMany(mappedBy = "articles")
    private Set<Tag> tags;

    @ManyToMany(mappedBy = "articles")
    private Set<UploadImage> uploadImages;

    @ManyToMany(mappedBy = "articles")
    private Set<UploadFile> uploadFiles;

    @Override
    public String toString() {
        return "ArticleParam{" +
                "id=" + id +
                ", userId=" + userId +
                ", subsectionId=" + subsectionId +
                ", title='" + title + '\'' +
                ", autoSummary='" + autoSummary +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", taskPublish=" + isTaskPublish +
                ", updateTime=" + updateTime +
                ", canComment=" + canComment +
                ", canView=" + canView +
                ", canCopy=" + canCopy +
                ", views=" + views +
                ", dayView=" + dayView +
                ", approve=" + approve +
                ", state=" + state +
                ", tags=" + tags +
                '}';
    }

    public static boolean checkIsRight(ArticleParam articleParam){
        boolean flag = true;
        if(CommonUtil.isEmpty(articleParam.title.trim())) {
            flag = false;
        }
        if (CommonUtil.isEmpty(articleParam.content.trim())){
            flag = false;
        }
        if(CommonUtil.isEmpty(articleParam.subsectionId)){
            flag = false;
        }
        return flag;
    }

}
