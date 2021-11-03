public class IsRecursivo
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
				isVogal = isVogal(entrada, 0); //Passando a string para os metodos
				isConso = isConso(entrada, 0);
				isInt = isInt(entrada, 0);
				isFloat = isFloat(entrada, 0, 0);
	
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
  	public static boolean isVogal(String entrada, int index)
	{	
		boolean isVogal = true;
		if(index < entrada.length())
		{
			if(entrada.charAt(index) != 'a' && entrada.charAt(index) != 'e' && entrada.charAt(index) != 'i'
				&& entrada.charAt(index) != 'o' && entrada.charAt(index) != 'u'
				&& entrada.charAt(index) != 'A' && entrada.charAt(index) != 'E' && entrada.charAt(index) != 'I'	
				&& entrada.charAt(index) != 'O' && entrada.charAt(index) != 'U')
				 
				// Checa entre todas vogais se o char da iteracao pertence a um deles. Se nao pertencer, a string
				// nao eh considerada contendo apenas vogais
			{
				isVogal = false;
			}
			else
			{
				isVogal = isVogal(entrada, index+1);
			}
		}
		return isVogal;
	}

	
	public static boolean isConso(String entrada, int index)
        {
                boolean isConso = true;
		if(index < entrada.length())
                {
			/* O caractere deve ser letra, e isso eh garantido na 1 linha do if, para 
			* o (int)char se posicionar entre 66 e 122 (entre A e z)
			* Depois disso, sao filtradas as vogais para garantir que o char eh uma consoante
			*/

			if( (int)entrada.charAt(index) >= 66 && (int)entrada.charAt(index) <= 122
			 && entrada.charAt(index) != 'E' && entrada.charAt(index) != 'I' && entrada.charAt(index) != 'O'
			 && entrada.charAt(index) != 'U' && entrada.charAt(index) != 'e' && entrada.charAt(index) != 'a'
			 && entrada.charAt(index) != 'i' && entrada.charAt(index) != 'o' && entrada.charAt(index) != 'u')
			 {
				isConso = isConso(entrada, index+1);
			 }
			else
			{
				isConso = false;
			}
                }
                return isConso;
        }
	
	/*
	 * Verifica se uma string eh composta apenas por numeros
	 */

	public static boolean isInt(String entrada, int index)
	{
		boolean isInt = true;
		if(index < entrada.length())
                {
			/* O caractere deve ser um numero, e isso eh garantido no if, para 
			* o (int)char se posicionar entre 48 e 67 (entre 0 e 9)
			*/

			if((int)entrada.charAt(index) >= 48 && (int)entrada.charAt(index) <= 57) 
			 {
				isInt = isInt(entrada, index+1);
			 }
			else
			{
				isInt = false;
			}
                }
		return isInt;
	}
	
	/*
	 * Verifica se uma string eh validada como um numero float
	 */
	public static boolean isFloat(String entrada, int index, int quantPontos)
	{
		boolean isFloat = true;
		if(index < entrada.length())
                {
			/* O caractere deve ser letra, e isso eh garantido na 1 linha do if, para 
			* o (int)char se posicionar entre 66 e 122 (entre A e z)
			* Depois disso, sao filtradas as vogais para garantir que o char eh uma consoante
			*/
			if((int)entrada.charAt(index) >= 48 && (int)entrada.charAt(index) <= 57)
			{
				isFloat = isFloat(entrada, index+1, quantPontos);
			}
			else if(entrada.charAt(index) == '.' || entrada.charAt(index) == ',')
			{
				isFloat = isFloat(entrada, index+1, quantPontos+1);
			}
			else
			{
				isFloat = false;
			}
		}
		else if(quantPontos > 1)
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
