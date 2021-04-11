package com.moshiur.simpleblogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.activities.MainActivity;
import com.moshiur.simpleblogapp.models.Blog;

public class BlogAdapter extends ListAdapter<Blog, BlogAdapter.BlogHolder> {

    private OnItemClickListener listener;
    private Context mContext;

    public BlogAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
    }

    private static final DiffUtil.ItemCallback<Blog> DIFF_CALLBACK = new DiffUtil.ItemCallback<Blog>() {
        @Override
        public boolean areItemsTheSame(Blog oldItem, Blog newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(Blog oldItem, Blog newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getCoverPhoto().equals(newItem.getCoverPhoto());
        }
    };

    @NonNull
    @Override
    public BlogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_item, parent, false);
        return new BlogHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull BlogHolder holder, int position) {
        Blog currentBlog = getItem(position);
        Glide.with(mContext).load(currentBlog.getCoverPhoto()).into(holder.imageViewCoverPhoto);
        holder.textViewTitle.setText(currentBlog.getTitle());
        holder.textViewAuthor.setText(currentBlog.getAuthor().getName());
        holder.textViewCategory.setText(String.valueOf(currentBlog.getCategories().get(0)));
    }

    public Blog getBlogAt(int position) {
        return getItem(position);
    }

    class BlogHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewCoverPhoto;
        private TextView textViewTitle;
        private TextView textViewAuthor;
        private TextView textViewCategory;
        public BlogHolder(View itemView) {
            super(itemView);
            imageViewCoverPhoto = itemView.findViewById(R.id.cover_photo);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewAuthor = itemView.findViewById(R.id.author);
            textViewCategory= itemView.findViewById(R.id.category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Blog blog);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
