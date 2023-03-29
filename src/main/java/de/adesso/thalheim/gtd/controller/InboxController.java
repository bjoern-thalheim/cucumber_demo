package de.adesso.thalheim.gtd.controller;

import de.adesso.thalheim.gtd.repository.ThoughtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("gtd/inbox")
@Slf4j
public class InboxController {

    @Inject
    private ThoughtRepository thoughtRepository;

    @PostMapping("/{thought}")
    public void collect(@PathVariable("thought") String thought) throws UnsupportedEncodingException {
        log.debug("Received " + thought);
        Thought theThought = new Thought(UUID.randomUUID(), URLDecoder.decode(thought, StandardCharsets.UTF_8.name()));
        thoughtRepository.save(theThought);
    }

    @GetMapping
    public List<Thought> get() {
        Iterable<Thought> all = thoughtRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false).toList();
    }
}
