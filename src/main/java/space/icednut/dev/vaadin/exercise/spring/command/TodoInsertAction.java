package space.icednut.dev.vaadin.exercise.spring.command;

import space.icednut.dev.vaadin.exercise.spring.message.TodoMessage;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author will.109
 * @date 01/09/2019
 **/
public class TodoInsertAction implements TodoAction {

    private final Supplier<Optional<TodoMessage>> actionBody;
    private final Consumer<Optional<TodoMessage>> undoActionBody;
    private Optional<TodoMessage> todoMessage;

    public TodoInsertAction(Supplier<Optional<TodoMessage>> actionBody, Consumer<Optional<TodoMessage>> undoActionBody) {
        this.actionBody = actionBody;
        this.undoActionBody = undoActionBody;
    }

    @Override
    public void doIt() {
        final Optional<TodoMessage> todoMessage = actionBody.get();
        this.todoMessage = todoMessage;
    }

    @Override
    public void undoIt() {
        undoActionBody.accept(todoMessage);
    }
}
