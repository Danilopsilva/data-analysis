# data-analysis

<a> Leitor de arquivos em lote e gerador de relatorio de vendas </a>

# Premissas

- Criar diretório de entrada:
<p>  %HOMEPATH% / data / in. </p>

- Criar diretório de saída:
<p>  %HOMEPATH% / data / out. </p>

- Adicionar arquivo com o valor abaixo (arquivo deve ser do tipo .dat):

<pre>
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
</pre>

# Para Compilar e executar o projeto
- mvn spring-boot:run


# Obs: 
- O projeto foi limitado a ler apenas até 100 mil linhas, apenas para funcionar de maneira ilustrativa limitando o tamanho do arquivo a ser lido.


