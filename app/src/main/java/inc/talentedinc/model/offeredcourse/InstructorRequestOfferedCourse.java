package inc.talentedinc.model.offeredcourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstructorRequestOfferedCourse {

    @SerializedName("instructorId")
    @Expose
    private Integer instructorId;
    @SerializedName("courseId")
    @Expose
    private Integer courseId;

    public InstructorRequestOfferedCourse(Integer instructorId, Integer courseId) {
        this.instructorId = instructorId;
        this.courseId = courseId;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
