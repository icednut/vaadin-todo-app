package space.icednut.dev.vaadin.exercise.spring.todo.event;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoAppEvent<T extends TodoAppParticipant> {

    private final T source;

    public TodoAppEvent(T source) {
        this.source = source;
    }

    public static <T extends TodoAppParticipant> TodoAppEvent of(T source) {
        return new TodoAppEvent(source);
    }

    public T getSource() {
        return source;
    }
}
