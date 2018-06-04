package inc.talentedinc.model.response;

import java.io.Serializable;

/**
 * Created by asmaa on 06/03/2018.
 */

public class BaseResponse implements Serializable {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
