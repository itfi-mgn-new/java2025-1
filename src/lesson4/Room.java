package lesson4;

/**
 * <p>Этот класс реализует "поведение" коммнаты, в которой может находиться ровно один робот 
 * и ровно один мешок золота.</p>
 * @author achernomyrdin
 * @since  0.0.1
 */
public class Room {
	private final int	length;
	private final int	width;
	private int			xRobot = -1;
	private int			yRobot = -1;
	private int			xGold = -1;
	private int			yGold = -1;

	/**
	 * <p>Конструктор экземпляра класса. Создает комнату случайного размера не менее 10х10 клеток и не более 20х20 клеток.</p> 
	 */
	public Room() {
		this((int)(10+10*Math.random()), (int)(10+10*Math.random()));
	}

	/**
	 * <p>Конструктор экземпляра класса. Создает комнату заданного размера</p>
	 * @param length длина комнаты (по оси Х). Должна быть не менее 2.
	 * @param width ширина комнаты (по оси Y). Должна быть не менее 2.
	 */
	public Room(final int length, final int width) {
		this.length = length;
		this.width = width;
	}
	
	/**
	 * <p>Получить текущую длину комнаты</p>
	 * @return текущая длина комнаты
	 */
	public int getLength() {
		return length;
	}

	/**
	 * <p>Получить текущую ширину комнаты</p>
	 * @return текущая ширина комнаты
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * <p>Поместить робота в случайное место комнаты</p>
	 * @return true если размещение успешно, в противном случае - false
	 */
	public boolean placeRobot() {
		while (!placeRobot((int)(getLength()*Math.random()), (int)(getWidth()*Math.random()))) {
		}
		return true;
	}
	
	/**
	 * <p>Поместить робота в указанное место комнаты</p>
	 * @param xRobot местоположение робота по оси X. Должно вписываться в длину комнаты.
	 * @param yRobot местоположение робота по оси Y. Должно вписываться в ширину комнаты.
	 * @return true если размещение успешно, в противном случае - false
	 */
	public boolean placeRobot(final int xRobot, final int yRobot) {
		if (isRobotPlaced()) {
			return false;	// Robot already placed.
		}
		else if (xRobot < 0 || xRobot >= getLength() || yRobot < 0 || yRobot >= getWidth()) {
			return false;	// Robot location outside the room.
		}
		else if (xRobot == getGoldX() && yRobot == getGoldY()) {
			return false;	// Robot location is the same as the gold one.
		}
		else {
			this.xRobot = xRobot;
			this.yRobot = yRobot;
			return true;
		}
	}
	
	/**
	 * <p>Удалить робота из комнаты</p>
	 * @return true если удаление успешно, в противном случае - false
	 */
	public boolean removeRobot() {
		if (!isRobotPlaced()) {
			return false;	// Robot was not placed or was removed earlier
		}
		else {
			xRobot = -1;
			yRobot = -1;
			return true;
		}
	}

	/**
	 * <p>Размещен ли робот в комнате?</p>
	 * @return true если размещен, в противном случае - false 
	 */
	public boolean isRobotPlaced() {
		return !(getRobotX() == -1 && getRobotX() == -1);
	}
	
	/**
	 * <p>Получить X-координату робота</p>
	 * @return X-координата робота или -1, если робот не размещен.
	 */
	public int getRobotX() {
		return xRobot;
	}

	/**
	 * <p>Получить Y-координату робота</p>
	 * @return Y-координата робота или -1, если робот не размещен.
	 */
	public int getRobotY() {
		return yRobot;
	}
	
	/**
	 * <p>Поместить мешок золота в случайное место комнаты</p>
	 * @return true если размещение успешно, в противном случае - false
	 */
	public boolean placeGold() {
		while (!placeGold((int)(getLength()*Math.random()), (int)(getWidth()*Math.random()))) {
		}
		return true;
	}

	/**
	 * <p>Поместить мешок золота в указанное место комнаты</p>
	 * @param xGold местоположение мешка золота по оси X. Должно вписываться в длину комнаты.
	 * @param yGold местоположение мешка золота по оси Y. Должно вписываться в ширину комнаты.
	 * @return true если размещение успешно, в противном случае - false
	 */
	public boolean placeGold(final int xGold, final int yGold) {
		if (isGoldPlaced()) {
			return false;	// Gold already placed.
		}
		else if (xGold < 0 || xGold >= getLength() || yGold < 0 || yGold >= getWidth()) {
			return false;	// Gold location outside the room.
		}
		else if (xGold == getRobotX() && yGold == getRobotY()) {
			return false;	// Gold location is the same as the robot one.
		}
		else {
			this.xGold = xGold;
			this.yGold = yGold;
			return true;
		}
	}

	/**
	 * <p>Удалить мешок золота из комнаты</p>
	 * @return true если удаление успешно, в противном случае - false
	 */
	public boolean removeGold() {
		if (!isGoldPlaced()) {
			return false;	// Gold was not placed or was removed earlier
		}
		else {
			xGold = -1;
			yGold = -1;
			return true;
		}
	}

	/**
	 * <p>Размещен ли мешок золота в комнате?</p>
	 * @return true если размещен, в противном случае - false 
	 */
	public boolean isGoldPlaced() {
		return !(getGoldX() == -1 && getGoldY() == -1);
	}

	/**
	 * <p>Получить X-координату мешка золота </p>
	 * @return X-координата мешка золота или -1, если мешок золота не размещен.
	 */
	public int getGoldX() {
		return xGold;
	}

	/**
	 * <p>Получить Y-координату мешка золота </p>
	 * @return Y-координата мешка золота или -1, если мешок золота не размещен.
	 */
	public int getGoldY() {
		return yGold;
	}
}
