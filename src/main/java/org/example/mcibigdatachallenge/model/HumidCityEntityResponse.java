package org.example.mcibigdatachallenge.model;

import lombok.AllArgsConstructor;
import org.example.mcibigdatachallenge.dto.HumidCity;

import java.util.List;

/**
 * Just for Swagger API documentation
 */
public class HumidCityEntityResponse extends EntityResponse<List<HumidCity>> {

    public HumidCityEntityResponse(List<HumidCity> entity, String status) {
        super(entity, status);
    }
}
