package inc.talentedinc.model.request;

import java.io.Serializable;

/**
 * Created by asmaa on 06/25/2018.
 */

public class CommentRequest extends MainRequest implements Serializable{
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
