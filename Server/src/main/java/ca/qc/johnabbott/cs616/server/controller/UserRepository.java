package ca.qc.johnabbott.cs616.server.controller;

import ca.qc.johnabbott.cs616.server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

@RepositoryRestResource(path = "user", excerptProjection = UserRepository.LimitedUser.class)
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(@Param(value = "username") String username);

    /**
     * Hides password from embedded views.
     */
    @Projection(name = "LimitedUser", types = { User.class })
    interface LimitedUser {
        String getId();
        String getUsername();
    }
}
