import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * No da arvore binaria
 * @author Max do Val Machado
 */
class No{
  public boolean cor;
  public Time time;
  public No esq, dir;
  
  public No (Time elemento){
      this(elemento, false, null, null);
  }
  public No (Time elemento, boolean cor){
      this(elemento, cor, null, null);
  }
  public No (Time elemento, boolean cor, No esq, No dir){
    this.cor   =  cor;
    this.time  =  new Time();
	this.time  =  elemento.clone();
    this.esq   =  esq;
    this.dir   =  dir;
  }
}

/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */
class Arvore {
	public No raiz; // Raiz da arvore.
	
	public int comparacoes;

	/**
	 * Construtor da classe.
	 */
	public Arvore() {
		raiz = null;
		comparacoes = 0;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param elemento Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String elemento) {
		return pesquisar(elemento, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param elemento Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String elemento, No i) {
      boolean resp;

	  comparacoes++;	
	 
	  if (i == null) {
		 System.out.printf("N%cO\n", 195);
         resp = false;

      } else if (elemento.compareTo(i.time.getNome()) == 0) {
        comparacoes++;
		System.out.println("SIM"); 
		resp = true;

      } else if (elemento.compareTo(i.time.getNome()) < 0) {
		 comparacoes++;
		 System.out.printf("esq ");
         resp = pesquisar(elemento, i.esq);

      } else {
		 comparacoes++;
		 System.out.printf("dir ");
         resp = pesquisar(elemento, i.dir);
      }
      return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarCentral() {
		//System.out.print("[ ");
		mostrarCentral(raiz);
		//System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarCentral(No i) {
		if (i != null) {
			mostrarCentral(i.esq); // Elementos da esquerda.
			//System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPre() {
		//System.out.print("[ ");
		mostrarPre(raiz);
		//System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPre(No i) {
		if (i != null) {
			//System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarPre(i.esq); // Elementos da esquerda.
			mostrarPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPos() {
		//System.out.print("[ ");
		mostrarPos(raiz);
		//System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPos(No i) {
		if (i != null) {
			mostrarPos(i.esq); // Elementos da esquerda.
			mostrarPos(i.dir); // Elementos da direita.
			//System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param elemento Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Time elemento)/* throws Exception */{
   
      //Se a arvore estiver vazia
      if(raiz == null){
         raiz = new No(elemento, false);
         //System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");

      //Senao, se a arvore tiver um elemento 
      } else if (raiz.esq == null && raiz.dir == null){
         if (raiz.time.getNome().compareTo(elemento.getNome()) > 0){
            raiz.esq = new No(elemento, true);
            //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento +").");
         } else {
            raiz.dir = new No(elemento, true);
            //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento +").");
         }

      //Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null){

         if(raiz.time.getNome().compareTo(elemento.getNome()) > 0){
            raiz.esq = new No(elemento);
            //System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");

         } else if (raiz.dir.time.getNome().compareTo(elemento.getNome()) > 0){
            raiz.esq = new No(raiz.time);
            raiz.time = elemento;
            //System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");

         } else {
            raiz.esq = new No(raiz.time);
            raiz.time = raiz.dir.time;
            raiz.dir.time = elemento;
            //System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
         }

         raiz.esq.cor = raiz.dir.cor = false;
         
      //Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null){
         
         if(raiz.time.getNome().compareTo(elemento.getNome()) < 0){
            raiz.dir = new No(elemento);
            //System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
         } else if (raiz.esq.time.getNome().compareTo(elemento.getNome()) < 0){
            raiz.dir = new No(raiz.time);
            raiz.time = elemento;
            //System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
         } else {
            raiz.dir = new No(raiz.time);
            raiz.time = raiz.esq.time;
            raiz.esq.time = elemento;
            //System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
         }

         raiz.esq.cor = raiz.dir.cor = false;

      //Senao, a arvore tem tres ou mais elementos
      } else {
         //System.out.println("Arvore com tres ou mais elementos...");
		   inserir(elemento, null, null, null, raiz);
      }

      raiz.cor = false;
   }

   private void balancear(No bisavo, No avo, No pai, No i){

      //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if(pai.cor == true){

         //4 tipos de reequilibrios e acoplamento
         if(pai.time.getNome().compareTo(avo.time.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
            if(i.time.getNome().compareTo(pai.time.getNome()) > 0){
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }

         } else { // rotacao a direita ou esquerda-direita
            if(i.time.getNome().compareTo(pai.time.getNome()) < 0){
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }

         if (bisavo == null){
            raiz = avo;
         } else {
            if(avo.time.getNome().compareTo(bisavo.time.getNome()) < 0){
               bisavo.esq = avo;
            } else {
               bisavo.dir = avo;
            }
         }

         //reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
         //System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir(" + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
      } //if(pai.cor == true)
   }

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param elemento Elemento a ser inserido.
	 * @param avo No em analise.
	 * @param pai No em analise.
	 * @param i No em analise.
	 * @throws Exception Se o elemento existir.
	 */
	private void inserir(Time elemento, No bisavo, No avo, No pai, No i)/* throws Exception */{
		if (i == null) {
         if(elemento.getNome().compareTo(pai.time.getNome()) < 0){
            i = pai.esq = new No(elemento, true);
         } else {
            i = pai.dir = new No(elemento, true);
         }
         if(pai.cor == true){
            balancear(bisavo, avo, pai, i);
         }
      } else {
        //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if(i == raiz){
               i.cor = false;
            }else if(pai.cor == true){
               balancear(bisavo, avo, pai, i);
            }
         }
         if (elemento.getNome().compareTo(i.time.getNome()) < 0) {
            inserir(elemento, avo, pai, i, i.esq);
         } else if (elemento.getNome().compareTo(i.time.getNome()) > 0) {
            inserir(elemento, avo, pai, i, i.dir);
         } else {
			;
            //throw new Exception("Erro inserir (elemento repetido)!");
         }
      }
	}

   private No rotacaoDir(No no) {
      //System.out.println("Rotacao DIR(" + no.elemento + ")");
      No noEsq = no.esq;
      No noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private No rotacaoEsq(No no) {
      //System.out.println("Rotacao ESQ(" + no.elemento + ")");
      No noDir = no.dir;
      No noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private No rotacaoDirEsq(No no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private No rotacaoEsqDir(No no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }
}


class Time
{
	private String nome, apelidos, tecnico, estadio;
	private String liga, nomeArquivo;
	public int dia, mes, ano, capacidade;
	private long tamPag;

	public Time()
	{
		return ;
	}


	public Time(String nome, String apelidos, String tecnico, String estadio, String liga, String nomeArquivo, int dia, int mes, int ano, int capacidade, long tamPag)
	{
		this.setNome         (nome);
		this.setApelido      (apelidos);
		this.setTecnico      (tecnico);
		this.setEstadio      (estadio);
		this.setLiga         (liga);
		this.setNomeArquivo  (nomeArquivo);
		this.setDia          (dia);
		this.setMes          (mes);
		this.setAno          (ano);
		this.setCapacidade   (capacidade);
		this.setTamPag       (tamPag);
	}


	public void setNome(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
	

	public void setApelido(String apelidos){
		this.apelidos = apelidos;
	}
	public String getApelidos(){
		return this.apelidos;
	}


	public void setTecnico(String tecnico){
		this.tecnico = tecnico;
	}
	public String getTecnico(){
		return this.tecnico;
	}


	public void setEstadio(String estadio){
		this.estadio = estadio;
	}
	public String getEstadio(){
		return this.estadio;
	}


	public void setLiga(String liga){
		this.liga = liga;
	}
	public String getLiga(){
		return this.liga;
	}


	public void setNomeArquivo(String nomeArquivo){
		this.nomeArquivo = nomeArquivo;
	}
	public String getNomeArquivo(){
		return this.nomeArquivo;
	}


	public void setDia(int dia){
		this.dia = dia;
	}
	public int getDia(){
		return this.dia;
	}


	public void setMes(int mes){
		this.mes = mes;
	}
	public int getMes(){
		return this.mes;
	}


	public void setAno(int ano){
		this.ano = ano;
	}
	public int getAno(){
		return this.ano;
	}

	
	public void setCapacidade(int capacidade){
		this.capacidade = capacidade;
	}
	public int getCapacidade(){
		return this.capacidade;
	}

	
	public void setTamPag(long tamPag){
		this.tamPag = tamPag;
	}
	public long getTamPag(){
		return this.tamPag;
	}


	public void imprimir(){
		System.out.printf("%s ## %s ## %02d/%02d/%02d ## %s ## %d ## %s ## %s ## %s ## %d ## \n", this.getNome(), this.getApelidos(), this.getDia(), this.getMes(), this.getAno(), this.getEstadio(), this.getCapacidade(), this.getTecnico(), this.getLiga(), this.getNomeArquivo(), this.getTamPag());
	}


	public Time clone(){
		Time novo = new Time(this.getNome(), this.getApelidos(), this.getTecnico(), this.getEstadio(), this.getLiga(), this.getNomeArquivo(), this.getDia(), this.getMes(), this.getAno(), this.getCapacidade(), this.getTamPag());
		return novo;
	}

	public void ler(String nomeArquivo){
		String linha;
		this.setNomeArquivo(nomeArquivo);

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
			File arquivo = new File(nomeArquivo);
			this.setTamPag(arquivo.length());

			while ((linha = reader.readLine()) != null)
			{
				if(linha.contains("infobox vcard"))
				{
					
					while(!linha.contains("League"))
					{
						linha += reader.readLine();
					}

					linha = javaq3.removerTags(linha, "<", ">");
					//System.out.println("Linha = "+ linha);

					String nome;
					nome = javaq3.retornarEntreTags(linha, "Full name", "</td>");
					nome = javaq3.removerTags( nome, " &", ";");
					nome = javaq3.removerTags( nome, "&", ";");
					this.setNome(nome);


					String apelidos;
					apelidos = javaq3.retornarEntreTags(linha, "Nickname(s)", "</td>");
					apelidos = javaq3.removerTags(apelidos, "&", ";");
					this.setApelido(apelidos);
					
					String estadio;
					estadio = javaq3.retornarEntreTags(linha, "Ground", "</td>");
					estadio = javaq3.removerTags(estadio, "&", ";");
					this.setEstadio(estadio);

					String capacidadeString = javaq3.retornarEntreTags(linha, "Capacity", "</td>"); 
					
					capacidadeString = javaq3.removerTags(capacidadeString, " ", ")");
					capacidadeString = javaq3.removerTags(capacidadeString, "&", ";");
					capacidadeString = javaq3.removerCaracteres(capacidadeString, " ");
					capacidadeString = javaq3.removerTags(capacidadeString, "–", ".");
					capacidadeString = javaq3.removerCaracteres(capacidadeString, ".");
					capacidadeString = javaq3.removerCaracteres(capacidadeString, ",");
					capacidadeString = javaq3.removerTags(capacidadeString, "(", ")");
					capacidadeString = javaq3.removerTags(capacidadeString, "[", "]");
					capacidadeString = javaq3.removerCaracteres(capacidadeString, "/");

					try
					{
						this.setCapacidade(Integer.parseInt(capacidadeString));
					}
					catch(java.lang.NumberFormatException error)
					{
						this.setCapacidade(6000);
					}

					String tecnico = "0";

					if(linha.contains("Manager"))
					{
						tecnico = javaq3.retornarEntreTags(linha, "Manager", "</td>");
					}
					else if(linha.contains("Coach"))
					{
						tecnico = javaq3.retornarEntreTags(linha, "Coach", "</td>");
					}
					else if(linha.contains("Head coach"))
					{
						tecnico = javaq3.retornarEntreTags(linha, "Head coach", "</td>");
					}

					tecnico = javaq3.removerTags(tecnico, "&", ";");
					this.setTecnico(tecnico);

					String liga = javaq3.retornarEntreTags(linha, ">League", "</td>");
					liga = javaq3.removerTags(liga, "&", ";");
					this.setLiga(liga);

					String data = javaq3.retornarEntreTags(linha, "Founde", "</td>");

					int[] dias = javaq3.filtrarData(data);
						
					this.setDia(dias[0]);
					this.setMes(dias[1]);
					this.setAno(dias[2]);

					break;
					}
				}
			}
			
			catch(FileNotFoundException erro) 
			{
				System.out.println(erro);
			}
			catch(IOException erro)
			{
				System.out.println(erro);
			}	
	}
}


public class javaq3
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String nomeArquivo;
		int index = 0, comparacoes = 0;
		long inicio = System.nanoTime();
		boolean notFim;
		
		Arvore arvore =  new Arvore();
		
		Time tmp;
		do{
			nomeArquivo = in.nextLine();
			notFim = notFim(nomeArquivo); // Se nome do time for diferente de FIM, a execucao continua

			if(notFim)
			{
				tmp = new Time();
				tmp.ler(nomeArquivo);
				arvore.inserir(tmp);
						
				if(tmp.getAno() == 0)
					tmp.imprimir();
			}
		
		}while(notFim);
			
		String nomeTime;
		do{
			nomeTime = in.nextLine();
			notFim = notFim(nomeTime); // Se nome do time for diferente de FIM, a execucao continua

			if(notFim)
			{
				System.out.printf("%s raiz ", nomeTime);
				arvore.pesquisar(nomeTime);
			}
		}while(notFim);

		long fim = System.nanoTime();
	
		try 
        {   
            FileWriter fw = new FileWriter("./651047_alvinegra.txt");

            String tempo = Long.toString(fim-inicio);
            String log = "651047\t"+ arvore.comparacoes +"\t"+tempo;
    
            fw.write(log);
            fw.close();
        }
        catch(Exception erro)
        {   
            System.out.println(erro);
        }   
	
	}


	//Usada para achar as datas de fundacao dos times
	public static int[] filtrarData(String dataString)
	{
	
		/*
		* data[0] eh o dia
		* data[1] eh o mes
		* data[2] eh o ano
		*/
        int[] data = new int[3]; 
        char[] dataChar = dataString.toCharArray();
		String numeroChar;


		if(dataString.contains("&#59"))
		{
			dataString = retornarEntreTags(dataString, "d", "#59");
			dataString = removerTags(dataString, "#", ";");
	        dataString = removerCaracteres(dataString, ",");
            dataChar = dataString.toCharArray();
            
            for(int i = 0; i < dataChar.length; i++)
            {
                if(dataChar[i] == '&')
                {
					dataChar[i] = ' ';
                }
            }
            dataString = new String(dataChar).substring(0, dataChar.length-1);
            dataChar = dataString.toCharArray();
		}
	
		dataString = removerTags(dataString, "#", ";");
		dataString = removerTags(dataString, "(", ")");
		dataString = dataString.replace("&", " ");
		dataString = removerCaracteres(dataString, ",");
		dataString = removerCaracteres(dataString, "d");
		
		if(dataString.length() == 4)
		{
			data[0] = 0;
            data[1] = 0;
            data[2] = Integer.parseInt(dataString);
            
			return data;
		}
	
		dataChar = dataString.toCharArray();
		//System.out.println(dataString);

		int index1 = -1, index2 = -1;
		for(int i = 0; i < dataString.length(); i++)	
			if(dataChar[i] == '-' || dataChar[i] == '/' || dataChar[i] == ' ')
			{
				index1 = i;
                for(int j = i+1; j < dataString.length(); j++)
                {
					if(dataChar[j] == '-' || dataChar[j] == '/' || dataChar[j] == ' ')
					{
						index2 = j;
						j = dataString.length();
						i = dataString.length();
                    }	
                }	
			}	
			// String antes do primeiro -

			numeroChar = dataString.substring(0, index1+1);
			numeroChar = removerCaracteres(numeroChar, " ");
			try
			{  
			data[0] = Integer.parseInt(numeroChar);
			}
			catch(NumberFormatException erro)
			{
				data[0] = 0;
			}
			// String entre as duas tags -
			if(index1 != -1 && index2 != -1)
			{
				numeroChar = dataString.substring(index1+1, index2+1);
				numeroChar = removerCaracteres(numeroChar, " ");
				try
				{
					data[1] = Integer.parseInt(numeroChar);
				}
				catch(NumberFormatException erro)
				{
					data[1] = 0;
				}
			}
			else
			{	
				index2 = index1;
			}
            // String depois do segundo -
			numeroChar = dataString.substring(index2+1, dataString.length());
			numeroChar = removerCaracteres(numeroChar, " ");
			try
			{
			data[2] = Integer.parseInt(numeroChar);
			}
			catch(NumberFormatException erro)
			{
				data[2] = 0;
			}
				

		//System.out.println(data[0] + " " + data[1] + " " + data[2]);
		
		/* Aqui confirmaremos que a data esta no formato dia/mes/ano.
		* A variavel <dataString> vai conter todo o conteudo que que esta entre "Founded" e "</td"
		* A logica eh ver se nomes de meses sao substrings de <numeroChar>
		* Se for, eh pq o mes que eh substring deve ser o valor numerico da variavel <data[1]>.
		* Caso o valor numerico de <mes> seja diferente do valor do mes no mundo real, ele
		* eh trocado com a variavel dia (Ex: <data[1]> = 3 e  subString(linha, "September") retorna True. Isso
		* nao pode acontecer, pois setembro eh o mes 9. Entao, concluimos que 3 so pode ser o dia! <data[0]> assume o valor
		* de <data[1]>, e <data[1]> vira 9)
		*/


		for(int i = 0; i < 3; i++)
		{
			if(data[i] > data[2])
			{
				int aux = data[2];
				data[2] = data[i];
				data[i] = aux;
			}
		}
		
		// Matriz com meses
		String[][] meses = {
					{"January"}, {"February"}, {"March"}, {"April"}, {"May"}, {"June"},
					{"July"}, {"August"}, {"September"}, {"October"}, {"November"}, {"December"}
					};


		for(int i = 0; i < 12; i++)
        {
			if(dataString.contains(meses[i][0]) && data[1] != i+1)
			{
				if(data[0] == i+1 && data[1] != 0)
				{	
					data[0] = data[1];
					data[1] = i+1;
				}
				else if(data[0] == 0 && data[1] != i+1)
				{
					data[0] = data[1];
					data[1] = i+1;
				}
				else
				{
					data[1] = i+1;
				}
			}
        }

		return data;
	}

	// Retorna todos as palavras da string que estao entre as strings st1 e str2
	public static String retornarEntreTags(String string, String str1, String str2)
	{

			boolean isStr1 = false, isStr2 = false;
			String stringEntreTags = new String();
        
            // Percorrer <string> inteira ate achar
            // o primeiro caractere de <str1>
			for(int i = 0; i < string.length(); i++)
			{ 
                if(string.charAt(i) == str1.charAt(0))
                {
                    isStr1 = true;
                    // Verificar se a primeira letra achada realmente corresponde a
                    // <str1>. Se nao corresponder, <isStr1> vira false, e voltamos para o for externo
                    for(int j = 0; j < str1.length(); j++)
                    {
                        if(string.charAt(i+j) != str1.charAt(j))
                        {
                            isStr1 = false;
                        }
                    }
                }	

                if(isStr1)
                {
                    // Caso aquela primeira letra achada seja de <str1>, 
                    // iniciamos outro for para achar a string <str2>. A mesma logica
                    // de procura será usada.
                    for(int j = i+str1.length()+1 ; j < string.length(); j++)
                    {
                        if(string.charAt(j) == str2.charAt(0))
                        {
                            isStr2 = true;
                            for(int k = 0; k < str2.length(); k++)
                            {
                                if(string.charAt(j+k) != str2.charAt(k))
                                {			
                                    isStr2 = false;			
                                }
                            }
                            
                            if(isStr1 && isStr2)
                            {
                                // Caso tenhamos achado <str1> e <str2>, vamos definir <stringEntreTags>
                                // como o que esta entre <i + str1.length()> 
                                // (primeira letra de <str1> + tamanho de <str1> = final de <str1>)
                                //  e o que esta em <j> (primeira letra de <str2>)
                                stringEntreTags = string.substring(
                                    i + str1.length(),
                                    j);
                                

                                // Deletando as aspas por causa de um problema com um time que tinha
                                // uma " fora de tags html                               
                                stringEntreTags = removerCaracteres(stringEntreTags, "\"");
                                
                                // Muitos times tem &#91 neles, inclusive entre varios atributos.
                                // Eu notei que sempre o que me interessa sempre está antes do &#91, entao
                                // passei a verificar se &#91 existe. Caso exista, <stringEntreTags>
                                // se torna o que esta entre <str> e &#91, ao inves de <str1> e <str2>
                                if(stringEntreTags.contains("&#91"))
                                {
                                    stringEntreTags = removerTags(stringEntreTags, "&#91;", "&#93;");
                                }
                                return stringEntreTags;
                            }
                        }
                    }
                }
            }
        // Caso nao exista <str1> e <str2>, será retornarnada a string recebida
		if(!isStr1 && !isStr2)
		{
			return string;
		}
		return stringEntreTags;
	}


	/*
	* Recebe duas strings. Remove todas ocorrencias da segunda 
	* string na primeira e retorna a primeira
	*/
	public static String removerCaracteres(String string, String parteRemovida)
	{
		boolean isParteRemovida;


        char[] stringChar = string.toCharArray(); 
        int tamanho = stringChar.length;

		for(int i = 0; i < string.length(); i++)
		{

			// Caso ache a primeira letra de parteRemovida, sera ativado um 
			// loop <for> para ver se aquela letra corresponde à parte a ser removida
			// ou se for apenas uma letra de outra palavra
			
			if(string.charAt(i) == parteRemovida.charAt(0))
			{
				isParteRemovida = true;
				for(int j = 0; j < parteRemovida.length(); j++)	
				{

					// Comparando com o resto da string. Se for diferente, a <isParteRemovida>
					// vira <false>, falhando no if que vem em seguida e voltando para o loop
					
					if(string.charAt(i+j) != parteRemovida.charAt(j))
					{
						isParteRemovida = false;
                    }
                }
				if(isParteRemovida)
				{	
                    // Como precisamos tirar <parteRemovida> por inteiro, temos
                    // um loop que roda uma vez pra cada caractere presente em
                    // <parteRemovida>
                    for(int x = 0; x < parteRemovida.length(); x++)
                    {
                        for(int j = i; j < string.length()-1; j++)
                        {	
                            stringChar[j] = stringChar[j+1];
                        }
                        string = new String(stringChar).substring(0, tamanho-1);
                        stringChar = string.toCharArray();
                        tamanho--;
                    }
                    
                    i--;
				}	
            }
        }

		return string;
	}

	/*
	* Recebe tres strings. Remove tudo que 
	* estiver entre str1 e str2 e retorna a string tratada
	*/
	public static String removerTags(String string, String tag1, String tag2)
	{

        char[] stringChar = string.toCharArray(); 
        int tamanho = stringChar.length;
        boolean isTag1 = false, isTag2 = false;
        
		for(int i = 0; i < string.length(); i++)
		{	 
			if( stringChar[i] == '<' &&
			    stringChar[i+1] == '/' &&
				stringChar[i+2] == 't' &&
				stringChar[i+3] == 'd' &&
				stringChar[i+4] == '>')
			{
				continue;
            }
            
			if(stringChar[i] == tag1.charAt(0))
			{
                isTag1 = true;
                for(int j = 0; j < tag1.length()-1; j++)
                {
                    if(tag1.charAt(j) != stringChar[i + j])
                    {
                        isTag1 = false;
                    }
                }
                if(isTag1)
                {
                    for(int j = (i + tag1.length()+1); j < stringChar.length; j++)
                    {
                        if(stringChar[j] == tag2.charAt(0))
                        {
                            isTag2 = true;
                            for(int k = 0; k < tag2.length(); k++)
                            {
                                if(tag2.charAt(k) != stringChar[j + k])
                                {
                                    isTag2 = false;
                                    k = tag2.length();
                                }
                            }
                            if(isTag2)
                            {
                                for(int k = 0; k < j-i +tag2.length(); k++)
                                {
                                    for(int x = i; x < string.length()-1; x++)
                                    {
                                        stringChar[x] = stringChar[x+1];
                                    }
                                    string = new String(stringChar).substring(0, tamanho-1);
                                    stringChar = string.toCharArray();
                                    tamanho--;
                                }
                                
                                i--;
                                j = string.length();
                            }	
                        }
                    }
                }
			}
		}

		return string;
	}

	// Verifica se a string recebida é igual a "FIM"
	public static boolean notFim(String string)
    {
            boolean notFim = false;

            if(string.charAt(0) != 'F' || string.charAt(1) != 'I' || string.charAt(2) != 'M')
            {
                    notFim = true;
            }
            return notFim;
    }
}
