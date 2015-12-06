package model;

public class MarshIndexEntry {

	private String household;
	private String card1;
	private String card2;
	private String card3;
	
	public MarshIndexEntry() {
	}

	public MarshIndexEntry(String household, String card1, String card2,
			String card3) {
		this.household = household;
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
	}

	@Override
	public String toString() {
		return "MarshIndexEntry [household=" + household + ", card1=" + card1
				+ ", card2=" + card2 + ", card3=" + card3 + "]";
	}

	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public String getCard1() {
		return card1;
	}

	public void setCard1(String card1) {
		this.card1 = card1;
	}

	public String getCard2() {
		return card2;
	}

	public void setCard2(String card2) {
		this.card2 = card2;
	}

	public String getCard3() {
		return card3;
	}

	public void setCard3(String card3) {
		this.card3 = card3;
	}
	
	
}
