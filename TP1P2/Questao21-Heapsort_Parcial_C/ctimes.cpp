#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

#define bool   short
#define true   1
#define false  0


struct Time
{
    char nome[1000];
    char apelidos[1000];
    char estadio[100];
    char tecnico[100];
    char membros[100];
    char liga[100];
    char nomeArquivo[100];

    int capacidade;
    int fundacaoDia, fundacaoMes, fundacaoAno;
   
    long int paginaTam; 
};

typedef struct Time Time;


Time* new_time(char nome[], char apelidos[], char estadio[], char tecnico[],
	       char liga[], char nomeArquivo[], int capacidade, 
	       int fundacaoDia, int fundacaoMes, int fundacaoAno, long int paginaTam);



void heapSort(Time** times, int* movimentacoes, int* comparacoes, int tam);

void imprimir(Time* time);

/*
 * Conjunto de funcoes usadas para achar as datas de fundacao dos times
 */

int* filtrarData(char dataString[]);


/*
 * Retorna todos as palavras da string que estao entre as strings st1 e str2
 */

char* retornarEntreTags(char string[], char str1[], char str2[]);

/*
 * Recebe duas strings. Remove todas ocorrencias da segunda 
 * string na primeira e retorna a primeira
 */

char* removerCaracteres(char string[], char removidos[]);

/*
 * Recebe uma string e dois caracteres. Remove tudo que 
 * estiver entre tag1 e tag2 e retorna a string tratada
 */

char* removerTags(char string[], char tag1[], char tag2[]);


/* 
 * Retorna um valor bool informando se
 * substring eh uma substring de string
 */
bool isSubstring(char string[], char substring[]);




Time* ler(char nomeArquivo[], Time* time);


int main(void)
{   
	Time* times[100];
    FILE* arq;
    char nomeArquivo[100];
    bool notFim = 1;
	int index = 0;
	FILE* log;   
	
	time_t inicio = time(NULL);
	
	log = fopen("./651047_heapsort.txt", "w");
    do
    {
	    memset(nomeArquivo, '\0', sizeof(nomeArquivo));
	    //Pega o nome do arquivo do time
	    fgets(nomeArquivo, 100, stdin);
	    strtok(nomeArquivo, "\n");
	    notFim = strcmp(nomeArquivo, "FIM"); //Se a palavra nao for FIM, as maiusculas sao contadas e retornadas
				
		int quantElementos;
		if(notFim)
        {
			times[index] = ler(nomeArquivo, times[index]);
			index++;	
		}

    } while(notFim);
	
	int movimentacoes = 0, comparacoes = 0;
	char nomeTime[100];
	
	heapSort(times, &movimentacoes, &comparacoes, index-1);
	
	
	for(int i = 0; i < 10; i++)
		imprimir(times[i]);

	time_t fim = time(NULL)-inicio;
	double tempo = (double)fim;
	

	fprintf(log, "651047\t%d\t%d\t%lf", comparacoes, movimentacoes, tempo);
	
	return 0;
}


void heapSort(Time** times, int* movimentacoes, int* comparacoes, int tam)
{	
	int i = tam/2, pai, filho;
	Time* t;
	while(1)
	{
		if(i > 0)
		{
			i--;
			t = times[i];
		}
		else
		{
			tam--;
			if(tam <= 0) return;
			
			*movimentacoes += 2;
			t = times[tam];
			times[tam] = times[0];
		}

		pai = i;
		filho = i*2 +1;
		while(filho < tam)
		{
			*comparacoes += 1;
			if(filho+1 < tam && strcmp(times[filho+1]->estadio, times[filho]->estadio) > 0)
				filho++;
			
			*comparacoes += 1;
			if(strcmp(times[filho]->estadio, t->estadio) > 0)
			{
				*movimentacoes += 1;
				times[pai] = times[filho];
				pai = filho;
				filho = pai*2 + 1;
			}
			else
			{
				break;
			}
		}
		*movimentacoes += 1;
		times[pai] = t;
	}
}

int* filtrarData(char dataString[])
{
	// Salvar dois index: index1, index2. Usar strncpy pra cortar e armazenar em data[1] e data[2]

	/*
	* data[0] eh o dia
	* data[1] eh o mes
	* data[2] eh o ano
	*/
	int data[3]; 
	int* ponteiroData = data;
	char numeroChar[1000];


	if(isSubstring(dataString, "&#59"))
	{
	
		strcpy(dataString, retornarEntreTags(dataString, "d", "#59;"));
		strcpy(dataString, removerTags(dataString, "#", ";"));
		strcpy(dataString, removerCaracteres(dataString, ","));
			
		for(int i = 0; i < strlen(dataString); i++)
			if(dataString[i] == '&')
				dataString[i] = ' ';
	    

		dataString[strlen(dataString)-1] = '\0';
	}
	
	
	strcpy(dataString, removerCaracteres(dataString, ","));
	strcpy(dataString, removerCaracteres(dataString, "d"));
	
	if(isSubstring(dataString, "&") && isSubstring(dataString, ";"))
		strcpy(dataString, removerTags(dataString, "&", ";"));
	
	
	if(strlen(dataString) == 4)
	{
		data[0] = 0;
		data[1] = 0;
		data[2] = atoi(dataString);
		return ponteiroData;
	}

	int index1 = 0, index2 = strlen(dataString)-1;
	for(int i = 0; i < strlen(dataString); i++)
		if(dataString[i] == '-' || dataString[i] == '/' || dataString[i] == ' ')
		{
			index1 = i;
			for(int j = i+1; j < strlen(dataString); j++)
				if(dataString[j] == '-' || dataString[j] == '/' || dataString[j] == ' ')
				{
					index2 = j;
					j = strlen(dataString);
					i = strlen(dataString);
				}	
		}	



		memset(numeroChar, '\0', sizeof(numeroChar)); // Zerando a string para impedir concatenacoes erradas
		// String antes do primeiro -
		
		strncpy(
			numeroChar,
			dataString,
			index1+1  //NAO DELETAR O + 1 PELAMOR DE DEUS SENAO VAI VOLTAR A DAR SEGMENTATION FAULT NA DATA E EU TO LOUCO JA 
			);
		
		data[0] = atoi(numeroChar);
		memset(numeroChar, '\0', sizeof(numeroChar)); // Zerando a string para impedir concatenacoes erradas


		// String entre as duas tags -
		strncpy(
			numeroChar,
			dataString + index1 + 1,
			index2 - index1
			);

		data[1] = atoi(numeroChar);
		memset(numeroChar, '\0', sizeof(numeroChar));


		// String depois do segundo -
		strncpy(
			numeroChar,
			dataString + index2 + 1,
			strlen(dataString) - index2
			);

		data[2] = atoi(numeroChar);
		memset(numeroChar, '\0', sizeof(numeroChar));
	
		
	if(data[0] > 1000)
	{
		int aux = data[0];
		data[0] = data[2];
		data[2] = aux;
	}

	else if(data[1] > 1000)
	{
		int aux = data[1];
		data[1] = data[2];
		data[2] = aux;
	}
	
	/* Aqui confirmaremos que a data esta no formato dia/mes/ano.
	* A variavel <dataString> vai conter todo o conteudo que que esta entre "Founded" e "</td"
	* A logica eh ver se nomes de meses sao substrings de <numeroChar>
	* Se for, eh pq o mes que eh substring deve ser o valor numerico da variavel <data[1]>.
	* Caso o valor numerico de <mes> seja diferente do valor do mes no mundo real, ele
	* eh trocado com a variavel dia (Ex: <data[1]> = 3 e  subString(linha, "September") retorna True. Isso
	* nao pode acontecer, pois setembro eh o mes 9. Entao, concluimos que 3 so pode ser o dia! <data[0]> assume o valor
	* de <data[1]>, e <data[1]> vira 9)
	*/


	

	// Matriz com meses
	char meses[12][12] = {
				{"January"}, {"February"}, {"March"}, {"April"}, {"May"}, {"June"},
				{"July"}, {"August"}, {"September"}, {"October"}, {"November"}, {"December"}
			     };


	for(int i = 0; i < 12; i++)

		if(isSubstring(dataString, meses[i])) 
		{
			if(data[0] == i+1 && data[1] != 0)
			{	
				data[0] = data[1];
				data[1] = i+1;
			}
			else if(data[0] == 0)
			{
				data[0] = data[1];
				data[1] = i+1;
			}
			else
			{
				data[1] = i+1;
			}
		}


	return ponteiroData;
}


char* retornarEntreTags(char string[], char str1[], char str2[])
{

        bool isStr1 = false, isStr2 = false;
	char *stringEntreTags = (char*)malloc(10000 * sizeof(char));
	
        for(int i = 0; i < strlen(string); i++)
     	{ 
		if(string[i] == str1[0])
                {
                        isStr1 = true;
                        for(int j = 0; j < strlen(str1); j++)
			{
				if(string[i+j] != str1[j])
				{
					isStr1 = false;
				}
			}
		}	
		if(isStr1)
		{
			for(int j = i+strlen(str1); j < strlen(string); j++)
                         
				if(string[j] == str2[0])
				{
					isStr2 = true;
					for(int k = 0; k < strlen(str2); k++)
					{
						if(string[j+k] != str2[k])
						{			
							isStr2 = false;			
						}
					}
					
					if(isStr1 && isStr2)
					{
						strncpy(stringEntreTags, 
							string + i + strlen(str1),
							j - i - strlen(str1)
							);
						
						strcpy(stringEntreTags, removerCaracteres(stringEntreTags, "\""));
						
						if(isSubstring(stringEntreTags, "&#91"))
						{
							strcpy(stringEntreTags, removerTags(stringEntreTags, "&#91", "&#93;"));
						}
						return stringEntreTags;
					
					}
				}

		}
	}
	if(!isStr1 && !isStr2)
	{
		strcpy(stringEntreTags, string);
		return stringEntreTags;
	}
	return stringEntreTags;
}


char* removerCaracteres(char string[], char parteRemovida[])
{
	char* p = string;
	bool isParteRemovida;

	for(int i = 0; i < strlen(string); i++)
		 

		// Caso ache a primeira letra de parteRemovida, sera ativado um 
		// loop <for> para ver se aquela letra corresponde à parte a ser removida
		// ou se for apenas uma letra de outra palavra
		
		if(string[i] == parteRemovida[0])
		{
			isParteRemovida = true;
			for(int j = 0; j < strlen(parteRemovida); j++)	
				

				// Comparando com o resto da string. Se for diferente, a <isParteRemovida>
				// vira <false>, falhando no if que vem em seguida e voltando para o loop
				
				if(string[i+j] != parteRemovida[j])
					
					isParteRemovida = false;
			
			
			if(isParteRemovida)
			{	
				for(int j = i; j < strlen(string)-1; j++)
					
					string[j] = string[j+1];

				
				string[strlen(string) - 1] = '\0';
				i--;
			}	
		}
	return p;
}


char* removerTags(char string[], char tag1[], char tag2[])
{
	bool isTag1, isTag2;
	char* ponteiroString = string;

	for(int i = 0; i < strlen(string); i++)
	{	 
        isTag1 = false; 
        isTag2 = false;
		
        if(string[i] == '<' &&
                   string[i+1] == '/' &&
                   string[i+2] == 't' &&
                   string[i+3] == 'd' &&
                   string[i+4] == '>')
                   {
                        continue;
                   }

        if(string[i] == tag1[0])
        {
            isTag1 = true;
            for(int j = 0; j < strlen(tag1); j++)
            {
                if(tag1[j] != string[i+j])
                {
                    isTag1 = false;
                    j = strlen(tag1);
                }
            }
        }
		if(isTag1)
		{
			for(int j = i+strlen(tag1); j < strlen(string); j++)
			{
				if(string[j] == tag2[0])
				{
                    isTag2 = true;
                    for(int k = 0; k < strlen(tag2); k++)
                    {
                        if(tag2[k] != string[k+j])
                        {
                            isTag2 = false;
                            k = strlen(tag2);
                        }
                    }
                }
                if(isTag1 && isTag2)
                {
					for(int k = 0; k < j-i+strlen(tag1); k++)
					{
						for(int x = i; x < strlen(string)-1; x++)
						{
							string[x] = string[x+1];
						}

					    string[strlen(string)-1] = '\0';
					}
					i--;
					j = strlen(string);	
                }            
            }
        }
	}
	ponteiroString = string;	
	return ponteiroString;
}

bool isSubstring(char string[], char substring[])
{       
        bool isContido = false;

        for(int i = 0; i < strlen(string); i++)
               
                if(string[i] == substring[0])
		{
                        isContido = true;
                        for(int j = 0; j < strlen(substring); j++)
                              
                                if(string[i+j] != substring[j])
                                {       
                                        j = strlen(substring);
                                        isContido = false;
                                }
                          
                        if(isContido)
                                i = strlen(string);
		}
        
        return isContido;
}

void imprimir(Time* time)
{
	printf("%s ## %s ## %02d/%02d/%02d ## %s ## %d ## %s ## %s ## %s ## %ld ## \n", time->nome, time->apelidos, time->fundacaoDia, time->fundacaoMes, time->fundacaoAno, time->estadio, time->capacidade, time->tecnico, time->liga, time->nomeArquivo, time->paginaTam);
}




Time* ler(char nomeArquivo[], Time* time)
{

    FILE* arq;
    char linhaExtra[10000];
    char linha[100000];
    bool notFim = 1;
    long int tamPag;


		    arq = fopen(nomeArquivo, "r");
		    
		    fseek(arq, 0, SEEK_END);
		    tamPag = ftell(arq);
		    fseek(arq, 0, SEEK_SET);
			
		    fscanf(arq, " %[^\n]",  linha);	
		    fgetc(arq);

	            memset(linha, '\0', sizeof(linha));
    
  		    // Ler a linha ate achar uma que comeca com <table. A que comeca com <table eh a com os dados
   		    while(!feof(arq))
		    {
			    memset(linha, '\0', sizeof(linha));
			    
			    fscanf(arq, " %[^\n]", linha);

			    // Se a linha tiver a palavra <table nela, essa sera a linha com os atributos
			    if(isSubstring(linha, "infobox vcard"))
			    {
					
				    memset(linhaExtra, '\0', sizeof(linhaExtra));

				    
				    while(!isSubstring(linha, "League"))
				    {
			    		fscanf(arq, " %[^\n]", linhaExtra);
					strcat(linha, linhaExtra);
				    }
				
				    char nome[1000], apelidos[1000], tecnico[100], estadio[100];
				    char capacidadeString[1000], data[200], liga[100];
		    		    int dia, mes, ano, capacidade;
				
				    
				    removerTags(linha, "<", ">");    	
				    
				    strcpy(nome, retornarEntreTags(linha, "Full name", "</td>"));
					strcpy(nome, removerTags(nome, "&", ";"));
					strcpy(nome, removerCaracteres(nome, ";"));

				    strcpy(apelidos, retornarEntreTags(linha, "Nickname(s)", "</td>"));
                    strcpy(apelidos, removerTags(apelidos, "&", ";"));

					//Alguns apelidos estao com espaco na frente do nome
					while(apelidos[0] == ' ')
					{
						for(int i = 0; i < strlen(apelidos)+1; i++)
							apelidos[i] = apelidos[i+1];
						
						tecnico[strlen(apelidos)-1] = '\0';
					}
				    
				    strcpy(estadio,  retornarEntreTags(linha, "Ground", "</td>"));
				    strcpy(estadio, removerTags(estadio, "&", ";"));
					
				    strcpy(capacidadeString, retornarEntreTags(linha, "Capacity", "</td>")); 
				    
				    strcpy(capacidadeString, removerTags(capacidadeString, "&", ";"));
				    strcpy(capacidadeString, removerCaracteres(capacidadeString, " "));
				    strcpy(capacidadeString, removerCaracteres(capacidadeString, "."));
				    strcpy(capacidadeString, removerCaracteres(capacidadeString, ","));
				    capacidade = atoi(capacidadeString);
					

					
					if(isSubstring(linha, "Manager"))
				    	strcpy(tecnico,  retornarEntreTags(linha, "Manager", "</td>"));
					
					else if(isSubstring(linha, "Coach"))
				        strcpy(tecnico, retornarEntreTags(linha, "Coach", "</td>"));
					
				    else if(isSubstring(linha, "Head coach"))
				    	strcpy(tecnico,  retornarEntreTags(linha, "Head coach", "</td>"));
				    

					
						
					strcpy(tecnico, removerTags(tecnico, "&", ";"));
					strcpy(tecnico, removerCaracteres(tecnico, ";"));		
					
					// Alguns tecnicos estao com espaco na frente do nome
					while(tecnico[0] == ' ')
					{
						for(int i = 0; i < strlen(tecnico)+1; i++)
							tecnico[i] = tecnico[i+1];
						
						tecnico[strlen(tecnico)-1] = '\0';
					}


				    strcpy(liga, retornarEntreTags(linha, ">League", "</td>"));
				    strcpy(liga, removerTags(liga, "&", ";"));
					strcpy(liga, removerCaracteres(liga, ";"));


				   //Pegar tudo entre Founded e &#59 (dia) e &#59 e &#160 (mes e ano)
				    strcpy(data, retornarEntreTags(linha, "Founde", "</td>"));
				    	
				    int* dataFormatada = filtrarData(data);

				    dia = dataFormatada[0];
				    mes = dataFormatada[1];
				    ano = dataFormatada[2];
				    
				    
				    time = new_time(nome, apelidos, estadio, tecnico,
  			             	  liga, nomeArquivo, capacidade,
               				dia, mes, ano, tamPag);
				    
		    		fseek(arq, 0, SEEK_END);
		 	    }		    
		    }
		    fclose(arq);
			return time;
}


Time* new_time(char nome[], char apelidos[], char estadio[], char tecnico[], 
	       char liga[], char nomeArquivo[], int capacidade, 
	       int fundacaoDia, int fundacaoMes, int fundacaoAno, long int paginaTam)

{ 
	Time* p = (Time*)malloc(sizeof(Time));
  
	strcpy(p->nome,        nome    );
  	strcpy(p->apelidos,    apelidos);
  	strcpy(p->estadio,     estadio );
	strcpy(p->tecnico,     tecnico );
  	
	strcpy(p->liga,        liga       );
	strcpy(p->nomeArquivo, nomeArquivo);

  	p->capacidade  = capacidade;
	p->fundacaoDia = fundacaoDia;
  	p->fundacaoMes = fundacaoMes;
 	p->fundacaoAno = fundacaoAno;
  	p->paginaTam   = paginaTam;
   	
	return p;
}
