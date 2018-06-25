package inc.talentedinc.model.offeredcourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstructorAcceptOrCancelRequest {

    @SerializedName("courseId")
    @Expose
    private Integer courseId;
    @SerializedName("workSpaceId")
    @Expose
    private Integer workSpaceId;

    public InstructorAcceptOrCancelRequest(Integer courseId, Integer workSpaceId) {
        this.courseId = courseId;
        this.workSpaceId = workSpaceId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getWorkSpaceId() {
        return workSpaceId;
    }

    public void setWorkSpaceId(Integer workSpaceId) {
        this.workSpaceId = workSpaceId;
    }
}
