package space.icednut.dev.vaadin.exercise.spring.todo.exception;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class InvalidTodoMessageException extends RuntimeException {
    public InvalidTodoMessageException(String message) {
        super(message);
    }
}
