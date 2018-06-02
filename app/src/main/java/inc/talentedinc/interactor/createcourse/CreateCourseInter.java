package inc.talentedinc.interactor.createcourse;

import inc.talentedinc.listener.OnCreateCourse;
import inc.talentedinc.model.Course;

/**
 * Created by Alaa on 6/1/2018.
 */

public interface CreateCourseInter {
    void createCourse(Course course , OnCreateCourse onCreateCourse);

}
