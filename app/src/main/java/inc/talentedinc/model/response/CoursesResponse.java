
package inc.talentedinc.model.response;

import java.io.Serializable;
import java.util.ArrayList;

import inc.talentedinc.model.Result;

public class CoursesResponse implements Serializable
{
    private Integer pageNumber;
    private Integer totalNumberOfUpComingCourses;
    private Integer totalNumberOfPages;
    private ArrayList<Result> result;
    private final static long serialVersionUID = 3413976361207597581L;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalNumberOfUpComingCourses() {
        return totalNumberOfUpComingCourses;
    }

    public void setTotalNumberOfUpComingCourses(Integer totalNumberOfUpComingCourses) {
        this.totalNumberOfUpComingCourses = totalNumberOfUpComingCourses;
    }

    public Integer getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(Integer totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }
}
