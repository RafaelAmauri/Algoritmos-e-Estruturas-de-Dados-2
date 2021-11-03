#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isPalindromo(char entrada[], int index);

int main()
{
	char entrada[1000];
	bool notFim;

	do{
		scanf(" %[^\n]", entrada);
		notFim = strcmp(entrada, "FIM");
	
		if(notFim) //Se entrada for diferente de FIM, a execucao continua
		{
	
	
			if(isPalindromo(entrada, 0))
			{
				printf("SIM\n");
			}
			else
			{
				printf("NAO\n");
			}
		}
	}while(notFim);
	return 0;
}

/*
*	Para determinar se eh palindromo, verifica constantemente a letra na posicao index
* (comeca na ultima letra do char array) com a letra da posicao strlen(entrada) - index - 1. 
* index em 0 e aumenta a cada iteracao, o que faz com que  strlen(entrada) - index - 1 diminua 1 por vez
*/
bool isPalindromo(char entrada[], int index)
{
	bool is_palindromo = true;
	if(index < strlen(entrada)/2)
	{
		if(entrada[index] == entrada[strlen(entrada) - 1 - index])
		{
			is_palindromo = isPalindromo(entrada, index + 1);
		}
		else
		{
			is_palindromo = false;
		}
	}
	return is_palindromo;
}