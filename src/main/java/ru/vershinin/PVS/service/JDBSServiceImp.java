package ru.vershinin.PVS.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.vershinin.PVS.Utils.ODSReader;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

/**
 * JDBSServiceImp
 *
 * @author Вершинин Пётр
 */
@Service
public class JDBSServiceImp implements JDBSService {

    private final JdbcTemplate jdbcTemplate;

    public JDBSServiceImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void writeDBOfType(int type) {

        File fReportOrg = new File("D:\\test\\t\\Отчёт по адресам.ods");
        File fListOrg = new File("D:\\test\\t\\Список организаций.ods");

        List generalList = ODSReader.readODSUnprocessed(fReportOrg);
        List allOrg = ODSReader.readODSAllOrg(fListOrg);
        try {
            if(type==1){
                writeListAllOrgToDB(allOrg);
            }else if(type==2){
                writeListUnprocessedToDB(generalList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public int writeListUnprocessedToDB(List<List<String>> gE) {
        List<String> listFIO = gE.get(0);
        List<String> listAdress = gE.get(1);
        List<String> lisrReceivingParty = gE.get(2);
        List<String> listTargetDate = gE.get(3);
        List<String> listPayer = gE.get(4);

        for (int i = 1; i < gE.get(0).size(); i++) {

            return jdbcTemplate.update("insert into public.list_unprocessed (fio,adress,receiving_party,target_date,payer) "
                            + "values ( ?, ?, ?, ?, ?)",
                    listFIO.get(i),
                    listAdress.get(i),
                    lisrReceivingParty.get(i).toUpperCase(Locale.ROOT),
                    Timestamp.valueOf(listTargetDate.get(i)),
                    listPayer.get(i));


        }
        return 0;
    }

    @Override
    public int writeListAllOrgToDB(List<List<String>> listOrg) {
        List<String> listNum = listOrg.get(0);
        List<String> listNameOrg = listOrg.get(1);
        List<String> lisrInn = listOrg.get(2);

        for (int i = 1; i < listOrg.get(0).size(); i++) {

            return jdbcTemplate.update("insert into public.list_org (num,name_org,inn) "
                            + "values ( ?, ?, ?)",
                    listNum.get(i),
                    listNameOrg.get(i),
                    lisrInn.get(i));
        }
        return 0;
    }

    @Override
    public int writeToDBProcessOrganization(String num, String nameOrg, String count, String inn, List<String> regAdress) {

        StringBuilder sb = new StringBuilder();
        for (String s : regAdress) {
            sb.append(s + "@");
        }
        return jdbcTemplate.update("INSERT INTO public.processed_organizations" +
                        "(inn, adress_migrate, count_pep, name_organization, number_organization)" +
                        "VALUES (?, ?, ?, ?, ?)",
                inn, sb.toString(), count, nameOrg, num);
    }

}
