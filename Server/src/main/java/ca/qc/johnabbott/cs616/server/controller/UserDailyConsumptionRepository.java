package ca.qc.johnabbott.cs616.server.controller;

import ca.qc.johnabbott.cs616.server.model.UserDailyConsumption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userdailyconsumption")
public interface UserDailyConsumptionRepository extends CrudRepository<UserDailyConsumption, Long> {
}
