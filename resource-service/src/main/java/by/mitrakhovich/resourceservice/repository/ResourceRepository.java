package by.mitrakhovich.resourceservice.repository;

import by.mitrakhovich.resourceservice.repository.entity.Mp3Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends CrudRepository<Mp3Resource, Integer> {
}
