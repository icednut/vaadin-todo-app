package space.icednut.dev.vaadin.exercise.spring.todo;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoMessage {

    private Long todoId;
    private String todoMessage;

    public TodoMessage(Long todoId, String todoMessage) {
        this.todoId = todoId;
        this.todoMessage = todoMessage;
    }

    public Long getTodoId() {
        return todoId;
    }

    public String getTodoMessage() {
        return todoMessage;
    }
}
