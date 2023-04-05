package de.adesso.thalheim.gtd.controller;

import de.adesso.thalheim.gtd.repository.ThoughtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("gtd/inbox")
@Slf4j
public class InboxController {

    @Inject
    private ThoughtRepository thoughtRepository;

    @PostMapping
    public void collect(@RequestBody String thought) {
        log.debug("Received " + thought);
        Thought theThought = new Thought(UUID.randomUUID(), thought);
        thoughtRepository.save(theThought);
    }

    @GetMapping
    public List<Thought> get() {
        Iterable<Thought> all = thoughtRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false).toList();
    }
}
