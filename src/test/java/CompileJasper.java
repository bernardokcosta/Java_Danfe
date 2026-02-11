import net.sf.jasperreports.engine.JasperCompileManager;

public class CompileJasper {
    public static void main(String[] args) {
        try {
            String baseDir = "src/main/resources/jasper/";

            JasperCompileManager.compileReportToFile(
                baseDir + "nfe/danfe.jrxml",
                baseDir + "nfe/danfe.jasper"
            );
            System.out.println("danfe.jasper compilado com sucesso!");

            JasperCompileManager.compileReportToFile(
                baseDir + "nfe/danfe_fatura.jrxml",
                baseDir + "nfe/danfe_fatura.jasper"
            );
            System.out.println("danfe_fatura.jasper compilado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

