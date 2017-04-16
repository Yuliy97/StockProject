import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Yuli on 4/14/17.
 */
public class Main {
    private static int NUMSTOCK;
    double[][] expectedValue = new double[800][800];
    public static void main(String[] args) {
        try {
            //use absolute path
            System.out.println(2);
            File excel = new File("/Users/Yuli/Desktop/StockProject/src/DailyReturn.xlsx");
            System.out.println(2);
            InputStream fileInput = new FileInputStream(excel);

            System.out.println(2);


            XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
            System.out.println(2);

            XSSFSheet sheet = workbook.getSheetAt(1);
            System.out.println(2);
            int rowNum = sheet.getLastRowNum();
            System.out.println(2);
            int colNum = sheet.getRow(0).getLastCellNum();
            System.out.println(2);
            XSSFRow row;
            XSSFCell cell;
            System.out.println(2);
            for (int ro = 0; ro < 10; ro++) {
                row = sheet.getRow(ro);
//                for (int col = 1; col < 10; ro++) {
//                    cell = row.getCell(col);
//                    value[ro][col - 1] = Double.parseDouble(cell.getStringCellValue());
//                }
            }


        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
    //public void expectedValue()

}
