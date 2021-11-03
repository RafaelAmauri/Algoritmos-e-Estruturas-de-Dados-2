#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isPalindromo(char entrada[]);

int main()
{
	char entrada[1000];
	bool notFim;

	do{
		scanf(" %[^\n]", entrada);
		notFim = strcmp(entrada, "FIM");
	
		if(notFim) //Se entrada for diferente de FIM, a execucao continua
		{
	
			if(isPalindromo(entrada))
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
*	Para determinar se eh palindromo, verifica constantemente a letra na posicao i(comeca na ultima letra do char array) com a letra da posicao cont (comeca em 0 e aumenta a cada iteracao). 
*/
bool isPalindromo(char entrada[])
{
	bool isPalindromo = true;
	int cont = 0, tam = (strlen(entrada) - 1);
	for(int i = tam; i > tam/2; i--)
	{
		if(entrada[cont] != entrada[i])
		{
			isPalindromo = false;
		}
		cont++;
	}
	return isPalindromo;
}
