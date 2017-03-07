package helpers;

public class Triplet<P, S, D> {
	private final P piece;
	private final S stick;
	private final D destination;

	public Triplet(P piece, S stick, D destination) {
		this.piece = piece;
		this.stick = stick;
		this.destination = destination;
	}

	public int getPiece() {
		return (int) this.piece;
	}

	public int getStick() {
		return (int) this.stick;
	}

	public int getDestination() {
		return (int) this.destination;
	}

	public void printTriplet() {
		System.out.println(this.piece + ", " + this.stick + ", " + this.destination);
	}
}
