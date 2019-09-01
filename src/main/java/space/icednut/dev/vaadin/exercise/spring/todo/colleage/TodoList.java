package space.icednut.dev.vaadin.exercise.spring.todo.colleage;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEventListener;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoList extends VerticalLayout implements TodoAppParticipant {

    private final IMediator mediator;
    private final AtomicLong todoIdGenerator;
    private final TodoAppEventListener<? extends TodoAppParticipant> actionListener;

    public TodoList(IMediator mediator, TodoAppEventListener<? extends TodoAppParticipant> actionListener) {
        this.mediator = mediator;
        this.actionListener = actionListener;
        this.todoIdGenerator = new AtomicLong();
        mediator.registerTodoList(this);
    }

    @Override
    public void execute() {
    }

    public Long addTodo(String todoMessage) {
        final long todoId = todoIdGenerator.incrementAndGet();
        final TodoListElement todoListElement = new TodoListElement(todoId, todoMessage, mediator, actionListener);

        add(todoListElement);
        return todoId;
    }

    public String deleteTodo(Long todoId) {
        final Optional<TodoListElement> maybeTodoListElement = getChildren()
                .filter(component -> {
                    final TodoListElement each = (TodoListElement) component;
                    return each.getTodoId().equals(todoId);
                })
                .findFirst()
                .map(component -> (TodoListElement) component);

        if (maybeTodoListElement.isPresent()) {
            final TodoListElement todoListElement = maybeTodoListElement.get();

            remove(todoListElement);
            return todoListElement.getTodoMessage();
        }
        return null;
    }
}
