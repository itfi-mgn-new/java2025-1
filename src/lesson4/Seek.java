package lesson4;

public class Seek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		seek(new RobotImpl());
		
	}

	public static void seek(final Robot r) {
		if (move2zeroZero(r)) {
			findGold(r);
		}
		take(r);
		move2zeroZero(r);
		put(r);
	}

	private static void put(Robot r) {
		rotate(r, Robot.NORTH);
		r.step();
		rotate(r, Robot.SOUTH);
		r.put();
	}

	private static void take(Robot r) {
		r.take();
	}

	private static void findGold(Robot r) {
		for (;;) {
			rotate(r, Robot.NORTH);
			gotoWall(r);
			if (r.see() == Robot.GOLD) {
				return;
			}
			rotate(r, Robot.EAST);
			if (r.see() != Robot.EMPTY) {
				return;
			}
			rotate(r, Robot.SOUTH);
			gotoWall(r);
			if (r.see() == Robot.GOLD) {
				return;
			}
			rotate(r, Robot.EAST);
			if (r.see() != Robot.EMPTY) {
				return;
			}
		}
	}

	private static boolean move2zeroZero(Robot r) {
		rotate(r, Robot.WEST);
		if (gotoWall(r)) {
			rotate(r, Robot.SOUTH);
			return gotoWall(r);
		}
		else {
			return false;
		}
	}

	private static boolean gotoWall(Robot r) {
		while(r.see() == Robot.EMPTY) {
			r.step();
		}
		return r.see() == Robot.WALL;
	}

	private static void rotate(final Robot r, final int direction) {
		while(r.getDirection() != direction) {
			r.left();
		}
	}
}

class RobotImpl extends Room implements Robot {
	private int	direction = NORTH;
	
	public RobotImpl() {
		super();
		placeRobot();
		placeGold();
	}

	public RobotImpl(int length, int width) {
		super(length, width);
	}

	@Override
	public int getDirection() {
		return direction;
	}

	@Override
	public int see() {
		if (getGoldX() == getSeeX() && getGoldY() == getSeeY()) {
			return GOLD;
		}
		else if (getSeeX() < 0 || getSeeX() >= getLength() || getSeeY() < 0 || getSeeY() >= getWidth()) {
			return WALL;
		}
		else {
			return EMPTY;
		}
	}

	@Override
	public void step() {
		int	x = getRobotX(), y = getRobotY();
		
		removeRobot();
		switch (getDirection()) {
			case NORTH :
				y++;
				break;
			case SOUTH :
				y--;
				break;
			case EAST :
				x--;
				break;
			case WEST :
				x++;
				break;
			default :
		}
		placeRobot(x, y);
	}

	@Override
	public void left() {
		switch (getDirection()) {
			case NORTH :
				direction = WEST;
				break;
			case SOUTH :
				direction = EAST;
				break;
			case EAST :
				direction = NORTH;
				break;
			case WEST :
				direction = SOUTH;
				break;
			default :
		}
	}

	@Override
	public void right() {
		switch (getDirection()) {
			case NORTH :
				direction = EAST;
				break;
			case SOUTH :
				direction = WEST;
				break;
			case EAST :
				direction = SOUTH;
				break;
			case WEST :
				direction = NORTH;
				break;
			default :
		}
	}

	@Override
	public void take() {
		System.err.println("Take "+getGoldX()+"/"+getGoldY());
		removeGold();
	}

	@Override
	public void put() {
		placeGold(getSeeX(), getSeeY());
		System.err.println("Put");
	}

	private int getSeeX() {
		int	x = getRobotX();
		
		switch (getDirection()) {
			case NORTH : case SOUTH :
				return x;
			case EAST :
				return x + 1;
			case WEST :
				return x - 1;
			default :
				return x;
		}
	}
	
	private int getSeeY() {
		int	y = getRobotY();
		
		switch (getDirection()) {
			case NORTH : 
				return y + 1;
			case SOUTH :
				return y - 1;
			case EAST : case WEST :
				return y;
			default :
				return y;
		}
	}
}
