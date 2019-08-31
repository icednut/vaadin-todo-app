package space.icednut.dev.vaadin.exercise.spring.todo.colleage;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEventListener;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoInput extends HorizontalLayout implements TodoAppParticipant {

    private final TextField todoField;
    private final Button addButton;
    private final IMediator mediator;

    public TodoInput(TodoAppEventListener<TodoInput> listner, IMediator mediator) {
        this.mediator = mediator;
        this.todoField = new TextField();
        this.addButton = new Button("Add");

        mediator.registerTodoInput(this);
        addButton.addClickListener(event -> listner.handle(this));
        add(todoField, addButton);
    }

    @Override
    public void execute() {
        final String todoMessage = todoField.getValue();
        mediator.addTodo(todoMessage);
    }

    public void clearField() {
        todoField.setValue("");
    }
}