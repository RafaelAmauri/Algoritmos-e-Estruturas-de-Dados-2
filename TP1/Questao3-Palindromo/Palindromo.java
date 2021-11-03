public class Palindromo
{
	public static void main(String[] args)
	{	
		char[] entradaChar = new char[1000];
		int tam;
		boolean notFim;
		String entradaString = new String();

		do{
			entradaString = MyIO.readLine();
			notFim = notFim(entradaString);
			if(notFim)
			{
				str2char(entradaString, entradaChar);
        			tam = entradaString.length();
		
        			if(isPalindromo(entradaChar, tam))
        			{
					MyIO.println("SIM");
        			}

        			else
        			{
					MyIO.println("NAO");
        			}
			}
		} while(notFim);
	}


	/*
	 * Converte de String para array de caracteres
	 */
	public static void str2char(String entradaString, char[] entradaChar)
	{
		for(int i = 0; i < entradaString.length(); i++)
		{
			entradaChar[i] = entradaString.charAt(i);
		}
	}
	

	/*
	 * Recebe um char[] e remove todos espacos dele. Retorna a quatidade
	 * de espacos que a palavra tinha, para assim saber o tamanho depois
	 * de os espacos terem sido removidos
	 */
	public static int removeEspaco(char[] entradaChar, int tam)
	{
		int quantEspacos = 0;

		for(int i = 0; i < tam; i++)
		{
			if(entradaChar[i] == ' ')
			{
				quantEspacos++;
				for(int j = i; j < (tam - 1); j++)
				{
					entradaChar[j] = entradaChar[j+1];
               			}
				entradaChar[tam-1] = '\0';
			}
		}
		return quantEspacos;
	}


    /*
    * Checa se uma palavra eh palindromo, retorna true ou false
    */
	public static boolean isPalindromo(char[] entradaChar, int tam)
	{
        	boolean isPalindromo = true;
		int cont = 0;
		for(int i = tam; i > (tam/2); i--)
		{
            		if(entradaChar[i - 1] != entradaChar[cont])
            		{
                		isPalindromo = false;
                		break;
            		}
			cont++;
        	}
        
		return isPalindromo;
    	}
        
    	/*
         * Verifica se a palavra inserida pelo usuario eh igual a FIM. Se for diferente, retorna True
         */
	public static boolean notFim(String entradaString)
	{
		boolean notFim = false;

                if(entradaString.charAt(0) != 'F' || entradaString.charAt(1) != 'I' || entradaString.charAt(2) != 'M')
                {
                        notFim = true;
                }

                return notFim;
        }

}
