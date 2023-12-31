package book.kp26.main_activity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import book.kp26.data.Book;
import book.kp26.data.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<List<Book>> listMutableLiveData;
    private final MutableLiveData<State> state;

    private final BookRecyclerViewAdapter recyclerViewAdapter;

    private BookRepository bookRepository;

    public MainActivityViewModel() {
        this.listMutableLiveData=new MutableLiveData<>(new ArrayList<>());
        this.state = new MutableLiveData<>(new State());
        this.recyclerViewAdapter = new BookRecyclerViewAdapter(MainActivityViewModel.this,listMutableLiveData.getValue());

        listMutableLiveData.observeForever(books -> {
            state.postValue(new State());
        });
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        reInitList();
    }

    public MutableLiveData<List<Book>> getListMutableLiveData(){
        return listMutableLiveData;
    }

    public MutableLiveData<State> getState() {
        return state;
    }

    public BookRecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public void onRemoveClicked(Book tmp) {
        Log.d("XXXX onRemove",tmp.toString());
    }

    public void onEditClicked(Book tmp) {
        Log.d("XXXX onEditClicked",tmp.toString());
    }

    public void onNewBookClicked(View v){
        State tmpState=state.getValue();
        tmpState.openNewBookActivity=true;
        state.postValue(tmpState);

    }

    public void reInitList(){
        bookRepository.getAllBooks((result, fault) -> {
            if(fault==null&&result!=null){
                listMutableLiveData.postValue(result);
            }
        });
    }
    public class State{

        public boolean openNewBookActivity=false;
        public boolean isListEmpty(){
            return listMutableLiveData.getValue().isEmpty();
        }
    }
}
