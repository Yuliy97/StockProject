import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        File excel =  new File ("/Users/Yuli/Desktop/StockProject/src/DailyReturn.xlsx");
        System.out.println(2);
        FileInputStream fis = new FileInputStream(excel);
        System.out.println(2);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheetAt(1);
        System.out.println(2);
        int rowNum = ws.getLastRowNum() + 1;
        int colNum = ws.getRow(0).getLastCellNum();
        String [][] data = new String [rowNum] [colNum];

        for(int i = 0; i <rowNum; i++){
            XSSFRow row = ws.getRow(i);
            for (int j = 0; j < colNum; j++){
                XSSFCell cell = row.getCell(j);
                String value = cell.toString();
                data[i][j] = value;
                System.out.println ("the value is " + value);
            }
        }

    }
}