package edu.unc.cs.heelp_ai.repository;

import edu.unc.cs.heelp_ai.model.HeelpData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeelpDataRepository extends JpaRepository<HeelpData, Long> {
}
