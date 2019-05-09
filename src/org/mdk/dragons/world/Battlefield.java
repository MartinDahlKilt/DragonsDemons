package org.mdk.dragons.world;

import javafx.geometry.Pos;
import org.mdk.dragons.actors.Actor;

import java.util.List;

public interface Battlefield {
    /* List of all friends on the battlefield */
    List<Actor> getFriendsOf(Actor actor);

    /* List of all foes on the battlefield */
    List<Actor> getFoesOf(Actor actor);

    /* Get the distance in meters between two actors */
    int getDistance(Actor first, Actor second);

    Position getPositionOf(Actor a);

}
