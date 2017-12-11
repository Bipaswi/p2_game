////https://stackoverflow.com/questions/12887921/shortest-path-in-a-2d-array-using-dijkstras-algorithm
////http://rosettacode.org/wiki/Maze_generation#Java
//import processing.core.PApplet;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//public class TrackGen {
//
//    final static int TRIED = 2;
//    //path will be the track switch off
//    final static int PATH = 3;
//
//    // @formatter:off
////    private int[][] GRID = {
////            { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 },
////            { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
////            { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 },
////            { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1 },
////            { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 },
////            { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
////            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
////            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
////            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
////            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
////            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
////            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
////            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
////    };
//    // @formatter:off
//
//    private int[][] grid;
//    private int height;
//    private int width;
//    public int worldSize;
//    public int size;
//    //public TrackWall[][] GRID = new TrackWall[worldSize][worldSize];
//    PApplet applet;
//    private int[][] map;
//    public ArrayList<Coord> coords = new ArrayList<>();
//    //public TrackWall[][] newTrack;
//    int Wallcount = 700;
//    int trackCount = 300;
//    //ArrayList<TrackWall> trackWalls = new ArrayList<>();
//
////    private final int x;
////    private final int y;
//    private final int[][] GRID = new int[worldSize][worldSize];
//
//    public TrackGen(int worldSize, int size) {
//        this.grid = GRID;
//        this.size = size;
//        this.worldSize = worldSize;
//        this.height = grid.length;
//        this.width = grid[0].length;
//        //this.newTrack = new TrackWall[height][width];
//        this.map = new int[height][width];
//    }
//
//    private void generateMaze(int cx, int cy) {
//        DIR[] dirs = DIR.values();
//        Collections.shuffle(Arrays.asList(dirs));
//        for (DIR dir : dirs) {
//            int nx = cx + dir.dx;
//            int ny = cy + dir.dy;
//            if (between(nx, x) && between(ny, y)
//                    && (GRID[nx][ny] == 0)) {
//                GRID[cx][cy] |= dir.bit;
//                GRID[nx][ny] |= dir.opposite.bit;
//                generateMaze(nx, ny);
//            }
//        }
//    }
//
//    private static boolean between(int v, int upper) {
//        return (v >= 0) && (v < upper);
//    }
//
//    private enum DIR {
//        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
//        private final int bit;
//        private final int dx;
//        private final int dy;
//        private DIR opposite;
//
//        // use the static initializer to resolve forward references
//        static {
//            N.opposite = S;
//            S.opposite = N;
//            E.opposite = W;
//            W.opposite = E;
//        }
//
//        DIR(int bit, int dx, int dy) {
//            this.bit = bit;
//            this.dx = dx;
//            this.dy = dy;
//        }
//    }
//
//    public boolean solve() {
//        return traverse(0,0);
//    }
//
//    private boolean traverse(int i, int j) {
//        if (!isValid(i,j)) {
//            return false;
//        }
//        if ( isEnd(i, j) ) {
//            map[i][j] = PATH;
//            coords.add(new Coord(i,j));
//            return true;
//        } else {
//            map[i][j] = TRIED;
//        }
//
//        // North
//        if (traverse(i - 1, j)) {
//            map[i-1][j] = PATH;
//            coords.add(new Coord(i-1,j));
//            return true;
//        }
//        // East
//        if (traverse(i, j + 1)) {
//            map[i][j + 1] = PATH;
//            coords.add(new Coord(i,j+1));
//            return true;
//        }
//        // South
//        if (traverse(i + 1, j)) {
//            map[i + 1][j] = PATH;
//            coords.add(new Coord(i+1,j));
//            return true;
//        }
//        // West
//        if (traverse(i, j - 1)) {
//            map[i][j - 1] = PATH;
//            coords.add(new Coord(i,j-1));
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean isEnd(int i, int j) {
//        return i == height - 1 && j == width - 1;
//    }
//
//    private boolean isValid(int i, int j) {
//        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean isOpen(int i, int j) {
//        return grid[i][j] == 1;
//    }
//
//    private boolean isTried(int i, int j) {
//        return map[i][j] == TRIED;
//    }
//
//    private boolean inRange(int i, int j) {
//        return inHeight(i) && inWidth(j);
//    }
//
//    private boolean inHeight(int i) {
//        return i >= 0 && i < height;
//    }
//
//    private boolean inWidth(int j) {
//        return j >= 0 && j < width;
//    }
//}
