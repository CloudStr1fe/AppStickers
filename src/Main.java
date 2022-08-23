import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        // exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }
    }
}