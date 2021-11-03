#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int quantMaiusculas(char linha[], int index, int quant_maiusculas);
int main()
{
	char linha[100];
	int quant_maiusculas = 0;
	bool notFim;
	do
	{
		scanf(" %[^\n]", linha); // Ler a linha ate o final
		
		notFim = strcmp(linha, "FIM"); //Se a palavra nao for FIM, as maiusculas sao contadas e retornadas
		if(notFim)
		{
			quant_maiusculas = quantMaiusculas(linha, 0, 0);
			
			printf("%d\n", quant_maiusculas);
			quant_maiusculas = 0;
		}
	} while(notFim);
	
	return 0;
}

int quantMaiusculas(char linha[], int index, int quant_maiusculas)
{
	
	if(index < strlen(linha))
       	{
                                
		// Aqui cada caractere da linha eh convertida para seu valor ASC2.
                // Se for maior que 65 (A) e menor que 90 (Z), a letra eh contada

		if((int)linha[index] >= 65 && (int)linha[index] <= 90)
              	{
                	quant_maiusculas++;
       		}
		quant_maiusculas = quantMaiusculas(linha, index+1, quant_maiusculas);
       	}


	return quant_maiusculas;
}
