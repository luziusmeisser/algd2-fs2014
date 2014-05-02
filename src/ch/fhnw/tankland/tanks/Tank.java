package ch.fhnw.tankland.tanks;

import java.awt.Graphics2D;
import java.util.HashMap;

import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.geometry.Positionable;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;

public final class Tank extends Positionable {

	private int color;
	private int speed;
	private IStrategy strat;
	private EOrientation orientation;
	private TankAction current;

	private HashMap<Position, Node> lastScan;

	private static final Sprite SPRITE = new Sprite();

	public Tank(IStrategy strat, Position position) {
		super(position);
		this.strat = strat;
		this.orientation = EOrientation.WEST;
		this.color = strat.getColor() % 8;
		this.speed = 100;
	}

	public boolean performStep(Land land) {
		if (isDestroyed()) {
			return false;
		} else {
			if (current == null) {
				Situation s = new Situation(land, lastScan, getPosition().copy(), getOrientation(), speed);
				ETankAction action = strat.getNextAction(s);
				current = new TankAction(action, action.calcWork(s));
			}
			current.work();
			if (current.isComplete()) {
				perform(land, current.getAction());
				current = null;
			}
			return true;
		}
	}

	private void perform(Land land, ETankAction action) {
		switch (action) {
		default:
		case PAUSE:
			return;
		case LEFT:
			orientation = orientation.left();
			break;
		case RIGHT:
			orientation = orientation.right();
			break;
		case FORWARD:
			forward(land);
			break;
		case SCAN:
			lastScan = new HashMap<>();
			new Node(lastScan, land, getPosition().copy());
			break;
		}
	}

	private void forward(Land land) {
		Position old = getPosition();
		land.getFieldAt(old).notifyTankLeft(this);
		old.move(orientation);
		land.getFieldAt(old).notifyTankEntered(this);
	}

	public EOrientation getOrientation() {
		return orientation;
	}

	public void draw(Graphics2D g) {
		Position pos = getPosition();
		int s = pos.getTileSize();
		int x = pos.getPixelPositionX();
		int y = pos.getPixelPositionY();
		int sx = SPRITE.getX(color, orientation);
		int sy = SPRITE.getY(color, orientation);
		g.drawImage(SPRITE.getImg(orientation), x, y, x + s, y + s, sx, sy, sx + Sprite.TANK_SIZE, sy + Sprite.TANK_SIZE, null);
	}

	public void increaseTime(int amount) {
		this.speed += amount;
		System.out.println(strat.getName() + " has now speed " + speed);
	}

	public void destroy() {
		this.speed = 0;
	}

	public boolean isDestroyed() {
		return speed == 0;
	}

	public String getName() {
		return strat.getName();
	}

}
