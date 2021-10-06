package com.rentmycar.rentmycar.repository;


import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByUser(User user);
}
