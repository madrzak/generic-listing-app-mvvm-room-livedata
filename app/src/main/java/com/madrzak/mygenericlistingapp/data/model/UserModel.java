package com.madrzak.mygenericlistingapp.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import static com.madrzak.mygenericlistingapp.data.model.UserModel.TABLE_NAME;

/**
 * Created by Łukasz on 04/11/2017.
 */

@Entity(tableName = TABLE_NAME)
public class UserModel {
    public static final String TABLE_NAME = "users";
    public static final String COL_DATE_CREATED = "date_created";
    public static final String COL_NAME = "name";
    public static final String COL_SURNAME = "surname";

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = COL_NAME)
    private String name;

    @ColumnInfo(name = COL_SURNAME)
    private String surname;

    @ColumnInfo(name = COL_DATE_CREATED)
    private Date dateCreated;





    public boolean isValid() {
        return name != null && !StringUtils.isEmpty(name)
                && surname != null && !StringUtils.isEmpty(surname);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
