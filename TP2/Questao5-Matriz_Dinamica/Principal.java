import java.io.*;
import java.nio.charset.*;

class MyIO {

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_){
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

   public static void print(){
   }

   public static void print(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(){
   }

   public static void println(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void printf(String formato, double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, x);// "%.2f"
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static double readDouble(){
      double d = -1;
      try{
         d = Double.parseDouble(readString().trim().replace(",","."));
      }catch(Exception e){}
      return d;
   }

   public static double readDouble(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readDouble();
   }

   public static float readFloat(){
      return (float) readDouble();
   }

   public static float readFloat(String str){
      return (float) readDouble(str);
   }

   public static int readInt(){
      int i = -1;
      try{
         i = Integer.parseInt(readString().trim());
      }catch(Exception e){}
      return i;
   }

   public static int readInt(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readInt();
   }

   public static String readString(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != ' ' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n' && tmp != ' ');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readString(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readString();
   }

   public static String readLine(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readLine(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readLine();
   }

   public static char readChar(){
      char resp = ' ';
      try{
         resp  = (char)in.read();
      }catch(Exception e){}
      return resp;
   }

   public static char readChar(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readChar();
   }

   public static boolean readBoolean(){
      boolean resp = false;
      String str = "";

      try{
         str = readString();
      }catch(Exception e){}

      if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || 
            str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
         resp = true;
            }

      return resp;
   }

   public static boolean readBoolean(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readBoolean();
   }

   public static void pause(){
      try{
         in.read();
      }catch(Exception e){}
   }

   public static void pause(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      pause();
   }
}

class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
      this(0, null, null, null, null);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}

class Matriz {
   public Celula inicial;
   private int linha, coluna;

   public Matriz (){
      this(3, 3);
   }

   public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;

      //alocar a matriz com this.linha linhas e this.coluna colunas
   }
	
   public void ler()
   {
		Celula nova = new Celula();
		Celula cima;
		this.inicial = new Celula();

		int elemento;

		for(int i = 0; i < this.linha; i++)
		{
			cima = inicial;	
					
			for(int k = i; k > 1; k--)
				cima = cima.inf;	
			
			for(int j = 0; j < this.coluna; j++)
			{
				//System.out.printf("Digite o elemento [%d][%d]:\n", i, j);
				elemento = MyIO.readInt();

				if((i == 0)  && (j == 0))
				{
					this.inicial.elemento  =  elemento;
					nova                   =  this.inicial;

					continue;
				}

				nova.dir      =  new Celula(elemento);
				nova.dir.esq  =  nova;
				nova          =  nova.dir;
				
				
				if(i > 0)
				{
					
					
					cima = inicial;	
					
					for(int k = i; k > 1; k--)
						cima = cima.inf;	

					for(int k = j; k > 0; k--)
						cima = cima.dir;

					nova.sup  =  cima;
					cima.inf  =  nova;
				}
			}
		}
   }

   public Matriz soma (Matriz m) {
      Matriz resp          =  new Matriz(m.linha, m.coluna);
	  resp.inicial         =  new Celula();
	  Celula resp_cel      =  resp.inicial;
	  Celula m1_cel        =  this.inicial;
	  Celula m2_cel        =  m.inicial;
	  Celula cima          =  resp.inicial;

	  for(int i = 0; i < linha; i++)
	  {

			m1_cel    =  this.inicial;
			m2_cel    =  m.inicial;
			resp_cel  =  resp.inicial;

			if(i > 0)
			{
				for(int k = i; k > 1; k--)
				{
					m1_cel    =  m1_cel.inf;
					m2_cel    =  m2_cel.inf;
					resp_cel  =  resp_cel.inf;
				}

				m1_cel        =  m1_cel.inf;
				m2_cel        =  m2_cel.inf;
				resp_cel.inf  =  new Celula();
				resp_cel      =  resp_cel.inf;
			}

			for(int j = 0; j < coluna; j++)
			{
				resp_cel.elemento = m1_cel.elemento + m2_cel.elemento;

				resp_cel.dir      =  new Celula();
				resp_cel.dir.esq  =  resp_cel;
				resp_cel          =  resp_cel.dir;
				m1_cel            =  m1_cel.dir;
				m2_cel            =  m2_cel.dir;

				if(i > 0)
				{
					cima = resp.inicial;

					for(int k = i; k > 1; k--)
						cima = cima.inf;
					
					for(int k = j; k >= 0; k--)
						cima = cima.dir;

					resp_cel.sup  =  cima;
					cima.inf      =  resp_cel;  
				}
			}
	  }

      return resp;
   }

   public Matriz multiplicacao (Matriz m) {
	
	Matriz resp          =  new Matriz(m.linha, m.coluna);
	resp.inicial         =  new Celula();
	Celula resp_cel      =  resp.inicial;
	Celula m1_cel        =  this.inicial;
	Celula m2_cel        =  m.inicial;
	Celula cima          =  resp.inicial;
	Celula aux           =  m1_cel;
	Celula aux2          =  m2_cel;
	int soma;


	for(int i = 0; i < linha; i++)
	{
		m1_cel    =  this.inicial;
		m2_cel    =  m.inicial;
		resp_cel  =  resp.inicial;

		
		if(i > 0)
		{
			for(int k = i; k > 1; k--)
			{
				m1_cel    =  m1_cel.inf;
				resp_cel  =  resp_cel.inf;
			}

			m1_cel        =  m1_cel.inf;
			m2_cel        =  m.inicial;
			resp_cel.inf  =  new Celula();
			resp_cel      =  resp_cel.inf;
		}
		
		aux   =  m1_cel;
		aux2  =  m2_cel;

		for(int j = 0; j < coluna; j++)
		{

			soma = 0;
			
			for(int k = 0; k < linha; k++)
			{
				soma += m1_cel.elemento * m2_cel.elemento;

				m1_cel  =  m1_cel.dir;
				m2_cel  =  m2_cel.inf;
			}

			resp_cel.elemento = soma;

			resp_cel.dir      =  new Celula();
			resp_cel.dir.esq  =  resp_cel;
			resp_cel          =  resp_cel.dir;
			m1_cel            =  aux;
			aux2              =  aux2.dir;
			m2_cel            =  aux2;

			if(i > 0)
			{
				cima = resp.inicial;

				for(int k = i; k > 1; k--)
					cima = cima.inf;
					
				for(int k = j; k >= 0; k--)
					cima = cima.dir;

				resp_cel.sup  =  cima;
				cima.inf      =  resp_cel;  
			}
		}
	}


	return resp;
	}


   public boolean isQuadrada(){
      return (this.linha == this.coluna);
   }


   public void mostrarDiagonalPrincipal (){
      
		if(isQuadrada())
		{
			Celula aux = inicial;
			while(aux != null)
			{
				try
				{
					System.out.printf("%d ", aux.elemento);
					aux = aux.inf.dir;
				}
				catch(java.lang.NullPointerException error)
				{
					return;
				}
			}
    	}
   }


   public void mostrarDiagonalSecundaria (){
	if(isQuadrada())
	{
		Celula aux = inicial;

		for(int i = 0; i < coluna-1; i++)
			aux = aux.dir;

		while(aux != null)
		{
			try
			{
				System.out.printf("%d ", aux.elemento);
				aux = aux.inf.esq;
			}

			catch(java.lang.NullPointerException error)
			{
				return;
			}
		
		}
		
    }
	  
   }

   public void mostrar(){
     
	Celula aux   =  this.inicial;
	Celula aux2  =  aux;

	for(int i = 0; i < linha; i++)
	{
		aux = aux2;
		for(int j = 0; j < coluna; j++) 
		 {
				System.out.printf("%d ", aux.elemento);
				aux = aux.dir;
		 }
		MyIO.println("");
		aux2 = aux2.inf;
	}

   
   }
}

public class Principal {

   public static void main(String[] args){
    
	int quantOperacoes = MyIO.readInt();
	
	for(int i = 0; i < quantOperacoes; i++)
	{
		Matriz m1, m2, m3;

		m1 = new Matriz(MyIO.readInt(), MyIO.readInt());
		
		m1.ler();
		//m1.mostrar();
		m1.mostrarDiagonalPrincipal();
		System.out.println();
		m1.mostrarDiagonalSecundaria();

		m2 = new Matriz(MyIO.readInt(), MyIO.readInt());
		m2.ler();
		//m2.mostrar();
	
		System.out.println();
		m3 = m1.soma(m2);
		m3.mostrar();
		
		m3 = m1.multiplicacao(m2);
		m3.mostrar();
		
	}
   }
}
