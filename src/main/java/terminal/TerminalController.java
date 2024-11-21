package terminal;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TerminalController {
    @GetMapping("")
    public String index(Model model) {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        model.addAttribute("names", names);
        return "index"; // Corresponding Thymeleaf template is names.html
    }

    @GetMapping("/displayCsv")
    public String displayCsv(Model model) throws Exception {
        // Path to the CSV file
        Path csvFilePath = Path.of("file.csv");

        // Read and parse the CSV file
        List<String> headers = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        try (Reader reader = new FileReader(csvFilePath.toFile())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            // Extract headers
            headers.addAll(records.iterator().next().toMap().keySet());

            // Extract rows
            for (CSVRecord record : records) {
                List<String> row = new ArrayList<>();
                for (String header : headers) {
                    row.add(record.get(header));
                }
                rows.add(row);
            }
        }

        // Add data to the model
        model.addAttribute("headers", headers);
        model.addAttribute("rows", rows);

        return "csvTable";
    }
}

