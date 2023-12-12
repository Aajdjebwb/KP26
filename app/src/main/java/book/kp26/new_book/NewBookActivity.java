package book.kp26.new_book;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import book.kp26.data.BookDatabase;
import book.kp26.databinding.ActivityNewBookBinding;

public class NewBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewBookViewModel viewModel = new ViewModelProvider(this).get(NewBookViewModel.class);
        viewModel.setBookRepository(BookDatabase.getInstance(getApplicationContext()));
        ActivityNewBookBinding binding = ActivityNewBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getState().observe(this,state -> {
            if(state.needFinish)
                NewBookActivity.this.finish();
        });
    }
}