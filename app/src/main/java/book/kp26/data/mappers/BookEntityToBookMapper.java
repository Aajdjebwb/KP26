package book.kp26.data.mappers;

import book.kp26.data.Book;
import book.kp26.data.room.BookEntity;

public class BookEntityToBookMapper implements Mapper<BookEntity, Book> {
    @Override
    public Book map(BookEntity val) {
        return new Book(val.getId(), val.getName(), val.getAuthor(), val.getYear(), val.getNote());
    }
}
