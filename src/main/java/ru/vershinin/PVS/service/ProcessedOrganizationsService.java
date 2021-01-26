package ru.vershinin.PVS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vershinin.PVS.model.ProcessedOrganizations;
import ru.vershinin.PVS.repos.ProcessOrganizationsRepo;

import java.util.List;

/**
 * ProcessedOrganizationsService
 *
 * @author Вершинин Пётр
 */
@Service
public class ProcessedOrganizationsService {

   private final ProcessOrganizationsRepo processOrganizationsRepo;

    public ProcessedOrganizationsService(ProcessOrganizationsRepo processOrganizationsRepo) {
        this.processOrganizationsRepo = processOrganizationsRepo;
    }

    public List<ProcessedOrganizations>findAll(){return processOrganizationsRepo.findAll();}
}
