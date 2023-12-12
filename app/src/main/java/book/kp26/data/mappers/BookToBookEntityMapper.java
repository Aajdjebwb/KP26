package book.kp26.data.mappers;

import book.kp26.data.Book;
import book.kp26.data.room.BookEntity;

public class BookToBookEntityMapper implements Mapper<Book, BookEntity> {
    @Override
    public BookEntity map(Book val) {
        return new BookEntity(val.getName(),val.getAuthor(), val.getYear(),val.getNote());
    }
}
