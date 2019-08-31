package space.icednut.dev.vaadin.exercise.spring.todo.colleage;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEventListener;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoList extends VerticalLayout implements TodoAppParticipant {

    private final IMediator mediator;
    private final TodoAppEventListener<? extends TodoAppParticipant> actionListener;

    public TodoList(IMediator mediator, TodoAppEventListener<? extends TodoAppParticipant> actionListener) {
        this.mediator = mediator;
        this.actionListener = actionListener;
        mediator.registerTodoList(this);
    }

    @Override
    public void execute() {
    }

    public void addTodo(String todoMessage) {
        final TodoListElement todoListElement = new TodoListElement(todoMessage, mediator, actionListener);

        add(todoListElement);
    }
}
