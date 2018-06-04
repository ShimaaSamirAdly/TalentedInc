package inc.talentedinc.model.offeredcourse;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferedCourseInstructor implements Serializable {
    
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("totalRate")
    @Expose
    private String totalRate;
    @SerializedName("instructorVideosCollection")
    @Expose
    private List<String> instructorVideosCollection = null;
    @SerializedName("skillsCollection")
    @Expose
    private List<String> skillsCollection = null;
    @SerializedName("instructorImagesCollection")
    @Expose
    private List<String> instructorImagesCollection = null;
    @SerializedName("instructorUrlsCollection")
    @Expose
    private List<String> instructorUrlsCollection = null;

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

    public List<String> getInstructorVideosCollection() {
        return instructorVideosCollection;
    }

    public void setInstructorVideosCollection(List<String> instructorVideosCollection) {
        this.instructorVideosCollection = instructorVideosCollection;
    }

    public List<String> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(List<String> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }

    public List<String> getInstructorImagesCollection() {
        return instructorImagesCollection;
    }

    public void setInstructorImagesCollection(List<String> instructorImagesCollection) {
        this.instructorImagesCollection = instructorImagesCollection;
    }

    public List<String> getInstructorUrlsCollection() {
        return instructorUrlsCollection;
    }

    public void setInstructorUrlsCollection(List<String> instructorUrlsCollection) {
        this.instructorUrlsCollection = instructorUrlsCollection;
    }
}
