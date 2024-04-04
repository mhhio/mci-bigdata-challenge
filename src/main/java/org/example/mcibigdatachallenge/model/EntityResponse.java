package org.example.mcibigdatachallenge.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EntityResponse<T> extends BaseResponse {
    private final T entity;


    @Builder(builderMethodName = "entityResponseBuilder")
    public EntityResponse(T entity,String status) {
        super(status);
        this.entity = entity;
    }
}
