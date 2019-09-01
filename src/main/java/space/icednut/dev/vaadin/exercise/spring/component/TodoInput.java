package space.icednut.dev.vaadin.exercise.spring.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 * @author will.109
 * @date 01/09/2019
 **/
public class TodoInput extends HorizontalLayout {

    final TextField todoField = new TextField();
    final Button addButton = new Button("Add");

    public TodoInput() {
        add(todoField, addButton);
    }

    public void addAddClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        addButton.addClickListener(listener);
    }

    public String getTodoMessage() {
        return todoField.getValue();
    }
}
