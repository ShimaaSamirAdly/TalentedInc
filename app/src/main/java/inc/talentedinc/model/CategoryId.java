
package inc.talentedinc.model;

import java.io.Serializable;

public class CategoryId implements Serializable
{

    private Integer categoryId;
    private String name;
    private String imageUrl;
    private final static long serialVersionUID = -7919680029724642283L;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
