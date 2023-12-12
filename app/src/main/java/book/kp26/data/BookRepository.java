package book.kp26.data;

import book.kp26.data.concurency.TaskCallback;

import java.util.List;

public interface BookRepository {
    void addNewBook(Book book);

    void getAllBooks(TaskCallback<List<Book>> callback);
}
