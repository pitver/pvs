package ru.vershinin.PVS.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vershinin.PVS.model.ListOrg;

import java.util.List;

@Repository
public interface ListOrgRepo extends CrudRepository<ListOrg,Long> {
    List<ListOrg> findAll();

    @Query(value="select name_org from list_org",nativeQuery = true)
    List<String>findAllNameOrg();









}
