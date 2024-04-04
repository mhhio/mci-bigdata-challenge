package org.example.mcibigdatachallenge.controller;

import lombok.AllArgsConstructor;
import org.example.mcibigdatachallenge.model.BaseResponse;
import org.example.mcibigdatachallenge.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/pipeline")
public class PipelineController {
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/publish")
    public ResponseEntity<BaseResponse> publish(@RequestParam String source){
        kafkaProducerService.publish(source);
        return ResponseEntity.ok(BaseResponse.builder().status("success").build());
    }

}
