package de.adesso.thalheim.gtd.repository;


import de.adesso.thalheim.gtd.controller.Thought;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ThoughtRepository extends CrudRepository<Thought, UUID> {
}
