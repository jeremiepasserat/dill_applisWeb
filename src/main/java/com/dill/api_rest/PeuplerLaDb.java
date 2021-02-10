package com.dill.api_rest;

import com.dill.api_rest.service.ServicesImpl;
import com.dill.api_rest.service.Services;

public class PeuplerLaDb {

    Services service = new ServicesImpl();


    PeuplerLaDb(){

        if (service != null) {
            service.newBadge(1, "Premiere visite", "images/badge1");
            System.out.println("Je le ai ajouté un badge hihihi");
        }
        else
        {
            System.out.println("Le service est null");
        }

    }

}