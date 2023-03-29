package de.adesso.thalheim.gtd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gtd/inbox")
@Slf4j
public class InboxController {
    @PostMapping("/{thought}")
    public void collect(@PathVariable("thought") String thought) {
        // TODO: implement me!
        log.debug("Received " + thought);
    }

    @GetMapping
    public List<Thought> get() {
        return List.of(new Thought("Send Birthday Wishes to Mike"));
    }
}
