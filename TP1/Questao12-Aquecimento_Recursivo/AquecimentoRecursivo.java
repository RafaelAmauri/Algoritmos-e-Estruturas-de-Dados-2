class AquecimentoRecursivo
{
	public static void main(String[] args)
	{
		int quantMaiusculas = 0;
		boolean notFim;
		String entrada = new String();
		do
		{
			entrada = MyIO.readLine();
			notFim = notFim(entrada); // Se string for diferente de FIM, a execucao continua
			if(notFim)
			{
				quantMaiusculas = contarMaiusculas(entrada, 0, 0);
		
				MyIO.println(quantMaiusculas);
				quantMaiusculas = 0;
			}
		} while(notFim);
	}
	
	
	/* 
	 * Pega o valor decimal de cada caractere e avalia se eh maiusculo. Se for, soma 1 na variavel quantMaiusculas.
	 * Retorna o valor de quantMaiusculas
	 */
  	public static int contarMaiusculas(String entrada, int quantMaiusculas, int index)
	{
		if(index < entrada.length()) // Limitando para a funcao nao acessar um index fora da string
		{
			// Se o char for maior entre A e Z, aumenta em 1 a quantidade de maiusculas
		
			if( (int)entrada.charAt(index) >= 65 && (int)entrada.charAt(index) <= 90)
			{
				quantMaiusculas++;
			}
			quantMaiusculas = contarMaiusculas(entrada, quantMaiusculas, index+1);
		}
		return quantMaiusculas;	
	}
	
	/*
	 * Verifica se a palavra inserida pelo usuario eh igual a FIM. Se for diferente, retorna True
	 */
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
