package com.antondolganov.mapapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Login {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String access_token;
    private String token_type;

    public Login(String access_token, String token_type) {
        this.access_token = access_token;
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
