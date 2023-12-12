package book.kp26.data.mappers;

import book.kp26.data.Book;
import book.kp26.data.room.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookEntitiesListToBookList implements Mapper<List<BookEntity>, List<Book>> {

    @Override
    public List<Book> map(List<BookEntity> value) {
        BookEntityToBookMapper mapper=new BookEntityToBookMapper();
        List<Book> result=new ArrayList<>();
        for(BookEntity book:value){
            result.add(mapper.map(book));
        }
        return result;
    }
}
