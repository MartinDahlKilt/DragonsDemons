package org.mdk.dragons;

import org.mdk.dragons.simulation.Combat;
import org.mdk.dragons.world.XmlParser;

public class Main {

    public static void main(String[] args) {

        /*Duel duel = new Duel();
        duel.registerActor("A", new SimpleHuman());
        duel.registerActor("B", new SimpleHuman());
        duel.run(); */
        XmlParser p = new XmlParser();
        Combat c = p.parse("engagement.xml");
        if(c!=null) {
            c.run();
        }
    }
}
