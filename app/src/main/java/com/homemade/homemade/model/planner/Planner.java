package com.homemade.homemade.model.planner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 2/27/2018.
 */

public class Planner {

    private List<PlannerObject> plannerList;

    public Planner() {
        plannerList = new ArrayList<>();
    }

    // Add planner object to list
    public void addPlannerObject(PlannerObject plannerObject) {
        plannerList.add(plannerObject);
    }

    // Remove planner object by name
    public void removePlannerObject(String name) {

        // iterate through all planner objects, if matching name, remove (first instance)
        for (int i = 0; i < plannerList.size(); i++) {

            PlannerObject plannerObject = plannerList.get(i);
            if (plannerObject.getName().equals(name)) {
                plannerList.remove(i);
                return;
            }
        }
    }

    // Todo: remove planner object by date

    // Modify planner object's name by existing name
    public void modifyName(String existingName, String newName) {

        // iterate through all planner objects, if matching name, change name (first instance)
        for (int i = 0; i < plannerList.size(); i++) {

            PlannerObject plannerObject = plannerList.get(i);
            if (plannerObject.getName().equals(newName)) {

                plannerObject.setName(newName);
                plannerList.set(i, plannerObject);
                return;
            }
        }
    }

    // Todo: modify planner object's date
}
