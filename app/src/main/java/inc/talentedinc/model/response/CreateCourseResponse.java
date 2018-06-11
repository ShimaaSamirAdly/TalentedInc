package inc.talentedinc.model.response;

/**
 * Created by Alaa on 6/8/2018.
 */

public class CreateCourseResponse {
    private Integer offeredCourseId ;

    public void setCourseId(Integer courseId) {
        this.offeredCourseId = courseId;
    }

    public Integer getCourseId() {
        return offeredCourseId;
    }
}
