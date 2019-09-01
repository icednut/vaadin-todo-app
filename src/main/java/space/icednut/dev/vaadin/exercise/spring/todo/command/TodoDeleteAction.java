package space.icednut.dev.vaadin.exercise.spring.todo.command;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoMessage;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoDeleteAction implements TodoAction {

    private final Supplier<TodoMessage> todoDeleteActionSupplier;
    private final IMediator mediator;
    private TodoMessage todoMessage;

    public TodoDeleteAction(Supplier<TodoMessage> todoDeleteActionSupplier, IMediator mediator) {
        this.todoDeleteActionSupplier = todoDeleteActionSupplier;
        this.mediator = mediator;
    }

    @Override
    public TodoMessage doIt() {
        this.todoMessage = todoDeleteActionSupplier.get();
        return todoMessage;
    }

    @Override
    public void undoIt() {
        Optional.ofNullable(todoMessage)
                .ifPresent(actualTodoMessage -> mediator.addTodoForUndo(actualTodoMessage.getTodoMessage()));
    }
}
