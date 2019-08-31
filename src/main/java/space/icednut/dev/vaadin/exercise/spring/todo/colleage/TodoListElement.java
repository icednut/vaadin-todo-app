package space.icednut.dev.vaadin.exercise.spring.todo.colleage;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.util.StringUtils;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEvent;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEventListener;
import space.icednut.dev.vaadin.exercise.spring.todo.exception.InvalidTodoMessageException;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoListElement extends HorizontalLayout implements TodoAppParticipant {

    private final Checkbox todo;
    private final Icon deleteButton;
    private final IMediator mediator;

    public TodoListElement(String todoMessage, IMediator mediator, TodoAppEventListener<? extends TodoAppParticipant> actionListener) {
        Optional.ofNullable(todoMessage)
                .filter(StringUtils::hasText)
                .orElseThrow(() -> new InvalidTodoMessageException("Todo 메세지가 비어있습니다."));
        this.mediator = mediator;
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.todo = new Checkbox(String.format("[%s] %s", now, todoMessage));
        this.deleteButton = new Icon(VaadinIcon.CLOSE_CIRCLE);

        mediator.registerTodoListElement(this);
        deleteButton.setSize("15px");
        deleteButton.addClickListener(event -> actionListener.handle(TodoAppEvent.of(this)));
        setAlignItems(FlexComponent.Alignment.CENTER);
        addClassName("todo-list-element");
        add(todo, deleteButton);
    }

    @Override
    public void execute() {
        mediator.deleteTodo(this);
    }
}
