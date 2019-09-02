package space.icednut.dev.vaadin.exercise.spring.todo.mediator;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoMessage;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoMediator implements IMediator {

    private TodoInput todoInput;
    private TodoList todoList;

    @Override
    public void registerTodoInput(TodoInput todoInput) {
        this.todoInput = todoInput;
    }

    @Override
    public void registerTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @Override
    public void addTodo() {
        TodoMessage todoMessage = todoInput.getCurrentTodoMessage();

        todoList.addTodo(todoMessage);
        todoInput.clearField();
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoList.deleteTodo(todoId);
    }
}
