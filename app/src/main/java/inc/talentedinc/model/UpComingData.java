
package inc.talentedinc.model;

/**
 * Created by asmaa on 05/21/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class UpComingData implements Serializable {

    private int page;
    private int totalResults;
    private int totalPages;
    private ArrayList<Result> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
