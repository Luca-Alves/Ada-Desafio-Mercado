import java.util.Arrays;
import java.util.Random;

public class Desafio01 {
    static Random random = new Random();
    private static final String[] nomes = new String[]{"Caio", "Marina", "Luca",
            "Rafa","Matias", "Fernandes", "Maia", "Rocha","Elis","Thiago"};

    public static void main(String[] args) {

        Vendedor[] vendedoresMercadoA =  new Vendedor[nomes.length];
        for (int i = 0; i < nomes.length; i++) {
            int indiceNome = random.nextInt(nomes.length);
            String nome = nomes[indiceNome];
            vendedoresMercadoA[i]=new Vendedor(nome);
        }
        Vendedor[] vendedoresMercadoB =  new Vendedor[nomes.length];
        for (int i = 0; i < nomes.length; i++) {
            int indiceNome = random.nextInt(nomes.length);
            String nome = nomes[indiceNome];
            vendedoresMercadoB[i]=new Vendedor(nome);
        }
        Vendedor[] vendedoresMercadoC =  new Vendedor[nomes.length];
        for (int i = 0; i < nomes.length; i++) {
            int indiceNome = random.nextInt(nomes.length);
            String nome = nomes[indiceNome];
            vendedoresMercadoC[i]=new Vendedor(nome);
        }

        Mercado mercadoA = new Mercado(vendedoresMercadoA,"Mercado Do Seu João",300);
        Mercado mercadoB = new Mercado(vendedoresMercadoB,"Mercado Da Esquina",500);
        Mercado mercadoC = new Mercado(vendedoresMercadoC,"Mercado Pega-Fogo",700);

        Comprador comprador = new Comprador();

        int qtdeVendedoresA = mercadoA.getVendedor().length;
        for (int i = 0; i < qtdeVendedoresA; i++) {
            Vendedor vendedor = mercadoA.getVendedor()[i];
            comprador.comprar(vendedor);
        }
        int qtdeVendedoresB = mercadoB.getVendedor().length;
        for (int i = 0; i < qtdeVendedoresB; i++) {
            Vendedor vendedor = mercadoB.getVendedor()[i];
            comprador.comprar(vendedor);
        }
        int qtdeVendedoresC = mercadoC.getVendedor().length;
        for (int i = 0; i < qtdeVendedoresC; i++) {
            Vendedor vendedor = mercadoC.getVendedor()[i];
            comprador.comprar(vendedor);
        }

        Regulador regulador =  new Regulador();

        regulador.confereBonificacao(new Mercado[]{mercadoA});
        regulador.confereBonificacao(new Mercado[]{mercadoB});
        regulador.confereBonificacao(new Mercado[]{mercadoC});


        System.out.println("================= S U M Á R I O =================\n");
        System.out.println("Itens Vendidos Pelo "+ mercadoA.getNomeMercado() + ": " + mercadoA.getTotalVendas()+"\n"
                + "\tHouveram " + mercadoA.getQtdePessoasBonificadas() + " funcionários bonificados!\n");
        System.out.println("Itens Vendidos Pelo "+ mercadoB.getNomeMercado() + ": " + mercadoB.getTotalVendas()+"\n"
                + "\tHouveram " + mercadoB.getQtdePessoasBonificadas() + " funcionários bonificados!\n");
        System.out.println("Itens Vendidos Pelo "+ mercadoC.getNomeMercado() + ": " + mercadoC.getTotalVendas()+"\n"
                + "\tHouveram " + mercadoC.getQtdePessoasBonificadas() + " funcionários bonificados!\n");
        System.out.println("=================================================");

        System.out.println("\n\n============= FUNCIONÁRIOS BONIFICADOS =============");
        System.out.println("\nFuncionários Bonificados do " + mercadoA.getNomeMercado() + "\n\tMeta mínima de "
                + mercadoA.getMeta() + " vendas.");
        System.out.println("\t"+Arrays.toString(mercadoA.nomesBonifc()));
        System.out.println("\nFuncionários Bonificados do " + mercadoB.getNomeMercado() + "\n\tMeta mínima de "
                + mercadoB.getMeta() + " vendas.");
        System.out.println("\t"+Arrays.toString(mercadoB.nomesBonifc()));
        System.out.println("\nFuncionários Bonificados do " + mercadoC.getNomeMercado() + "\n\tMeta mínima de "
                + mercadoC.getMeta() + " vendas.");
        System.out.println("\t"+Arrays.toString(mercadoC.nomesBonifc()));
        System.out.println("\n====================================================");
    }
}
class Mercado{
    Vendedor[] vendedor;
    private final int meta;
    private int TotalVendas;
    private final String nomeMercado;
    int qtdePessoasBonificadas;
    String[] funcionariosBonificados = new String[10];

    public Mercado(Vendedor[] vendedorCons,String nomeCons,int baterMeta){
        vendedor = vendedorCons;
        nomeMercado = nomeCons;
        meta = baterMeta;
    }
    public Vendedor[] getVendedor(){
        return vendedor;
    }

    public String getNomeMercado() {
        return nomeMercado;
    }
    public int vendasTotais(){
        int numeroDeVendas=0;
        for (int v = 0; v < vendedor.length; v++) {
          numeroDeVendas += vendedor[v].vendas;
        }
        return numeroDeVendas;
    };

    public int getTotalVendas() {
        TotalVendas=vendasTotais();
        return TotalVendas;
    }
    public int pessoasBonific(){
        int pBnf=0;
        int indiceArrayFuncBoni=0;
        for (int p = 0; p < vendedor.length; p++) {
            if (vendedor[p].bomificacao==10){
                pBnf+=1;
                funcionariosBonificados[indiceArrayFuncBoni] = vendedor[p].nome;
                indiceArrayFuncBoni++;
            }
        }
        return pBnf;
    };
    public int getQtdePessoasBonificadas() {
        qtdePessoasBonificadas = pessoasBonific();
        return qtdePessoasBonificadas;
    }

    public int getMeta() {
        return meta;
    }
    public String[] nomesBonifc(){
        int navegadorArr=0;
        int indiceArr=10;
        String[] nomesBoni= new String[10];

        for (int i = 0; i < vendedor.length; i++) {
            if (vendedor[i].bomificacao==10){
                nomesBoni[navegadorArr]=vendedor[i].nome;
                navegadorArr++;
            } else indiceArr--;
        }
        String[] nomesBonificados = new String[indiceArr];
        for (int i = 0; i < indiceArr; i++) {
            nomesBonificados[i]=nomesBoni[i];
        }

        return nomesBonificados;
    };
}

class Vendedor{

String nome;
int vendas;
int bomificacao;

    public Vendedor(String nomeCons){
    nome = nomeCons;
    }
    public void vender(int valor){
        vendas = valor;
    }
    public String anuncio(){
        String txtBoni="";
        if (bomificacao>0) txtBoni = "e foi bonificado com " +bomificacao+ "%";
        return nome + " " + "obteve " + vendas + " vendas " + txtBoni + "\n";
    }
    public int getVendas(){
        return this.vendas;
    }
}
class Comprador{
    Random random = new Random();
    public void comprar(Vendedor vendedor){
    vendedor.vender(random.nextInt(1000));
    }
}
class Regulador{
    public void confereBonificacao(Mercado[] mercados){
        for (int i = 0; i < mercados.length; i++) {
            Mercado mercado = mercados[i];
            Vendedor[] vendedor = mercado.vendedor;
            for (int j = 0; j < vendedor.length; j++) {
                Vendedor vendedore = vendedor[j];
                if (vendedore.getVendas() > mercado.getMeta()){

                    vendedore.bomificacao=10;
                }
                System.out.println(vendedore.anuncio());
            }
        }

    }
}