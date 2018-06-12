package inc.talentedinc.model.offeredcourse;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import inc.talentedinc.model.InstructorImages;
import inc.talentedinc.model.InstructorSkills;
import inc.talentedinc.model.InstructorVideos;

public class OfferedCourseInstructor implements Serializable {
    
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("totalRate")
    @Expose
    private String totalRate;
    @SerializedName("instructorVideosCollection")
    @Expose
    private List<InstructorVideos> instructorVideosCollection = null;
    @SerializedName("skillsCollection")
    @Expose
    private List<InstructorSkills> skillsCollection = null;
    @SerializedName("instructorImagesCollection")
    @Expose
    private List<InstructorImages> instructorImagesCollection = null;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

    public List<InstructorVideos> getInstructorVideosCollection() {
        return instructorVideosCollection;
    }

    public void setInstructorVideosCollection(List<InstructorVideos> instructorVideosCollection) {
        this.instructorVideosCollection = instructorVideosCollection;
    }

    public List<InstructorSkills> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(List<InstructorSkills> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    public List<InstructorImages> getInstructorImagesCollection() {
        return instructorImagesCollection;
    }

    public void setInstructorImagesCollection(List<InstructorImages> instructorImagesCollection) {
        this.instructorImagesCollection = instructorImagesCollection;
    }

}
