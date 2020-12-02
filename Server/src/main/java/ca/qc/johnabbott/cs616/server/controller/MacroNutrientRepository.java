package ca.qc.johnabbott.cs616.server.controller;

import ca.qc.johnabbott.cs616.server.model.MacroNutrient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "macronutrient")
public interface MacroNutrientRepository extends CrudRepository<MacroNutrient, Long> {
}
