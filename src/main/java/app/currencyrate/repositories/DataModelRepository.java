package app.currencyrate.repositories;

import app.currencyrate.domain.entities.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DataModelRepository extends JpaRepository<DataModel, String> {

    @Query("SELECT dm " +
            "FROM app.currencyrate.domain.entities.DataModel dm " +
            "WHERE dm.base " +
            "LIKE :base " +
            "AND dm.date " +
            "LIKE :date")
    Optional<DataModel> findByBaseAndDate(@Param("base") String base,@Param("date") String date);
}
