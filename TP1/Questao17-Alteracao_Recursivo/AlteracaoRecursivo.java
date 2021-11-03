import java.util.Random;
public class AlteracaoRecursivo
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
				char char1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
				char char2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
				alterar(entradaString, 0, char1, char2);
				MyIO.println("");
			}	
		} while(notFim);
	}


	


	/*
	* Sorteia 2 caracteres aleatorios e procura na string um dos sorteados. Se achar, os troca pelo outro sorteado. 
	* Em ultimo caso (caso nao corresponda a nenhum dos sorteados), printa o proprio caractere, inalterado
    */
	public static void alterar(String entradaString, int index, char char1, char char2)
	{
		if(index < entradaString.length())
		{
			if(entradaString.charAt(index) == char1)
			{
				MyIO.print(char2);
				alterar(entradaString, index+1, char1, char2);
			}
			else
			{
				MyIO.print(entradaString.charAt(index));
				alterar(entradaString, index+1, char1, char2);
			}
		}	
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
