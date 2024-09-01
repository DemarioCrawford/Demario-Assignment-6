import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeslaSalesApplication {

	public static void main(String[] args) {
		// integrate the file service in main class
		FileService fileService = new FileService();
		try {
			// define the file paths for the csv file
			String model3File = "model3.csv";
			String modelSFile = "modelS.csv";
			String modelXFile = "modelx.csv";
			// read the files using the file service
			List<SalesData> model3Data = fileService.readSalesData(model3File);
			List<SalesData> modelSData = fileService.readSalesData(modelSFile);
			List<SalesData> modelXData = fileService.readSalesData(modelXFile);

			// print the results for each model
			printReport("Model 3", model3Data);
			printReport("Model S", modelSData);
			printReport("Model X", modelXData);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// this is the method created to print the results
	private static void printReport(String modelName, List<SalesData> salesData) {
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

		// calculate sales using streams
		Map<Integer, Integer> yearlySales = salesData.stream().collect(
				Collectors.groupingBy(data -> data.getDate().getYear(), Collectors.summingInt(SalesData::getSales)));
		
		//print sales report for the year
		System.out.println(modelName + " Yearly Sales Report");
		for (int year : Arrays.asList(2016, 2017, 2018, 2019)) {
			System.out.println(year + " -> " + yearlySales.getOrDefault(year, 0));
			
			
		}

	}

}
