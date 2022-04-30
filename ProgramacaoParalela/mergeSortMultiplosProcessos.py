from multiprocessing import shared_memory
from multiprocessing import Semaphore
import os

freeprocessos = shared_memory.ShareableList([0])# Número de processos livres

mutex = Semaphore() 		# Região crítica de todos os processos

semProcesso = shared_memory.ShareableList([None]) # Variável global compartilhada para retornar o semáforo de um processo

runProcesso    = Semaphore()# Semáforo de bloqueio de todos os processos workers
waitParameters = Semaphore()# Semáforo de bloqueio da linha de 
							# execução que liberou o processo

gl = shared_memory.ShareableList([None])
gh = shared_memory.ShareableList([None]) # Variáveis globais de comunicação com os processos

sai = shared_memory.ShareableList([False]) # Quando true, esta variável indica que o processo deve encerrar a execução

def esperaProcesso(semaforos, semaforo):
	global freeprocessos
	global mutex

	# Espera o processo terminar
	semaforos[semaforo].acquire()

	# Atualiza o contador
	mutex.acquire()
	freeprocessos[0] += 1
	mutex.release()

def tentaProcesso(l, h):
	global dicionariodeSemaforos
	global freeprocessos
	global mutex
	global semProcesso
	global runProcesso
	global waitParameters
	global gl
	global gh

	semaforo	= None	
	mutex.acquire()
	if(freeprocessos[0]>0):
		freeprocessos[0] -= 1
		
		# Prepara os argumentos 
		# para o processo
		gl[0] = l
		gh[0] = h

		# Libera um processo worker
		runProcesso.release()

		# Aguarda o worker informar 
		# qual o semáforo dele
		waitParameters.acquire()
		semaforo = semProcesso[0]
	mutex.release()
	
	return (dicionariodeSemaforos, semaforo)

def merge(vetor, p, meio, u):
  L = vetor[p:meio+1]
  R = vetor[meio+1:u+1]
  K = p
  while(len(L)>0 and len(R)>0):
    if(int(L[0]) < int(R[0])):
      vetor[K] = L.pop(0)
    else:
      vetor[K] = R.pop(0)
    K += 1
  while(len(L)>0):
    vetor[K] = L.pop(0)
    K += 1
  while(len(R)>0):
    vetor[K] = R.pop(0)
    K += 1

def mergeSortWorker(vetor, semaforos, semaforo):
	global semProcesso # Variáveis global de retorno do semaforo do processo
	global runProcesso
	global waitParameters
	global gl
	global gh
	global sai

	semaforos[semaforo].acquire()
	
	while(True):
		runProcesso.acquire() # Aguarda até o pai liberar a execução
		
		if(sai[0]):	# Se eh para encerrar a execução
			exit(0)
		
		l = gl[0]
		h = gh[0]
		semProcesso[0] = semaforo
		waitParameters.release()# Já copiou os argumentos e configurou
								# o valor de retorno

		mergeSort(vetor, l, h)
		
		semaforos[semaforo].release()

# Teste com 5 6 3 7 2 8 1 9 0 4

def mergeSort(vetor, p=None, u=None):
	if(p == None):
		p = 0

	if(u == None):
		u = len(vetor)-1

	if(p<u):
		meio = int((p+u)/2)
		
		(semaforos, semL) = tentaProcesso(p, meio)
		
		# Se não tinha um thread para fazer, então
		# esta linha de execução fará
		if(semL == None):
			mergeSort(vetor, p, meio)
		
		# O ramo direito executa nesta linha de execução mesmo
		mergeSort(vetor, meio+1, u)

		# Se ordenou o ramo esquerdo no thread, então espere ele terminar
		if(semL != None):
			esperaProcesso(semaforos, semL)
			
		merge(vetor, p, meio, u)

print("Informe um vetor de números : ", end='')

vetor = input().split()

vetor = shared_memory.ShareableList(list(map(int, vetor)))

print(vetor)

runProcesso.acquire()	# Todos os processos vão bloquear nesse semáforo e só
					# são liberados quando tem trabalho a ser feito por 
					# eles


semPai = None
waitParameters.acquire()	# Quando tentar fazer o acquire novamente vai 
							# bloquear este processo e esperar que um filho
							# libere. Note que o código em que ele espera
							# um filho está no mutex. Somente um filho vai
							# rodar e liberar o pai

MaxProcessos = 4
freeprocessos[0] = MaxProcessos
listadeProcessos = []
dicionariodeSemaforos = {}

# Eh preciso que todos os semáforos estejam criados antes de criar os 
# processos para que eles tenham acesso a todos eles
for i in range(0, MaxProcessos):
	dicionariodeSemaforos[i] = Semaphore()

for i in range(0, MaxProcessos):
	novoWorker = os.fork()
	if(novoWorker == 0): # Processos filhos vão executar o mergeSortWorker
		mergeSortWorker(vetor, dicionariodeSemaforos, i)

	listadeProcessos.append(novoWorker) #salva o pid

mergeSort(vetor)

sai[0] = True
for i in range(0, len(listadeProcessos)):
	runProcesso.release()

for i in listadeProcessos:
	os.wait()

print(vetor)

# Libera a memória compartilhada
vetor.shm.unlink()

freeprocessos.shm.unlink()
semProcesso.shm.unlink()
gl.shm.unlink()
gh.shm.unlink()
sai.shm.unlink()
