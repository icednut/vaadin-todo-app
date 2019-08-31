package space.icednut.dev.vaadin.exercise.spring.todo.mediator;

import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoListElement;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoMediator implements IMediator {

    private TodoInput todoInput;
    private TodoList todoList;
    private TodoListElement todoListElement;

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
    public void addTodo(String todoMessage) {
        todoList.addTodo(todoMessage);
        todoInput.clearField();
    }

    @Override
    public void deleteTodo(TodoListElement targetTodoListElement) {
    }
}
