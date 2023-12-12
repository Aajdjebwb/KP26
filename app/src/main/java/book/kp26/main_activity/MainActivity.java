package book.kp26.main_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import book.kp26.data.BookDatabase;
import book.kp26.databinding.ActivityMainBinding;
import book.kp26.new_book.NewBookActivity;


public class MainActivity extends AppCompatActivity {


    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.setBookRepository(BookDatabase.getInstance(getApplicationContext()));
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getState().observe(this,state -> {
            if (state.openNewBookActivity){
                Intent intent=new Intent(MainActivity.this, NewBookActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.reInitList();
    }
}