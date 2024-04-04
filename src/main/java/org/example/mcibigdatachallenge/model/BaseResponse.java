package org.example.mcibigdatachallenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BaseResponse {
    private final String status;
}
