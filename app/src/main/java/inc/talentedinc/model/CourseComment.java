
package inc.talentedinc.model;


import java.io.Serializable;

public class CourseComment implements Serializable{

    private Integer userIdOfComment;
    private String comment;
    private String time;
    private String userNameOfcomment;
    private String userImageOfComment;

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

    public String getUserNameOfcomment() {
        return userNameOfcomment;
    }

    public void setUserNameOfcomment(String userNameOfcomment) {
        this.userNameOfcomment = userNameOfcomment;
    }

    public String getUserImageOfComment() {
        return userImageOfComment;
    }

    public void setUserImageOfComment(String userImageOfComment) {
        this.userImageOfComment = userImageOfComment;
    }

}
