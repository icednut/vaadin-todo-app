package space.icednut.dev.vaadin.exercise.component;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * @author will.109
 * @date 04/09/2019
 **/
public class TodoListElement extends HorizontalLayout {

    private final String todoMessage;

    public TodoListElement(String todoMessage) {
        this.todoMessage = todoMessage;
    }

    public void addDeleteClickListener(Object o) {
    }
}
