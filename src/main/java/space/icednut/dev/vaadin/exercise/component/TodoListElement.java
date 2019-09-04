package space.icednut.dev.vaadin.exercise.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * @author will.109
 * @date 04/09/2019
 **/
public class TodoListElement extends HorizontalLayout {

    private final Checkbox checkbox;
    private final Icon deleteButton = new Icon(VaadinIcon.CLOSE_CIRCLE);

    public TodoListElement(String todoMessage) {
        this.checkbox = new Checkbox(todoMessage);
        deleteButton.setSize("15px");
        setAlignItems(Alignment.CENTER);
        add(checkbox, deleteButton);
    }

    public void addDeleteClickListener(ComponentEventListener<ClickEvent<Icon>> deleteClickListener) {
        deleteButton.addClickListener(deleteClickListener);
    }
}
