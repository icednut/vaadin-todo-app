package space.icednut.dev.vaadin.exercise.spring.todo.colleage;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoList extends VerticalLayout implements TodoAppParticipant {

    private final IMediator mediator;

    public TodoList(IMediator mediator) {
        this.mediator = mediator;
        mediator.registerTodoList(this);
    }

    @Override
    public void execute() {
    }

    public void addTodo(String todoMessage) {
        final TodoListElement todoListElement = new TodoListElement(todoMessage, targetTodoListElement -> {
            System.out.println("삭제 핸들링");
        }, mediator);

        add(todoListElement);
    }
}
