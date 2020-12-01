package ca.qc.johnabbott.cs616.server.controller;

import ca.qc.johnabbott.cs616.server.model.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Note repo class
 *
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
@RepositoryRestResource(path = "note")
public interface NoteRepository extends CrudRepository<FoodItem, String> {
}
