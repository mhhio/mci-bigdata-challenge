package org.example.mcibigdatachallenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.example.mcibigdatachallenge.model.BaseResponse;
import org.example.mcibigdatachallenge.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/pipeline")
public class PipelineController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    @Operation(summary = "Publish data to kafka", description = "Start publishing data from source to kafka broker. it will be converted from JSON to Protobuf",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = BaseResponse.class)))
            })
    public ResponseEntity<BaseResponse> publish(@RequestParam MultipartFile file){
        kafkaProducerService.publish(file);
        return ResponseEntity.ok(BaseResponse.builder().status("success").build());
    }

}
