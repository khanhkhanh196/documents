package com.restapi.restapidemo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="updated_date")
    private Timestamp  updatedDate;

    @PrePersist
    public void setCreatedDate(){
        long date = new Date().getTime();
        Timestamp createdDate = new Timestamp(date);
        this.createdDate = createdDate;
    }

    @PreUpdate
    public void setUpdatedDate(){
        long date = new Date().getTime();
        Timestamp updatedDate = new Timestamp(date);
        this.updatedDate = updatedDate;
    }
}
