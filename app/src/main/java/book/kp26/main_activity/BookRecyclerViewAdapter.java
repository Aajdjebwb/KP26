package book.kp26.main_activity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import book.kp26.data.Book;
import book.kp26.R;

import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookHolder> {

    private final MainActivityViewModel model;
    private List<Book> list;

    public BookRecyclerViewAdapter(MainActivityViewModel model, List<Book> list) {
        this.model = model;
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        model.getListMutableLiveData().observeForever(this::reInit);
    }


    @SuppressLint("NotifyDataSetChanged")
    public void reInit(List<Book> newItems) {
        if (list == null || list.isEmpty()) {
            list = newItems;
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new BooksDiffUtils(list, newItems));
        list = newItems;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView author;

        private final ImageView edit;
        private final ImageView remove;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            author = itemView.findViewById(R.id.author);
            edit = itemView.findViewById(R.id.edit_icon);
            remove = itemView.findViewById(R.id.trash_icon);
        }

        public void bind(Book item) {
            name.setText(item.getName());
            author.setText(item.getAuthor());
            remove.setOnClickListener(v -> {
                Book tmp=list.get(getAdapterPosition());
                model.onRemoveClicked(tmp);
            });

            edit.setOnClickListener(v -> {
                Book tmp=list.get(getAdapterPosition());
                model.onEditClicked(tmp);
            });
        }


    }
}
