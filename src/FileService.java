import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
	//create the format using .ofpattern
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
	//create method that reads and parses data from csv file
	public List<SalesData> readSalesData(String filePath) throws FileNotFoundException, IOException{
		List<SalesData> salesDataList = new ArrayList<>();
		
		//open and read files
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
			String line;
			//skip header
			bufferedReader.readLine();
			
		//read lines and parse it
			while ((line = bufferedReader.readLine()) != null){
				//I was getting and exception at this point that I had to handle
				//one date came up as jun-17-30 and it threw me off
				//it continues to read even though the format was a bit off on one date
				String[] parts = line.split(", ");
				if (parts.length != 2) {
					
					continue;
				}
				LocalDate date = LocalDate.parse(parts[0], formatter);
				int sales = Integer.parseInt(parts[1]);
				salesDataList.add(new SalesData(date, sales));
			}
			
		}
		return salesDataList;
		
	}
	
	
}
