import java.util.Random;
public class Alteracao
{
	public static void main(String[] args)
	{	
		boolean notFim;
		String entradaString = new String();
		Random gerador = new Random();
        gerador.setSeed(4);

		do{
			entradaString = MyIO.readLine();
			notFim = notFim(entradaString);
			if(notFim)
			{
        		
				alterar(entradaString, gerador);
			}	
		} while(notFim);
	}


	


	/*
	* Sorteia 2 caracteres aleatorios e procura na string um dos sorteados. Se achar, os troca pelo outro sorteado. 
	* Em ultimo caso (caso nao corresponda a nenhum dos sorteados), printa o proprio caractere, inalterado
    */
	public static void alterar(String entradaString, Random gerador)
	{
		// Sorteando as duas letras
		char char1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
		char char2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

		for(int i = 0; i < entradaString.length(); i++)
		{
			if(entradaString.charAt(i) == char1)
			{
				MyIO.print(char2);
			}
			else
			{
				MyIO.print(entradaString.charAt(i));
			}
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
		if(entradaString.length() >= 3)
		{
                	if(entradaString.charAt(0) != 'F' || entradaString.charAt(1) != 'I' || entradaString.charAt(2) != 'M')
                	{
                	        notFim = true;
                	}
		}
		else
		{
			notFim = true;
		}
                return notFim;
        }

}
