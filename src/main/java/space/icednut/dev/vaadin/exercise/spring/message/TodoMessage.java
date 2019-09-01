package space.icednut.dev.vaadin.exercise.spring.message;

/**
 * @author will.109
 * @date 01/09/2019
 **/
public class TodoMessage {

    private Long todoId;
    private String messageBody;

    public TodoMessage(Long todoId, String messageBody) {
        this.todoId = todoId;
        this.messageBody = messageBody;
    }

    public Long getTodoId() {
        return todoId;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
