package org.mdk.dragons;

import org.mdk.dragons.actors.Actor;
import org.mdk.dragons.actors.Body;
import org.mdk.dragons.actors.HumanBody;
import org.mdk.dragons.actors.RandomHumanStats;
import org.mdk.dragons.formations.Solo;
import org.mdk.dragons.simulation.Runner;

public class Main {

    public static void main(String[] args) {

        Runner simulation = new Runner();
        for(int idx = 0; idx < 2; idx++) {
            simulation.addFormation(new Solo(new Actor(new RandomHumanStats(), new HumanBody())));
        }
        // Create Weapons, Equipment, Behaviour
        simulation.run();
    }
}
