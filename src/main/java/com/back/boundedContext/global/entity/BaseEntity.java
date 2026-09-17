package com.back.boundedContext.global.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
// 모든 엔티티 조상
public abstract class BaseEntity {
    public abstract int getId();

    public abstract LocalDateTime getCreateDate();

    public abstract LocalDateTime getModifyDate();

    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }
}