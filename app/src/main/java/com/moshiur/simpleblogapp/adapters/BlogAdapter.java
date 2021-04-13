package com.moshiur.simpleblogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.moshiur.simpleblogapp.BR;
import com.moshiur.simpleblogapp.R;
import com.moshiur.simpleblogapp.databinding.BlogItemBinding;
import com.moshiur.simpleblogapp.models.Blog;

import java.util.Objects;

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
            return Objects.equals(oldItem, newItem);
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

        //added onItemClickListener to the cover image
        holder.getBlogItemBindingImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(currentBlog);
                }
            }
        });
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

        public ImageView getBlogItemBindingImageView() {
            return blogItemBinding.coverPhoto;
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Blog blog);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
