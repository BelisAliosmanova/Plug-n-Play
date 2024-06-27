package com.example.plug_n_play.controllers;

import com.example.plug_n_play.services.crawler.CrawlerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/crawlers")
public class CrawlerController {

    private final CrawlerService crawlerService;

    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @Async
    @GetMapping("/start")
    public CompletableFuture<ResponseEntity<Object>> start() {
        crawlerService.run();

        var response = ResponseEntity.status(HttpStatus.ACCEPTED).build();
        return CompletableFuture.completedFuture(response);
    }

    @Async
    @GetMapping("/stop")
    public CompletableFuture<ResponseEntity<Object>> stop() {
        crawlerService.shutdown();

        var response = ResponseEntity.status(HttpStatus.ACCEPTED).build();
        return CompletableFuture.completedFuture(response);
    }
}
