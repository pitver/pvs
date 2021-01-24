package ru.vershinin.PVS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query(value="SELECT count(*)FROM public.list_unprocessed where receiving_party LIKE '%' || :adress || '%'",nativeQuery = true)
    String countAllByAdressLike( @Param("adress")String adress);

    @Query(value ="SELECT DISTINCT adress FROM public.list_unprocessed where receiving_party LIKE '%' || :adress || '%' ",nativeQuery = true )
    List<String> distinctAllByAdressLike(@Param("adress")String adress);
}

