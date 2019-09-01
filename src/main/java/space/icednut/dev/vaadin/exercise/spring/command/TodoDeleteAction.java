package space.icednut.dev.vaadin.exercise.spring.command;

import space.icednut.dev.vaadin.exercise.spring.message.TodoMessage;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author will.109
 * @date 01/09/2019
 **/
public class TodoDeleteAction implements TodoAction {

    private final Supplier<TodoMessage> actionBody;
    private final Consumer<TodoMessage> undoActionBody;
    private TodoMessage todoMessage;

    public TodoDeleteAction(Supplier<TodoMessage> actionBody, Consumer<TodoMessage> undoActionBody) {
        this.actionBody = actionBody;
        this.undoActionBody = undoActionBody;
    }

    @Override
    public void doIt() {
        this.todoMessage = actionBody.get();
    }

    @Override
    public void undoIt() {
        undoActionBody.accept(todoMessage);
    }
}
