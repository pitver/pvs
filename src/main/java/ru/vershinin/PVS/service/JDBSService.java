package ru.vershinin.PVS.service;

import java.util.List;

/**
 * JDBSService
 *
 * @author Вершинин Пётр
 */
public interface JDBSService {

    int writeListUnprocessedToDB(List<List<String>> gE);
    int writeListAllOrgToDB(List<List<String>> listOrg);
    int writeToDBProcessOrganization(String num, String nameOrg, String count, String inn, List<String> regAdress);
}
