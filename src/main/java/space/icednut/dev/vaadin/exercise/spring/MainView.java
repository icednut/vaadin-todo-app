package space.icednut.dev.vaadin.exercise.spring;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.util.StringUtils;
import space.icednut.dev.vaadin.exercise.spring.command.TodoAction;
import space.icednut.dev.vaadin.exercise.spring.command.TodoDeleteAction;
import space.icednut.dev.vaadin.exercise.spring.command.TodoInsertAction;
import space.icednut.dev.vaadin.exercise.spring.component.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.component.TodoListElement;
import space.icednut.dev.vaadin.exercise.spring.message.TodoMessage;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Route
@PWA(name = "Design Pattern Exercise Command Pattern", shortName = "Command Pattern Exercise")
public class MainView extends VerticalLayout {

    private LinkedList<TodoAction> undoStack = new LinkedList<>();
    private LinkedList<TodoAction> redoStack = new LinkedList();
    private final AtomicLong todoIdGenerator = new AtomicLong();

    private final VerticalLayout todosList = new VerticalLayout();
    private final TodoInput todoInput = new TodoInput();
    private final Button undoButton = new Button("Undo");
    private final Button redoButton = new Button("Redo");

    public MainView() {

        undoButton.addClickListener(event -> {
            if (undoStack.isEmpty()) {
                return;
            }
            final TodoAction todoAction = undoStack.removeLast();
            todoAction.undoIt();
            redoStack.add(todoAction);
        });

        redoButton.addClickListener(event -> {
            if (redoStack.isEmpty()) {
                return;
            }
            final TodoAction todoAction = redoStack.removeLast();
            todoAction.doIt();
            undoStack.add(todoAction);
        });

        todoInput.addAddClickListener(event -> {
            final long todoId = todoIdGenerator.incrementAndGet();
            final String todoMessageBody = todoInput.getTodoMessage();
            final TodoMessage todoMessage = new TodoMessage(todoId, todoMessageBody);

            final TodoAction todoInsertAction = new TodoInsertAction(() -> {
                if (StringUtils.hasText(todoMessageBody)) {
                    final TodoListElement todoListElement = new TodoListElement(todoMessage);
                    final ComponentEventListener<ClickEvent<Icon>> deleteListener = getDeleteClickListener(todoMessage, todoListElement);

                    todoListElement.addDeleteClickListener(deleteListener);
                    todosList.add(todoListElement);
                    return Optional.ofNullable(todoMessage);
                }
                return Optional.empty();
            }, undoTodoElement -> {
                undoTodoElement.ifPresent(actualUndoTodoElement -> {
                    deleteByTodoId(actualUndoTodoElement.getTodoId());
                });
            });

            todoInsertAction.doIt();
            undoStack.add(todoInsertAction);
        });

        add(new HorizontalLayout(undoButton, redoButton), todoInput, todosList);
    }

    private void deleteByTodoId(Long targetTodoId) {
        todosList.getChildren()
                .map(component -> (TodoListElement) component)
                .filter(todoListElement -> todoListElement.getTodoId().equals(targetTodoId))
                .findFirst()
                .ifPresent(todoListElement -> todosList.remove(todoListElement));
    }

    private ComponentEventListener<ClickEvent<Icon>> getDeleteClickListener(TodoMessage todoMessage, TodoListElement todoListElement) {
        return deleteClick -> {
            final TodoDeleteAction todoDeleteAction = new TodoDeleteAction(() -> {
                deleteByTodoId(todoListElement.getTodoId());
                return todoMessage;
            }, undoTodoMessageBody -> {
                final TodoListElement undoTodoListElement = new TodoListElement(todoMessage);

                ComponentEventListener<ClickEvent<Icon>> deleteListener = getDeleteClickListener(undoTodoMessageBody, undoTodoListElement);
                undoTodoListElement.addDeleteClickListener(deleteListener);
                todosList.add(undoTodoListElement);
            });

            todoDeleteAction.doIt();
            undoStack.add(todoDeleteAction);
        };
    }
}
