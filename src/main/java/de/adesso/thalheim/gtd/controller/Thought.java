package de.adesso.thalheim.gtd.controller;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Thought {

    @Id
    private UUID id;

    @Getter
    private String description;

}
