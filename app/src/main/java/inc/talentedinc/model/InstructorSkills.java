package inc.talentedinc.model;

/**
 * Created by MMM on 6/8/2018.
 */

public class InstructorSkills {

    private int instructorId;

    private String skillValue;

    public InstructorSkills() {
    }

    public InstructorSkills(String skillValue){
        this.skillValue = skillValue;
    }

    public InstructorSkills(int instructorId, String skillValue) {
        this.instructorId = instructorId;
        this.skillValue = skillValue;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(String skillValue) {
        this.skillValue = skillValue;
    }

}
