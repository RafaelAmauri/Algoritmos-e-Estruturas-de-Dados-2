public class Is
{
	public static void main(String[] args)
	{
		boolean notFim, isVogal, isConso, isInt, isFloat;
		String entrada = new String();
		do
		{
			entrada = MyIO.readLine();
			notFim = notFim(entrada); // Se string for diferente de FIM, a execucao continua
			if(notFim)
			{
				isVogal = isVogal(entrada); //Passando a string para os metodos
				isConso = isConso(entrada);
				isInt = isInt(entrada);
				isFloat = isFloat(entrada);
	
				if(isVogal)
				{
					MyIO.print("SIM ");
				}
				else
				{
					MyIO.print("NAO ");
				}

				if(isConso)
                                {
                                        MyIO.print("SIM ");
                                }
                                else
                                {
                                        MyIO.print("NAO ");
                                }

				if(isInt)
                                {
                                        MyIO.print("SIM ");
                                }
                                else
                                {
                                        MyIO.print("NAO ");
                                }
				
				if(isFloat)
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
	 * Procura por nao-vogais na string. se achar, retorna false.
	 */
  	public static boolean isVogal(String entrada)
	{	
		boolean isVogal = true;
		for(int i = 0; i < entrada.length(); i++)
		{
			if(entrada.charAt(i) != 'a' && entrada.charAt(i) != 'e' && entrada.charAt(i) != 'i'
				&& entrada.charAt(i) != 'o' && entrada.charAt(i) != 'u'
				&& entrada.charAt(i) != 'A' && entrada.charAt(i) != 'E' && entrada.charAt(i) != 'I'
			 	&& entrada.charAt(i) != 'O' && entrada.charAt(i) != 'U') 
				// Checa entre todas vogais se o char da iteracao pertence a um deles. Se nao pertencer, a string
				// nao eh considerada contendo apenas vogais
			{
				isVogal = false;
				break;
			}
		}
		return isVogal;
	}

	
	public static boolean isConso(String entrada)
        {
		char[] consoantes = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z' };
                boolean isConso = true;
                int naoConsoante;
		for(int i = 0; i < entrada.length(); i++)
                {
			naoConsoante = 0;
                        for(int j = 0; j < consoantes.length; j++)
			{
				// Percore o entrada.charAt(i) por todo array de consoantes. A cada consoante diferente do char, a variavel
				// naoConsoante aumenta em 1. Se seu valor for igual ao do tamanho do array de consoantes, sabemos que a
				// letra nao eh consoante (pois foi diferente de todas).
                        	if(entrada.charAt(i) != consoantes[j])
				{
					naoConsoante++;
				}
			}
			
			if(naoConsoante == consoantes.length)
			{
				isConso = false;
				break;
			}
                }
                return isConso;
        }
	
	/*
	 * Verifica se uma string eh composta apenas por numeros
	 */

	public static boolean isInt(String entrada)
	{
		char[] numeros = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		boolean isInt = true;
		int quantNaoInteiros;
		for(int i = 0; i < entrada.length(); i++)
                {
                        quantNaoInteiros = 0;
                        for(int j = 0; j < numeros.length; j++)
                        {
				// Verifica se a letra na posicao i eh um numero. Para isso, segue a mesma logica de validacao da funcao acima
                                if(entrada.charAt(i) != numeros[j])
				{
                                        quantNaoInteiros++;
                                }
                        }

                        if(quantNaoInteiros == numeros.length)
                        {
                                isInt = false;
                                break;
                        }
                }

		return isInt;
	}
	
	/*
	 * Verifica se uma string eh validada como um numero float
	 */
	public static boolean isFloat(String entrada)
	{
		char[] numeros = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',' };
                boolean isFloat = true;
                int quantNaoFloats;
		int quantPontos = 0;
		
		// Tambem segue a mesma logica de validacao da funcao isConso(), mas essa tambem conta a
		// quantidade de pontos '.' ou ',' na string. Um numero real nao pode ter uma quantidade de pontos diferente de 1,
		// Por isso existe a variavel quantPontos. Se ela for maior que 1, a funcao retorna false, mesmo se todos outros caracteres
		// forem numeros
                for(int i = 0; i < entrada.length(); i++)
                {
                        quantNaoFloats = 0;
			if(entrada.charAt(i) == '.' || entrada.charAt(i) == ',')
			{
				quantPontos++; // Se cair aqui eh pq o char na posicao i eh um ponto, entao nao tem porque testar com o array
				continue; 
			}

                        for(int j = 0; j < numeros.length; j++)
                        {

                                if(entrada.charAt(i) != numeros[j])
                                {
                                        quantNaoFloats++;
                                }
                        }

                        if(quantNaoFloats == numeros.length)
                        {
                                isFloat = false;
				break;
                        }
                }

		if(quantPontos > 1)
		{
			isFloat = false;
		}
		
                return isFloat;
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

