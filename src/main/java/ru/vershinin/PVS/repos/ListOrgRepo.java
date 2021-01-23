package ru.vershinin.PVS.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vershinin.PVS.model.ListOrg;

import java.util.List;

@Repository
public interface ListOrgRepo extends CrudRepository<ListOrg,Long> {
    List<ListOrg> findAll();
    ListOrgRepo findAllByNameOrg(String nameOrg);
    ListOrgRepo findAllByNum(String num);
    ListOrgRepo findAllByInn(String Inn);
}
