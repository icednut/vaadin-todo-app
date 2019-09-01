package space.icednut.dev.vaadin.exercise.spring.todo.mediator;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoMessage;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoListElement;
import space.icednut.dev.vaadin.exercise.spring.todo.command.TodoAction;
import space.icednut.dev.vaadin.exercise.spring.todo.command.TodoDeleteAction;
import space.icednut.dev.vaadin.exercise.spring.todo.command.TodoInsertAction;

import java.util.LinkedList;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoMediator implements IMediator {

    private TodoInput todoInput;
    private TodoList todoList;
    private TodoListElement todoListElement;
    private LinkedList<TodoAction> undoStack = new LinkedList<>();
    private LinkedList<TodoAction> redoStack = new LinkedList<>();

    @Override
    public void registerTodoInput(TodoInput todoInput) {
        this.todoInput = todoInput;
    }

    @Override
    public void registerTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @Override
    public void registerTodoListElement(TodoListElement todoListElement) {
        this.todoListElement = todoListElement;
    }

    @Override
    public TodoMessage addTodo(String todoMessage) {
        final TodoInsertAction todoInsertAction = new TodoInsertAction(() -> {
            todoInput.clearField();
            final Long todoId = todoList.addTodo(todoMessage);
            return new TodoMessage(todoId, todoMessage);
        }, this);

        undoStack.add(todoInsertAction);
        return todoInsertAction.doIt();
    }

    @Override
    public TodoMessage deleteTodo(Long todoId) {
        final TodoDeleteAction todoDeleteAction = new TodoDeleteAction(() -> {
            final String todoMessage = todoList.deleteTodo(todoId);
            return new TodoMessage(todoId, todoMessage);
        }, this);

        undoStack.add(todoDeleteAction);
        return todoDeleteAction.doIt();
    }

    @Override
    public void addTodoForUndo(String todoMessage) {
        todoList.addTodo(todoMessage);
    }

    @Override
    public void deleteTodoForUndo(Long todoId) {
        todoList.deleteTodo(todoId);
    }

    @Override
    public void undo() {
        if (undoStack.isEmpty()) {
            return;
        }
        final TodoAction action = undoStack.removeLast();
        action.undoIt();
        redoStack.add(action);
    }

    @Override
    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        final TodoAction action = redoStack.removeLast();
        action.doIt();
        undoStack.add(action);
    }
}
