
package inc.talentedinc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InstructorId implements Serializable
{

    private Integer userId;
    private Double totalRate;

    private final static long serialVersionUID = 5854272730306471510L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Double totalRate) {
        this.totalRate = totalRate;
    }
}
