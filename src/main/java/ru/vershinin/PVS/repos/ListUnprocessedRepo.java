package ru.vershinin.PVS.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vershinin.PVS.model.ListUnprocessed;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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


    /*@Query(value = "SELECT distinct regexp_replace(receiving_party, '(.*)\\s.*','\\1')\n" +
            "\tFROM public.list_unprocessed ", nativeQuery = true)*/
    @Query(value = "SELECT distinct receiving_party \n" +
            "\tFROM public.list_unprocessed ", nativeQuery = true)
    List<String> findListPreparedOrganizations();

    @Query(value = "SELECT adress,inn||'*',count(*)||'#' as adres_count" +
            " FROM public.list_unprocessed where receiving_party " +
            "LIKE '%' || :nameOrg || '%' AND inn ILIKE '%' || :inn || '%'group by adress,inn;",nativeQuery = true)
    List<String> findMassReOrg(String nameOrg,String inn);

    @Query(value = "SELECT adress,inn||'*',count(*)||'#' as adres_count" +
            " FROM public.list_unprocessed where receiving_party " +
            "LIKE '%' || :nameOrg || '%' group by adress,inn;",nativeQuery = true)
    List<String> findMassReOrg(String nameOrg);

    /*@Query(value =
            "DO $$\n" +
                    "\n" +
                    "    DECLARE\n" +
                    "        rec TEXT;\n" +
                    "\n" +
                    "    BEGIN\n" +
                    "        FOR rec IN\n" +
                    "            SELECT distinct regexp_replace(receiving_party, '(.*)\\s.*','\\1')\n" +
                    "            FROM public.list_unprocessed\n" +
                    "            LOOP\n" +
                    "Insert Into public.mas_reg(name_org,adress,adres_count)\n" +
                    "tSELECT distinct regexp_replace(receiving_party, '(.*)\\s.*','\\1'),adress, count(*) as adres_count\n" +
                    "                FROM public.list_unprocessed\n" +
                    "                where receiving_party\n" +
                    "                Like '%' ||rec|| '%' group by receiving_party,adress;\n" +
                    "            ENDLOOP;\n" +
                    "\n" +
                    "    END\n" +
                    "    $$;",nativeQuery = true)
    List<String> findMassReOrg();*/


}

