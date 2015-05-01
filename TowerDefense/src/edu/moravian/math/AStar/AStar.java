
package edu.moravian.math.AStar;


import java.util.ArrayList;
import java.util.Collections;

import edu.moravian.math.heuristic.AStarHeuristic;
import edu.moravian.math.AStar.utils.Logger;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;


public class AStar {
        private AreaMap map;
        private AStarHeuristic heuristic;
        private double startX;
        private double startY;
        private double goalX;
        private double goalY;
        /**
         * closedList The list of Nodes not searched yet, sorted by their distance to the goal as guessed by our heuristic.
         */
        private ArrayList<Node> closedList;
        private SortedNodeList openList;
        public Path shortestPath;
        Logger log = new Logger();

        public AStar(AreaMap map, AStarHeuristic heuristic) {
                this.map = map;
                this.heuristic = heuristic;

                closedList = new ArrayList<Node>();
                openList = new SortedNodeList();
        }

        public Path calcShortestPath(double startX, double startY, double goalX, double goalY) {
                this.startX = startX;
                this.startY = startY;
                this.goalX = goalX;
                this.goalY = goalY;

                //mark start and goal node
                map.setTowerLocation((int)startX, (int)startY);
                map.setGoalLocation((int)goalX, (int)goalY);

                //Check if the goal node is blocked (if it is, it is impossible to find a path there)
                if (map.getNode((int)goalX, (int)goalY).isObstacle) {
                        return null;
                }

                map.getStartNode().setDistanceFromStart(0);
                closedList.clear();
                openList.clear();
                openList.add(map.getStartNode());

                //while we haven't reached the goal yet
                while(openList.size() != 0) {

                        //get the first Node from non-searched Node list, sorted by lowest distance from our goal as guessed by our heuristic
                        Node current = openList.getFirst();

                        // check if our current Node location is the goal Node. If it is, we are done.
                        if(current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY()) {
                                return reconstructPath(current);
                        }

                        //move current Node to the closed (already searched) list
                        openList.remove(current);
                        closedList.add(current);

                        //go through all the current Nodes neighbors and calculate if one should be our next step
                        for(Node neighbor : current.getNeighborList()) {
                                boolean neighborIsBetter;

                                //if we have already searched this Node, don't bother and continue to the next one 
                                if (closedList.contains(neighbor))
                                        continue;

                                //also just continue if the neighbor is an obstacle
                                if (!neighbor.isObstacle) {

                                        // calculate how long the path is if we choose this neighbor as the next step in the path 
                                        float neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor));

                                        //add neighbor to the open list if it is not there
                                        if(!openList.contains(neighbor)) {
                                                openList.add(neighbor);
                                                neighborIsBetter = true;
                                                //if neighbor is closer to start it could also be better
                                        } else if(neighborDistanceFromStart < current.getDistanceFromStart()) {
                                                neighborIsBetter = true;
                                        } else {
                                                neighborIsBetter = false;
                                        }
                                        // set neighbors parameters if it is better
                                        if (neighborIsBetter) {
                                                neighbor.setPreviousNode(current);
                                                neighbor.setDistanceFromStart(neighborDistanceFromStart);
                                                neighbor.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));
                                        }
                                }

                        }

                }
                return null;
        }

        
        
        public void printPath() {
                Node node;
                for(int x=0; x<map.getMapWith(); x++) {

                        if (x==0) {
                                for (int i=0; i<=map.getMapWith(); i++)
                                        System.out.print("-");
                                System.out.println();   
                        }
                        System.out.print("|");

                        for(int y=0; y<map.getMapHeight(); y++) {
                                node = map.getNode(x, y);
                                if (node.isObstacle) {
                                        System.out.print("X");
                                } else if (node.isStart) {
                                        System.out.print("s");
                                } else if (node.isGoal) {
                                        System.out.print("g");
                                } //else if (shortestPath.contains(node.getX(), node.getY())) {
                                        //System.out.print("+");
                                 else {
                                        System.out.print(" ");
                                }
                                if (y==map.getMapHeight())
                                        System.out.print("_");
                        }

                        System.out.print("|");
                        System.out.println();
                }
                for (int i=0; i<=map.getMapWith(); i++)
                        System.out.print("-");
        }

        private Path reconstructPath(Node node) {
                 Path path = new Path();
                while(!(node.getPreviousNode() == null)) {
                        path.prependWayPoint(node);
                        node = node.getPreviousNode();
                }
                this.shortestPath = path;
                return path;
        }

        private class SortedNodeList {

                private ArrayList<Node> list = new ArrayList<Node>();

                public Node getFirst() {
                        return list.get(0);
                }

                public void clear() {
                        list.clear();
                }

                public void add(Node node) {
                        list.add(node);
                        Collections.sort(list);
                }

                public void remove(Node n) {
                        list.remove(n);
                }

                public int size() {
                        return list.size();
                }

                public boolean contains(Node n) {
                        return list.contains(n);
                }
        }

}