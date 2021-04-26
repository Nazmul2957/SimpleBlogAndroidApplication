package com.example.simpleblogandroidapplication.model;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bumptech.glide.Glide;
import com.example.simpleblogandroidapplication.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Entity(indices = {@Index(value = {"id"}, unique = true)})
public class Blog {


        @SerializedName("id")
        @Expose
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private Integer id;
        @SerializedName("title")
        @Expose
        @NonNull
        private String title;
        @SerializedName("description")
        @Expose
        @NonNull
        private String description;
        @SerializedName("cover_photo")
        @Expose
        private String coverPhoto;
        @SerializedName("categories")
        @Expose
        @NonNull
        @TypeConverters({Converters.class})
        private List<String> categories = null;
        @SerializedName("author")
        @Expose
        @NonNull
        @Embedded
        private Author author;


    public Blog(@NonNull String title, @NonNull String description, List<String> categories, @NonNull Author author) {
        this.title = title;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.categories = categories;
        this.author = author;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCoverPhoto() {
            return coverPhoto;
        }

        public void setCoverPhoto(String coverPhoto) {
            this.coverPhoto = coverPhoto;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

    @NonNull
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", coverPhoto='" + coverPhoto + '\'' +
                ", categories=" + categories +
                ", author=" + author +
                '}';
    }
@BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView,String url){
        Glide.with(imageView)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }
}


