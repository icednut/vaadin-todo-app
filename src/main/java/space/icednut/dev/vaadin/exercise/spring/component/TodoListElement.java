package space.icednut.dev.vaadin.exercise.spring.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import space.icednut.dev.vaadin.exercise.spring.message.TodoMessage;

/**
 * @author will.109
 * @date 01/09/2019
 **/
public class TodoListElement extends HorizontalLayout {

    private final Checkbox todo;
    private final Icon deleteButton = new Icon(VaadinIcon.CLOSE_CIRCLE);
    private final TodoMessage todoMessage;

    public TodoListElement(TodoMessage todoMessage) {
        final String messageBody = todoMessage.getMessageBody();

        this.todoMessage = todoMessage;
        this.todo = new Checkbox(todoMessage.getMessageBody());

        setAlignItems(FlexComponent.Alignment.CENTER);
        deleteButton.setSize("15px");
        add(todo, deleteButton);
    }

    public void addDeleteClickListener(ComponentEventListener<ClickEvent<Icon>> listener) {
        deleteButton.addClickListener(listener);
    }

    public Long getTodoId() {
        return todoMessage.getTodoId();
    }
}
