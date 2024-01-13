package assignments.nameSurfer;/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private Set<NameSurferEntry> names;
    private HashMap<String, Color> colors;
    private NameSurferEntry currentEntry;


    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        setSize(NameSurferConstants.APPLICATION_WIDTH, NameSurferConstants.APPLICATION_HEIGHT);
        addComponentListener(this);
        names = new HashSet<>();
        colors = new HashMap<>();
    }

    public void setCurrentEntry(NameSurferEntry currentEntry) {
        this.currentEntry = currentEntry;
    }

    public NameSurferEntry getCurrentEntry() {
        return this.currentEntry;
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        names.clear();
        colors.clear();
        setCurrentEntry(null);
    }

    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        names.add(entry);
        colors.putIfAbsent(entry.getName(), RandomGenerator.getInstance().nextColor());
        setCurrentEntry(entry);
    }

    /* deletes entry from the list */
    public void deleteEntry(NameSurferEntry entry) {
        for(NameSurferEntry nameSurferEntry: names){
            if (nameSurferEntry.equals(entry)){
                names.remove(nameSurferEntry);
                break;
            }
        }
        colors.remove(entry.getName());
        if (entry.equals(currentEntry)) {
            currentEntry = null;
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();

        drawColumns();
        drawHeader();
        drawFooter();
        drawDiagrams();
        detailledInfo();
    }

    /* private methods */

    private void detailledInfo() {
        String entryName = "Name: ";

        for (int i = 1; i <= NDECADES; i++) {
            int year = 1900 + (i - 1) * 10;
            String rankOnYear = year + ": ";
            GLabel rankOnYearLabel = new GLabel(rankOnYear);
            add(rankOnYearLabel, getWidth() * 2 / 3.0, (i + 1) * getHeight() / (NDECADES + 2.0));
        }

        if (this.currentEntry != null) {
            entryName += this.currentEntry.getName();

            for (int i = 1; i <= NDECADES; i++) {
                int year = 1900 + (i - 1) * 10;
                String rankOnYear = "" + this.currentEntry.getRank(year);
                GLabel rankOnYearLabel = new GLabel(rankOnYear);
                add(rankOnYearLabel, (new GLabel(year + ":   ").getWidth()) + getWidth() * 2 / 3.0, (i + 1) * getHeight() / (NDECADES + 2.0));
            }
        }

        GLabel entryNameLabel = new GLabel(entryName);
        add(entryNameLabel, getWidth() * 2 / 3.0, getHeight() / (NDECADES + 2.0));
    }

    private void drawDiagrams() { // draws diagram for every person
        for (NameSurferEntry name : names) {
            GCompound individualDiagram = makeIndividualDiagram(name);
            add(individualDiagram);
        }
    }

    private GCompound makeIndividualDiagram(NameSurferEntry name) { // draws diagram for one specific person
        GCompound individualDiagram = new GCompound();
        double lastY = 0;
        double rankUnit = (getHeight() - 2.0 * GRAPH_MARGIN_SIZE) / MAX_RANK;
        for (int i = 0; i < NDECADES; i++) {
            int rank = name.getRank(START_DECADE + 10 * i);
            double currentY = rank != 0 ? GRAPH_MARGIN_SIZE + rankUnit * rank : getHeight() - GRAPH_MARGIN_SIZE;
            double x1Coordinate = (i - 1.0) * (getWidth() * 2 / 3.0) / NDECADES;
            double x2Coordinate = (1.0 * i * (getWidth() * 2 / 3.0)) / NDECADES;

            if (i != 0) {
                GLine statisticLine = new GLine(x1Coordinate, lastY, x2Coordinate, currentY);
                statisticLine.setColor(colors.get(name.getName()));
                individualDiagram.add(statisticLine);
            }

            lastY = currentY;

            String nameString = rank == 0 ? "*" : rank + "";
            nameString += name.getName();

            GLabel nameLabel = new GLabel(nameString, x2Coordinate, currentY);
            nameLabel.setColor(colors.get(name.getName()));
            individualDiagram.add(nameLabel);
        }
        return individualDiagram;
    }

    private void drawColumns() {// draw vertical lines
        for (int i = 0; i <= NameSurferConstants.NDECADES; i++) {
            int xCoordinate = i * (getWidth() * 2 / 3) / NameSurferConstants.NDECADES;
            add(new GLine(xCoordinate, 0, xCoordinate, getHeight()));
        }
    }

    private void drawFooter() {// show year division along with horizontal line
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, (getWidth() * 2 / 3.0), getHeight() - GRAPH_MARGIN_SIZE));

        for (int i = 0; i < NameSurferConstants.NDECADES; i++) {
            add(new GLabel(START_DECADE + i * 10 + "", (double) i * (getWidth() * 2 / 3.0) / NDECADES + 5, getHeight() - 4));
        }
    }

    private void drawHeader() {// horizontal upper line
        add(new GLine(0, GRAPH_MARGIN_SIZE, (getWidth() * 2 / 3.0), GRAPH_MARGIN_SIZE));
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
