package book.kp26.data;

import android.content.Context;

import androidx.room.Room;

import book.kp26.data.concurency.TaskCallback;
import book.kp26.data.mappers.BookEntitiesListToBookList;
import book.kp26.data.mappers.BookToBookEntityMapper;
import book.kp26.data.room.AppDatabase;
import book.kp26.data.room.BookDAO;
import book.kp26.data.room.BookEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookDatabase implements BookRepository{

    private static BookDatabase database = null;
    private final AppDatabase db;
    private final ExecutorService executorService;
    private BookDAO bookDAO;

    private BookToBookEntityMapper bookToBookEntityMapper=new BookToBookEntityMapper();
    private BookEntitiesListToBookList bookEntitiesListToBookList=new BookEntitiesListToBookList();

    private BookDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "BookDB").build();
        executorService = Executors.newSingleThreadExecutor();
        bookDAO=db.getBookDAO();
    }
    public static BookDatabase getInstance(Context context) {
        if (database == null) {
            database = new BookDatabase(context);
        }
        return database;
    }

    @Override
    public void addNewBook(Book book){
        executorService.submit(() -> {
           bookDAO.insert(bookToBookEntityMapper.map(book));
        });
    }

    @Override
    public void getAllBooks(TaskCallback<List<Book>> callback){
        executorService.submit(() -> {
           List<BookEntity> books=bookDAO.getAll();
           callback.onCompleted(bookEntitiesListToBookList.map(books),null);
        });
    }


}
