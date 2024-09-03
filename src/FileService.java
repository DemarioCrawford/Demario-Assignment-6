import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {
	
	public List<SalesData> readSalesData(String filename) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get(filename))) {

			return lines.skip(1).map(line -> {

				String[] parts = line.split(",");
				YearMonth date = YearMonth.parse(parts[0], DateTimeFormatter.ofPattern("MMM-yy"));
				Integer sales = Integer.parseInt(parts[1]);
				return new SalesData(date, sales);

			}).collect(Collectors.toList());
		}
		

	}
	//the first time I did the assignment I did not use the Optional method
	//NOTE TO SELF code line by line and make sure to understand the pseudo code
	public Optional<SalesData> findBestMonth(List<SalesData> salesData){
		return salesData.stream().max(Comparator.comparingInt(SalesData::getSales));
		
	}
	
	public Optional<SalesData> findWorstMonth(List<SalesData> salesData){
		return salesData.stream().min(Comparator.comparingInt(SalesData::getSales));
	}

}
