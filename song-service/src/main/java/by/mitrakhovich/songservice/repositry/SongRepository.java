package by.mitrakhovich.songservice.repositry;

import by.mitrakhovich.songservice.repositry.entity.SongDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<SongDao, Integer> {
}
