package com.moshiur.simpleblogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.moshiur.simpleblogapp.BR;
import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.databinding.BlogItemBinding;
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
        BlogItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.blog_item, parent, false);
        return new BlogHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogHolder holder, int position) {
        Blog currentBlog = getItem(position);
        holder.bind(currentBlog);
    }

    public Blog getBlogAt(int position) {
        return getItem(position);
    }

    class BlogHolder extends RecyclerView.ViewHolder {

        public BlogItemBinding blogItemBinding;

        public BlogHolder(BlogItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.blogItemBinding = itemBinding;
        }

        public void bind(Object obj) {
            blogItemBinding.setVariable(BR.blogItem, obj);
            //blogItemBinding.executePendingBindings();
        }


//            imageViewCoverPhoto = itemView.findViewById(R.id.cover_photo);
//            textViewTitle = itemView.findViewById(R.id.title);
//            textViewAuthor = itemView.findViewById(R.id.author);
//            textViewCategory= itemView.findViewById(R.id.category);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    if (listener != null && position != RecyclerView.NO_POSITION){
//                        listener.onItemClick(getItem(position));
//                    }
//                }
//            });
    }

    public interface OnItemClickListener {
        void onItemClick(Blog blog);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
