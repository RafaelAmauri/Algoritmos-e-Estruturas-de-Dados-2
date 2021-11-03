class Aquecimento
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
				quantMaiusculas = contarMaiusculas(entrada);
		
				MyIO.println(quantMaiusculas);
				quantMaiusculas = 0;
			}
		} while(notFim);
	}
	
	
	/* 
	 * Pega o valor decimal de cada caractere e avalia se eh maiusculo. Se for, soma 1 na variavel quantMaiusculas.
	 * Retorna o valor de quantMaiusculas
	 */
  	public static int contarMaiusculas(String entrada)
	{
		int quantMaiusculas = 0;
	
		// Se o char for maior entre A e Z, aumenta em 1 a quantidade de maiusculas
		for(int i = 0; i < entrada.length(); i++)
			if( (int)entrada.charAt(i) >= 65 && (int)entrada.charAt(i) <= 90)
				quantMaiusculas++;


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
