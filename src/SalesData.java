import java.time.YearMonth;
	//testing to see if changes are being made in this class
public class SalesData {
	YearMonth date;
	Integer sales;
	
	public SalesData(YearMonth date, Integer sales) {
		this.date = date;
		this.sales = sales;
	}
	
	public YearMonth getDate() {
		return date;
	}
	public void setDate(YearMonth date) {
		this.date = date;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "SalesData [date=" + date + ", sales=" + sales + "]";
	}
	//testing
	
}
