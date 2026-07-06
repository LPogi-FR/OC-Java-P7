package com.nnk.springboot.repositories;
import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating save(Rating rating);

    List<Rating> findAll();

    void deleteById(Integer id);

}
