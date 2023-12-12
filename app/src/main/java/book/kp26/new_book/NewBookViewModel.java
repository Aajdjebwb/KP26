package book.kp26.new_book;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import book.kp26.data.Book;
import book.kp26.data.BookRepository;
import book.kp26.data.mappers.Mapper;

public class NewBookViewModel extends ViewModel {

    private final MutableLiveData<NewBookModel> newBookModelMutableLiveData;
    private final MutableLiveData<State> state;
    private BookRepository bookRepository;

    public NewBookViewModel() {
        newBookModelMutableLiveData = new MutableLiveData<>(new NewBookModel());
        state = new MutableLiveData<>(new State());
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MutableLiveData<NewBookModel> getNewBookModelMutableLiveData() {
        return newBookModelMutableLiveData;
    }

    public MutableLiveData<State> getState() {
        return state;
    }

    public void onSaveClicked(View v){
        bookRepository.addNewBook(mapper.map(newBookModelMutableLiveData.getValue()));
        finish();
    }

    public void finish(){
        State tmp=state.getValue();
        if (tmp != null) {
            tmp.needFinish=true;
            state.postValue(tmp);
        }
    }
    private final Mapper<NewBookModel,Book> mapper = value -> {
        return new Book(value.getName(),value.getAuthor(),Integer.parseInt(value.getYear()),value.getNotes());
    };

    public class State{
        boolean needFinish;
    }
}
