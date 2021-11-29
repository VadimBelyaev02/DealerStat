package com.leverx.dealerstat.repository;

import com.leverx.dealerstat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

   // @Query(nativeQuery = true,
   // value = "SELECT t, sum(rate) FROM dealer_stat.public.users as t" +
   //         "")
   // Map<User, Double> findTopByRating();
}
