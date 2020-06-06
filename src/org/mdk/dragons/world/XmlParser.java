package org.mdk.dragons.world;

import net.sf.saxon.s9api.*;
import org.mdk.dragons.Dice;
import org.mdk.dragons.actors.Actor;
import org.mdk.dragons.actors.Stats;
import org.mdk.dragons.simulation.Combat;
import javax.xml.transform.stream.StreamSource;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private XPathCompiler mXpathCompiler;



    public Combat parse(String file) {
        Combat c = null;

        try {
            Processor processor = new Processor(false);
            processor.getUnderlyingConfiguration().setXIncludeAware(true);
            mXpathCompiler = processor.newXPathCompiler();

            XdmNode xdm = processor.newDocumentBuilder().build(new StreamSource(new FileReader(file)));
            for(String t : getTeams(xdm)) {
                List<Actor> actors = getTeamActors(xdm,t);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return c;
    }


    private List<String> getTeams(XdmNode node) {
        List<String> names = new ArrayList<>();
        try {
            String expression = "/engagement/teams/team/name[text()]";
            XPathSelector ex = mXpathCompiler.compile(expression).load();
            ex.setContextItem(node);

            for (XdmItem n : ex) {
                names.add(n.getStringValue());
            }
        } catch (SaxonApiException e) {
            e.printStackTrace();
        }
        return names;
    }

    private Stats getStats(XdmItem node) throws SaxonApiException {
        Stats s = new Stats();
        for(Stats.Type t : Stats.Type.values()) {
            String expression = "stats/stat[lower-case(name)='"+t.name().toLowerCase()+"']/value";
            XPathSelector valueSelector = mXpathCompiler.compile(expression).load();
            valueSelector.setContextItem(node);
            String val = valueSelector.evaluateSingle().getStringValue();
            if(val.contains("d")) {
                s.set(t, Dice.roll(val));
            } else {
                s.set(t, Integer.parseInt(val));
            }
        }
        return s;
    }

    private List<Actor> getTeamActors(XdmNode node, String team) throws SaxonApiException {
        List<Actor> actors = new ArrayList<>();
        String expression = "/engagement/teams/team[name='"+team+"']/actors/actor";
        XPathSelector actorSelector = mXpathCompiler.compile(expression).load();
        actorSelector.setContextItem(node);

        for (XdmItem n : actorSelector) {
            Class[] classes = {};
            Object[] objects = {};

            Actor a = createInstance(getClassString(n, "org.mdk.dragons.actors"), classes, objects);

            XPathSelector nameSelector = mXpathCompiler.compile("name").load();
            nameSelector.setContextItem(n);
            a.setName(nameSelector.evaluateSingle().getStringValue());
            a.setStats(getStats(n));
            // Get SKills
            // Get Items
            actors.add(a);
        }
        return actors;
    }

    private String getClassString(XdmItem node, String prefix) throws SaxonApiException {
            XPathSelector ex = mXpathCompiler.compile("class[text()]").load();
            ex.setContextItem(node);
            return prefix + "." + ex.evaluateSingle().getStringValue();
    }

    /*private void DumpDocument(Node node) {
        // do something with the current node instead of System.out
        System.out.println(node.getNodeName() + " " + node.hasChildNodes());

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                //calls this method for all the children which is Element
                DumpDocument(currentNode);
            } else if (currentNode.getNodeType() == Node.TEXT_NODE) {
                String t = currentNode.getNodeValue().trim();
                if(!t.isEmpty()) {
                    System.out.println(t);
                }
            }
        }
    }*/

    public <T> T createInstance(final String className, Class[] inputTypes, Object[] inputValues){
        try{
            Class type = Class.forName(className);
            Constructor con = type.getDeclaredConstructor(inputTypes);
            return (T)con.newInstance(inputValues);
        } catch(NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e){
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
