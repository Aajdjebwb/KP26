package book.kp26.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface BookDAO {
    @Query("SELECT * FROM Books")
    List<BookEntity> getAll();

    @Insert
    void insert(BookEntity book);

}

