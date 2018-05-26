package inc.talentedinc.model;

public class Course {

    private PublishedCoursePK publishedCoursePK;
    private int publishApproval;
    private int status;
    private int applicantNoCompleted;

    public Course() {
    }

    public PublishedCoursePK getPublishedCoursePK() {
        return publishedCoursePK;
    }

    public void setPublishedCoursePK(PublishedCoursePK publishedCoursePK) {
        this.publishedCoursePK = publishedCoursePK;
    }

    public int getPublishApproval() {
        return publishApproval;
    }

    public void setPublishApproval(int publishApproval) {
        this.publishApproval = publishApproval;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getApplicantNoCompleted() {
        return applicantNoCompleted;
    }

    public void setApplicantNoCompleted(int applicantNoCompleted) {
        this.applicantNoCompleted = applicantNoCompleted;
    }
}
