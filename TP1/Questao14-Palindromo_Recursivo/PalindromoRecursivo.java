public class PalindromoRecursivo
{
	public static void main(String[] args)
	{	
		boolean notFim;
		String entradaString = new String();

		do{
			entradaString = MyIO.readLine();
			notFim = notFim(entradaString);
			if(notFim)
			{
        			if(isPalindromo(entradaString, 0))
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
    * Checa se uma palavra eh palindromo, retorna true ou false
    */
	public static boolean isPalindromo(String entrada, int index)
	{
        	boolean isPalindromo = true;
			if(index < entrada.length()/2)
			{
				if(entrada.charAt(index) == entrada.charAt(entrada.length() - 1 - index))
				{
					isPalindromo = isPalindromo(entrada, index + 1);
				}
				else
				{
					isPalindromo = false;
				}
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