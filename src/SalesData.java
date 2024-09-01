import java.time.LocalDate;

public class SalesData {
	private LocalDate date;
	private int sales;

	public SalesData(LocalDate date, int sales) {
		this.date = date;
		this.sales = sales;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "SalesData [date=" + date + ", sales=" + sales + "]";
	}

}
