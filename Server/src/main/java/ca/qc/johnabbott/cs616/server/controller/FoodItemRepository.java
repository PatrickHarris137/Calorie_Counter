package ca.qc.johnabbott.cs616.server.controller;

import ca.qc.johnabbott.cs616.server.model.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "fooditem")
public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {
}
