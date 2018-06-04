package inc.talentedinc.model.offeredcourse;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferedCoursesResponse {

    @SerializedName("content")
    @Expose
    private ArrayList<OfferedCourseDetailed> content = null;
    @SerializedName("last")
    @Expose
    private Boolean last;
    @SerializedName("totalElements")
    @Expose
    private Integer totalElements;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("first")
    @Expose
    private Boolean first;
    @SerializedName("numberOfElements")
    @Expose
    private Integer numberOfElements;

    public ArrayList<OfferedCourseDetailed> getContent() {
        return content;
    }

    public void setContent(ArrayList<OfferedCourseDetailed> content) {
        this.content = content;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

}
