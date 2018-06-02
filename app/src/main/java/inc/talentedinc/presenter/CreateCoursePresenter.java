package inc.talentedinc.presenter;

import inc.talentedinc.interactor.createcourse.CreateCourseImpl;
import inc.talentedinc.interactor.createcourse.CreateCourseInter;
import inc.talentedinc.listener.OnCreateCourse;
import inc.talentedinc.model.Course;

/**
 * Created by Alaa on 6/1/2018.
 */

public class CreateCoursePresenter  {

    CreateCourseView createCourseView ;
    private CreateCourseInter createCourseInter ;

    public CreateCoursePresenter(CreateCourseView createCourseView){
        this.createCourseView =createCourseView ;
        createCourseInter = new CreateCourseImpl();
    }

    public void courseCreated(Course course){
        createCourseInter.createCourse(course, new OnCreateCourse() {
            @Override
            public void onSuccess(Object object) {
                createCourseView.successToCreateCourse();
            }

            @Override
            public void onFail() {
                createCourseView.failToCreateCourse();
            }
        });

    }




    public interface CreateCourseView {
       void successToCreateCourse();
       void failToCreateCourse();

    }
}
