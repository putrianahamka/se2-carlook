package org.bonn.se.model.objects.entities;

import org.bonn.se.model.dao.ContainerFahrzeugDAO;
import org.bonn.se.model.objects.dto.FahrzeugDTO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class ContainerFahrzeuge {
    private final List<FahrzeugDTO> liste;

    final private ContainerFahrzeugDAO containerFahrzeugDAO = ContainerFahrzeugDAO.getInstance();
    private static ContainerFahrzeuge instance;

    public static ContainerFahrzeuge getInstance(){
        if(instance == null){
            instance = new ContainerFahrzeuge();
        }
        return instance;
    }
    private ContainerFahrzeuge(){
        liste = new ArrayList<>();
    }
    public void setFahrzeug(Vertriebler vertriebler){
        try{
            containerFahrzeugDAO.setFahrzeug(vertriebler);
        }catch(DatabaseException throwables){
            throwables.getMessage();
        }
    }
}
