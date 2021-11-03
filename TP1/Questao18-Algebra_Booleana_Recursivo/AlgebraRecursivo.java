public class AlgebraRecursivo
{
	public static void main(String[] args)
	{	
		boolean notFim;
		int quantNumeros;
		String entradaString = new String();
		int tam;
		char[] entradaChar = new char[1000];

		do{
			quantNumeros = MyIO.readInt();
			notFim = notFim(quantNumeros);
			if(notFim)
			{
				String[] listaValores = lerValores(quantNumeros);
				entradaString = MyIO.readLine();
				str2char(entradaString, entradaChar);
				tam = entradaString.length();
				tam = removerEspacos(entradaChar, tam);
				trocar(entradaChar, listaValores, tam);
				char resp = algebraBooleana(entradaChar, tam, 0 , tam);
				MyIO.println(resp);
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

	public static int removerEspacos(char[] entradaChar, int tam)
	{
		for(int i = 0; i < tam; i++)
		{
			if (entradaChar[i] == ' ' || entradaChar[i] == ',')
        		{
				for(int j=i; j < tam-1; j++)
				{
						entradaChar[j] = entradaChar[j+1];
				}
				i--;
				entradaChar[tam] = '\0';
				tam--;
                	}
		}
		return tam;
	}


	public static void im(char[] entradaChar, int tam)
	{
		for(int i = 0; i < tam; i++)
		{
			MyIO.print(entradaChar[i]);
		}
		MyIO.println("\n");
	}


	/*
	 * Troca as letras pelos valores binarios
	 */

	public static void trocar(char[] entradaChar, String[] listaValores, int tam)
	{
		int index = 0;
		for(int i = 0; i < listaValores.length; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				if((int)entradaChar[j] == index + 65 )
				{
					entradaChar[j] = (char)listaValores[index].charAt(0);
				}
			}
			index++;
		}
		return ;
	}
	
	/*
	 * Le os valores binarios que sao apresentados na entrada
	 */	
	public static String[] lerValores(int quantValores)
	{
		String[] listaValores = new String[quantValores];
		for(int i = 0; i < quantValores; i++)
		{
			listaValores[i] = MyIO.readString();
		}
		return listaValores;
	}
	
	/*
	 * Metodo booleano not
	 */
	public static char not(char[] valores, int quantValores)
	{
		char retorno = '1';
                for(int i = 0; i < quantValores; i++)
                {

                        if(valores[i] == '1')
                        {
                                retorno = '0';
                                i = quantValores;
                                valores = new char[10];
                        }
                }
                return retorno;

	}

	/*
	 * Metodo booleano and
	 */
	public static char and(char[] valores, int quantValores)
	{
		char retorno = '1';
			for(int i = 0; i < quantValores; i++)
			{

				if(valores[i] == '0')
				{
						retorno = '0';
						i = quantValores;
						valores = new char[10];
				}
			}
		return retorno;
	}
	/*
	 * Metodo booleano or
	 */
	
	public static char or(char[] valores, int quantValores)
	{
		char retorno = '0';
		for(int i = 0; i < quantValores; i++)
		{
			
			if(valores[i] == '1')
			{
				retorno = '1';
				i = quantValores;
				valores = new char[10];
			}
		}
		return retorno;
	}


	/*
	* Realiza a solucao da expressao booleana. Para isso, procura um ')', salva sua posicao em i e depois
	* procura um '('. Depois olha qual caractere est� antes de '('. Se for 'd', sabe-se que eh um and, se for 'r'
	* eh um or e se for um 't', eh um not. Com isso, ele le os valores entre '(' e ')' e armazena em char[] valores, depois manda
	* o array para a funcao correspondente.
	*/

	public static char algebraBooleana(char[] entradaChar, int tam, int indexI, int indexJ)
	{
		char substituir;
		char[] valores = new char[10];
		int quantValores = 0;
		
		if(indexI < tam)
		{
			if(entradaChar[indexI] == ')')
			{
				if(entradaChar[indexJ] == '(')
				{
					// Achei um not
					if(entradaChar[indexJ-1] == 't')
					{	
						valores[quantValores] = entradaChar[indexJ+1];
						quantValores++;

						substituir = not(valores, quantValores);
						entradaChar[indexJ-3] = substituir;
						for(int k = 0; k < quantValores+4; k++)
						{
							for(int w = indexJ-2; w < tam -1; w++)
							{
								entradaChar[w] = entradaChar[w+1];
							}
							entradaChar[tam-k] = '\0';
						}
						tam -= quantValores+4;
						valores = new char[10];
						algebraBooleana(entradaChar, tam, 0, tam);
					}
					
					
					// Achei um and
					else if(entradaChar[indexJ-1] == 'd')
					{
						for(int k = indexJ+1; k < indexI; k++)
						{
							valores[quantValores] = entradaChar[k];
							quantValores++;
						}
						substituir = and(valores, quantValores);
						entradaChar[indexJ-3] = substituir;

						for(int k = 0; k < quantValores+4; k++)
						{
							for(int w = indexJ-2; w < tam-1; w++)
								{
									entradaChar[w] = entradaChar[w+1];
								}
							entradaChar[tam-k] = '\0';
						}	
											
						tam -= quantValores+4;
						valores = new char[10];
						algebraBooleana(entradaChar, tam, 0, tam);
					}

					// Achei um or
					else if(entradaChar[indexJ-1] == 'r')
					{
						for(int k = indexJ+1; k < indexI; k++)
						{
								valores[quantValores] = entradaChar[k];
								quantValores++;
						}
						substituir = or(valores, quantValores);
						entradaChar[indexJ-2] = substituir;

						for(int k = 0; k < quantValores+3; k++)
						{
							for(int w = indexJ-1; w < tam-1; w++)
							{
								entradaChar[w] = entradaChar[w+1];
							}
							entradaChar[tam-k] = '\0';
						}
						tam -= quantValores+3;
						valores = new char[10];
						algebraBooleana(entradaChar, tam, 0, tam);
					}
				}
				else if(indexJ > 0)
				{
					valores = new char[10];
					algebraBooleana(entradaChar, tam, indexI, indexJ-1);
				}
			}
			else if(indexI < tam)
			{
			valores = new char[10];
			algebraBooleana(entradaChar, tam, indexI+1, tam);
			}
		}
		return entradaChar[0];
	}


	/*
	* Sorteia 2 caracteres aleatorios e procura na string um dos sorteados. Se achar, os troca pelo outro sorteado. 
	* Em ultimo caso (caso nao corresponda a nenhum dos sorteados), printa o proprio caractere, inalterado
    */
	public static void alterar(String entradaString)
	{

		
		return;
    }
        
    	/*
         * Verifica se a palavra inserida pelo usuario eh igual a FIM. Se for diferente, retorna True
         */
	public static boolean notFim(int quantNumeros)
	{
		boolean notFim = true;
		if(quantNumeros == 0)
		{
			notFim = false;
		}
                return notFim;
    }

}
