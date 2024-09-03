import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeslaSalesApplication {

	public static void main(String[] args) {
		// integrate the file service in main class
		FileService fileService = new FileService();

		// this is where I usually go wrong and cannot read the files properly...

		try {
			// read the files
			List<SalesData> model3Data = fileService.readSalesData("model3.csv");
			List<SalesData> modelSData = fileService.readSalesData("modelS.csv");
			List<SalesData> modelXData = fileService.readSalesData("modelX.csv");

			// print report
			printReport("Model 3", model3Data);
			printReport("Model S", modelSData);
			printReport("Model X", modelXData);
			// Here I had to handle my own IOException
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// this method prints the results with the sum of the sales
	private static void printReport(String modelName, List<SalesData> salesData) {
		System.out.println(modelName + " Yearly Sales Report");
		salesData.stream()
				.collect(Collectors.groupingBy(report -> report.getDate().getYear(),
						Collectors.summingInt(SalesData::getSales)))
				.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

		// find and print best and worst months
		Optional<SalesData> bestMonth = findBestMonth(salesData);
		Optional<SalesData> worstMonth = findWorstMonth(salesData);

		System.out.println("The best month for " + modelName + " was: "
				+ bestMonth.map(report -> report.getDate().toString()).orElse("N/A"));
		System.out.println("The worst mothn for " + modelName + " was: "
				+ worstMonth.map(report -> report.getDate().toString()).orElse("N/A"));
		System.out.println("--------------------");
	}

	private static Optional<SalesData> findWorstMonth(List<SalesData> salesData) {

		return salesData.stream().max(Comparator.comparingInt(SalesData::getSales));
	}

	private static Optional<SalesData> findBestMonth(List<SalesData> salesData) {

		return salesData.stream().min(Comparator.comparingInt(SalesData::getSales));
	}

}
