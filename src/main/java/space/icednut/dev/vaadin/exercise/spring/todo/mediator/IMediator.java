package space.icednut.dev.vaadin.exercise.spring.todo.mediator;

import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoListElement;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public interface IMediator {

    void registerTodoInput(TodoInput todoInput);

    void registerTodoList(TodoList todoList);

    void registerTodoListElement(TodoListElement todoListElement);

    void addTodo(String todoMessage);

    void deleteTodo(TodoListElement targetTodoListElement);
}
