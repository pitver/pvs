package ru.vershinin.PVS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vershinin.PVS.model.ProcessedOrganizations;

import java.util.List;

/**
 * ProcessOrganizationsRepo
 *
 * @author Вершинин Пётр
 */
@Repository
public interface ProcessOrganizationsRepo extends CrudRepository<ProcessedOrganizations,Long> {

    List<ProcessedOrganizations> findAll();
}
