package com.example.simpleblogandroidapplication.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(indices = {@Index(value = {"authorId"})})
public class Author {


        @SerializedName("id")
        @Expose
        @PrimaryKey
        @NonNull
        private Integer authorId;
        @SerializedName("name")
        @Expose
        @NonNull
        private String name;
        @SerializedName("avatar")
        @Expose
        @NonNull
        private String avatar;
        @SerializedName("profession")
        @Expose
        @NonNull
        private String profession;

    public Author(@NonNull Integer authorId, @NonNull String name, @NonNull String avatar, @NonNull String profession) {
        this.authorId = authorId;
        this.name = name;
        this.avatar = avatar;
        this.profession = profession;
    }

    @NonNull
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(@NonNull Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

    }

