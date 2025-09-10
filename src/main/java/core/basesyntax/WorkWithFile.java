package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int INITIAL_VALUE = 0;
    private static final int OPERATION_PART = 0;
    private static final int TRANSACTION_AMOUNT_PART = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = INITIAL_VALUE;
        int buy = INITIAL_VALUE;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String csvData;

            while ((csvData = bufferedReader.readLine()) != null) {
                String[] dataParts = csvData.split(",");
                String operation = dataParts[OPERATION_PART];
                int transactionAnount = Integer.parseInt(dataParts[TRANSACTION_AMOUNT_PART]);

                if (operation.equals("supply")) {
                    supply += transactionAnount;
                } else if (operation.equals("buy")) {
                    buy += transactionAnount;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + fromFileName);
        } catch (IOException e) {
            System.out.println("Can`t read file" + fromFileName);
        }

        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportBuilder.toString());
        } catch (IOException e) {
            System.out.println("Can`t write to file" + toFileName);
        }
    }
}
