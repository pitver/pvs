package ru.vershinin.PVS.Utils;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Locale;

/**
 * getListOrg
 *
 * @author Вершинин Пётр
 */
public class getListOrg {
    public static void main(String[] args) {

        File file = new File("D:\\test\\t\\Отчёт по адресам.ods");
        File file1 = new File("D:\\test\\t\\Список организаций.ods");
        List generalList = ODSReader.readODSUnprocessed(file);
        List allOrg = ODSReader.readODSAllOrg(file1);
        try {
            // writeListUnprocessedToDB(generalList);
            //  writeListAllOrgToDB(allOrg);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // allOrg.stream().forEach(System.out::println);
        // generalList.stream().forEach(System.out::println);

    }

    private static void writeListUnprocessedToDB(List<List<String>> gE) throws SQLException {
        Connection conn = DBConnect.connect();
        List<String> listFIO = gE.get(0);
        List<String> listAdress = gE.get(1);
        List<String> lisrReceivingParty = gE.get(2);
        List<String> listTargetDate = gE.get(3);
        List<String> listPayer = gE.get(4);
        String sql = "insert into public.list_unprocessed (fio,adress,receiving_party,target_date,payer) "
                + "values ( ?, ?, ?, ?, ?)";

        System.out.println(gE.get(0).size());
        for (int i = 1; i < gE.get(0).size(); i++) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {

                st.setString(1, listFIO.get(i));
                st.setString(2, listAdress.get(i));
                st.setString(3, lisrReceivingParty.get(i).toUpperCase(Locale.ROOT));
                st.setTimestamp(4, Timestamp.valueOf(listTargetDate.get(i)));
                st.setString(5, listPayer.get(i));
                st.execute();
            }
        }
        DBConnect.connectClose();

    }

    private static void writeListAllOrgToDB(List<List<String>> listOrg) {
        Connection conn = DBConnect.connect();
        List<String> listNum = listOrg.get(0);
        List<String> listNameOrg = listOrg.get(1);
        List<String> lisrInn = listOrg.get(2);

        String sql = "insert into public.list_org (num,name_org,inn) "
                + "values ( ?, ?, ?)";


        for (int i = 1; i < listOrg.get(0).size(); i++) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {

                st.setString(1, listNum.get(i));
                st.setString(2, listNameOrg.get(i));
                st.setString(3, lisrInn.get(i));

                st.execute();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        DBConnect.connectClose();
    }

    public static void writeToDBProcessOrganization(String num, String nameOrg, String count, String inn, List<String> regAdress) throws SQLException {
        Connection conn = DBConnect.connect();

        String sql = "INSERT INTO public.processed_organizations" +
                "(inn, adress_migrate, count_pep, name_organization, number_organization)" +
                "VALUES (?, ?, ?, ?, ?);";



            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, inn);
                st.setString(2, String.valueOf(regAdress));
                st.setString(3, count);
                st.setString(4, nameOrg);
                st.setString(5, num);

                st.execute();
            }

            DBConnect.connectClose();


    }

}
