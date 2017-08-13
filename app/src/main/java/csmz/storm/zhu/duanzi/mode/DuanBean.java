package csmz.storm.zhu.duanzi.mode;

import com.google.gson.annotations.SerializedName;

import java.security.acl.Group;

/**
 * Created by uniface on 2017/8/3.
 */

public class DuanBean {

    @SerializedName("group")
    private GroupBean groupBean;
    private String type;

    public GroupBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(GroupBean groupBean) {
        this.groupBean = groupBean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
