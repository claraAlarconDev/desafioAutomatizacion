package utilidades;
//apache poi
import com.sun.jdi.BooleanValue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//jdk
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataDriven {
    public List<String> obtenerDatosDePrueba(String hojaEx, String tittleTC) throws IOException {
        ArrayList<String> datos = new ArrayList<String>();
        XSSFWorkbook excel = null;
        FileInputStream file = null;
        XSSFSheet hoja = null;
        PropertiesDriven prop = new PropertiesDriven();

        file = new FileInputStream(prop.obtenerProperties("rutaExcel"));

        excel = new XSSFWorkbook(file);


        int numberOfSheets = excel.getNumberOfSheets();

        for (int j = 0; j < numberOfSheets; j++) {
            if (excel.getSheetName(j).equalsIgnoreCase(hojaEx)) {
                hoja = excel.getSheetAt(j);
                Iterator<Row> filas = hoja.iterator();
                Row firstRow = filas.next();

                Iterator<Cell> celdas = firstRow.iterator();
                int x = 0;
                int col = 0;
                while (celdas.hasNext()) {
                    Cell selectedCell = celdas.next();
                    if (selectedCell.getStringCellValue().equalsIgnoreCase(prop.obtenerProperties("titulosCps"))) {
                        col = x;
                    }
                    x++;
                }

                while (filas.hasNext()) {
                    Row r = filas.next();
                    if (r.getCell(col).getStringCellValue().equalsIgnoreCase(tittleTC)) {
                        Iterator<Cell> cv = r.cellIterator();

                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                datos.add(c.getStringCellValue());
                            } else if (c.getCellType() == CellType.NUMERIC) {
                                datos.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            } else if(c.getCellType() == CellType.BOOLEAN){
                                datos.add(String.valueOf(c.getBooleanCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return datos;
    }
    /*public static void main(String[] args) throws  IOException {
        XSSFWorkbook excel = null;
        FileInputStream file = null;
        XSSFSheet hoja = null;
        file = new FileInputStream("C:\\Users\\clara.alarcon\\OneDrive - TSOFT\\Escritorio\\DesafioAutomatizacion\\TrabajoPOM_alarcon\\src\\test\\resources\\datos\\data.xlsx");

        excel = new XSSFWorkbook(file);


        int numberOfSheets = excel.getNumberOfSheets();
        //System.out.println("Cant hojas " + numberOfSheets);

        for (int j = 0; j < numberOfSheets ; j++) {
            if (excel.getSheetName(j).equalsIgnoreCase("DatosTC")) {
                 hoja = excel.getSheetAt(j);
                Iterator<Row> filas = hoja.iterator();
                Row firstRow = filas.next();

                Iterator<Cell> celdas = firstRow.iterator();
                int x = 0;
                int col=0;
                while(celdas.hasNext()) {
                    Cell selectedCell = celdas.next();
                    if(selectedCell.getStringCellValue().equalsIgnoreCase("CasosDePrueba")){
                        col = x;
                    }
                    x++;
                }

                while(filas.hasNext()){
                     Row r = filas.next();
                     if(r.getCell(col).getStringCellValue().equalsIgnoreCase("TC001")){
                            Iterator<Cell> cv = r.cellIterator();

                            while(cv.hasNext()){
                                Cell c = cv.next();
                                if(c.getCellType() == CellType.STRING){
                                    System.out.println(c.getStringCellValue());
                                } else if(c.getCellType() == CellType.NUMERIC){
                                    System.out.println(NumberToTextConverter.toText(c.getNumericCellValue()));
                                }

                            }
                     }
                }
            }
        }
    }
    */

}
