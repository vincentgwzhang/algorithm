package interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Fupan {

    static List<Direction> leftDirections = new ArrayList<>();
    static List<Direction> rightDirections = new ArrayList<>();

    static {
        leftDirections.add(Direction.EAST);
        leftDirections.add(Direction.NORTH);
        leftDirections.add(Direction.WEST);
        leftDirections.add(Direction.SOUTH);
        leftDirections.add(Direction.EAST);

        rightDirections.add(Direction.EAST);
        rightDirections.add(Direction.SOUTH);
        rightDirections.add(Direction.WEST);
        rightDirections.add(Direction.NORTH);
        rightDirections.add(Direction.EAST);
    }

    private void go(Coordinator coordinator, String instructions) {
        char[] instrument = instructions.toCharArray();

        for (int index = 0; index < instrument.length; index++) {
            convert(coordinator, instrument[index]);
        }
    }

    private void convert(Coordinator coordinator, char instrument) {
        if (instrument == 'F' || instrument == 'B') {
            if (coordinator.getDirection() == Direction.EAST || coordinator.getDirection() == Direction.WEST) {
                int metric = instrument == 'F' ? 1 : -1;
                int metric2 = coordinator.getDirection() == Direction.EAST ? 1 : -1;
                int result = metric * metric2;
                coordinator.setX(coordinator.getX() + result);
            } else {
                int metric = instrument == 'F' ? 1 : -1;
                int metric2 = coordinator.getDirection() == Direction.NORTH ? 1 : -1;
                int result = metric * metric2;
                coordinator.setY(coordinator.getY() + result);
            }
        } else {
            Direction current = coordinator.getDirection();
            if (instrument == 'L') {
                current = leftDirections.get(leftDirections.indexOf(current) + 1);
            } else {
                current = rightDirections.get(rightDirections.indexOf(current) + 1);
            }
            coordinator.setDirection(current);
        }
    }

    @Test
    public void test1() {
        Coordinator coordinator = new Coordinator();
        coordinator.setX(4);
        coordinator.setY(2);
        coordinator.setDirection(Direction.EAST);

        //go(coordinator, "FL");
        go(coordinator, "FLFFFRFLB");

        System.out.println(coordinator.toString());
    }

    public static class Coordinator {
        private int x;
        private int y;
        private Direction direction;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Coordinator.class.getSimpleName() + "[", "]")
                    .add("x=" + x)
                    .add("y=" + y)
                    .add("direction=" + direction)
                    .toString();
        }
    }

    public static enum Direction {
        EAST, WEST, NORTH, SOUTH
    }

}
