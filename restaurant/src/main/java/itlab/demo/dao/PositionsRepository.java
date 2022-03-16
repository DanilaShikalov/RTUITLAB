package itlab.demo.dao;

import itlab.demo.model.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionsRepository extends JpaRepository<PositionsEntity, Long> {
}
