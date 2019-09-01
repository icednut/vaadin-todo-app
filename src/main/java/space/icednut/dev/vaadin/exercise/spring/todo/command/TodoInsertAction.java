package space.icednut.dev.vaadin.exercise.spring.todo.command;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoMessage;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoInsertAction implements TodoAction {

    private final Supplier<TodoMessage> todoInsertActionSupplier;
    private final IMediator mediator;
    private TodoMessage todoMessage;

    public TodoInsertAction(Supplier<TodoMessage> todoInsertActionSupplier, IMediator mediator) {
        this.mediator = mediator;
        this.todoInsertActionSupplier = todoInsertActionSupplier;
    }

    @Override
    public TodoMessage doIt() {
        this.todoMessage = todoInsertActionSupplier.get();
        return todoMessage;
    }

    @Override
    public void undoIt() {
        Optional.ofNullable(todoMessage)
                .ifPresent(actualTodoMessage -> mediator.deleteTodoForUndo(actualTodoMessage.getTodoId()));
    }
}
