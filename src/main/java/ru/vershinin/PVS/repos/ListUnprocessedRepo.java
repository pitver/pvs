package ru.vershinin.PVS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vershinin.PVS.model.ListUnprocessed;

import java.util.List;

/**
 * ListUnprocessedRepo
 *
 * @author Вершинин Пётр
 */
@Repository
public interface ListUnprocessedRepo  extends CrudRepository<ListUnprocessed,Long> {

    List<ListUnprocessed> findAll();

}
