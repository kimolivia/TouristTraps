package hu.ait.android.touristinfo.data;

import io.realm.RealmObject;

/**
 * Created by oliviakim on 12/5/17.
 */

public class Sights extends RealmObject {

    // dummy class to test recyclerview and realm

    public Sights() {

    }

    private String todoTitle;
    private String createDate;
    private boolean done;

    public Sights(String todoTitle, String createDate, boolean done) {
        this.todoTitle = todoTitle;
        this.createDate = createDate;
        this.done = done;

    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
