public class Ciframento
{
	public static void main(String[] args)
	{	
		char[] entradaChar = new char[1000];
		int tam;
		boolean notFim;
		int quantEspacos;
		String entradaString = new String();

		do{
			entradaString = MyIO.readLine();
			notFim = notFim(entradaString);
			if(notFim)
			{
				str2char(entradaString, entradaChar);
        			tam = entradaString.length();
		
        			encriptar(entradaChar, tam);
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
    * Retorna a mensagem encriptada com a cifra de césar
    * somar 0x0 a um valor inteiro pega o valor char referente a 
    * tal inteiro na tabela ASCII
    */
	public static void encriptar(char[] entradaChar, int tam)
	{
		for(int i = 0; i < tam; i++)
		{
			MyIO.print((char)(entradaChar[i] + 3 + 0x0));
        	}
		MyIO.println("");
		return;
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
