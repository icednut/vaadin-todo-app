package space.icednut.dev.vaadin.exercise.spring.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Consumer;

/**
 * @author will.109
 * @date 04/09/2019
 **/
public class TodoListElement extends HorizontalLayout {

    private final Checkbox checkbox;
    private final Icon deleteButton = new Icon(VaadinIcon.CLOSE_CIRCLE);

    public TodoListElement(String todoMessage, Consumer<TodoListElement> deleteClickListener) {
        this.checkbox = new Checkbox(todoMessage);
        deleteButton.setSize("15px");
        deleteButton.addClickListener(event -> {
            deleteClickListener.accept(this);
        });

        setAlignItems(Alignment.CENTER);
        add(checkbox, deleteButton);
    }
}
