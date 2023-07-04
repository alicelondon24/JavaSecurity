package security;

import java.io.File;
import java.io.IOException;

public class VerificaPasta {

    public static void main(String[] args) {
        String pasta = "caminho/para/pasta";

        try {
            File diretorio = new File(pasta);
            File[] arquivos = diretorio.listFiles();

            for (File arquivo : arquivos) {
                if (!arquivo.isDirectory()) {
                    verificarArquivo(arquivo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verificarArquivo(File arquivo) throws IOException {
        String comando = "clamscan --no-summary " + arquivo.getAbsolutePath();
        Process processo = Runtime.getRuntime().exec(comando);

        try {
            int resultado = processo.waitFor();
            if (resultado == 0) {
                System.out.println("o arquivo " + arquivo.getName() + " não está infectado! :)");
            } else {
                System.out.println("o arquivo " + arquivo.getName() + " está infectado! :(");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
