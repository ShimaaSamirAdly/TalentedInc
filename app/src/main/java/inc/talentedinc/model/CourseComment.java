
package inc.talentedinc.model;

import java.io.Serializable;

public class CourseComment implements Serializable
{

    private Integer userIdOfComment;
    private String comment;
    private String time;
    private final static long serialVersionUID = -1920818391702369194L;

    public Integer getUserIdOfComment() {
        return userIdOfComment;
    }

    public void setUserIdOfComment(Integer userIdOfComment) {
        this.userIdOfComment = userIdOfComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
