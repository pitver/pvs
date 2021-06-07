package ru.vershinin.PVS.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
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
public interface ListUnprocessedRepo extends PagingAndSortingRepository<ListUnprocessed, Long> {

    Page<ListUnprocessed> findAll(Pageable pageable);


    @Query(value = "SELECT * FROM public.list_unprocessed where receiving_party " +
            "LIKE '%' || :adress || '%'", nativeQuery = true)
    Page<ListUnprocessed> findByReceivingParty(@Param("adress") String adress, Pageable pageable);


    @Query(value = "SELECT count(*)FROM public.list_unprocessed where receiving_party " +
            "LIKE '%' || :adress || '%'", nativeQuery = true)
    String countAllByAdressLike(@Param("adress") String adress);

    @Query(value = "SELECT DISTINCT adress FROM public.list_unprocessed where receiving_party " +
            "LIKE '%' || :adress || '%' ", nativeQuery = true)
    List<String> distinctAllByAdressLike(@Param("adress") String adress);


    @Query(value = "SELECT distinct regexp_replace(receiving_party, '(.*)\\s.*','\\1')\n" +
            "\tFROM public.list_unprocessed ", nativeQuery = true)
    List<String> findListPreparedOrganizations();

    /*@Query(value = "SELECT adress,count(*) as adres_count" +
            " FROM public.list_unprocessed where receiving_party " +
            "LIKE '%' || :nameOrg || '%' group by adress;",nativeQuery = true)
    List<String> findMassReOrg(@Param("nameOrg" )String nameOrg); */

    @Query(value = "DO $$\n" +
            "\n" +
            "    DECLARE\n" +
            "        rec TEXT;\n" +
            "\n" +
            "    BEGIN\n" +
            "        FOR rec IN\n" +
            "            SELECT distinct regexp_replace(receiving_party, '(.*)\\s.*','\\1')\n" +
            "            FROM public.list_unprocessed\n" +
            "            LOOP\n" +
            "\n" +
            "                SELECT adress, count(*) as adres_count into rec\n" +
            "                FROM public.list_unprocessed\n" +
            "                where receiving_party\n" +
            "                Like '%' ||rec|| '%' group by adress;\n" +
            "\n" +
            "            END LOOP;\n" +
            "\n" +
            "    END\n" +
            "    $$;",nativeQuery = true)
    List<String> findMassReOrg();


}

