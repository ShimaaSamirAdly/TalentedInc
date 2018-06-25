package inc.talentedinc.model.request;

import java.io.Serializable;

/**
 * Created by asmaa on 06/25/2018.
 */

public class MainRequest implements Serializable{
    private Integer userId;
    private Integer courseId;
    private String courseDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }
}
