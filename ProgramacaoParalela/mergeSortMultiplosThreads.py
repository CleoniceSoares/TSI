import threading

freethreads = 0 						# Número de threads livres
mutex = threading.Lock() 				# Região crítica de todos os threads
semThread = None 						# Variável global para retornar o semáforo de um thread
runThread = threading.Semaphore()		# Semáforo de bloqueio de todos os
										# threads workers
waitParameters = threading.Semaphore()	# Semáforo de bloqueio da linha de 
										# execução que liberou o thread
gl = None 								# Variáveis globais de comunicação com os threads
gh = None

sai = False # Quando true, esta variável indica que o thread deve encerrar a execução

def esperaThread(semaforos, semaforo):
	global freethreads
	global mutex

	# Espera o thread terminar
	semaforos[semaforo].acquire()

	# Atualiza o contador
	mutex.acquire()
	freethreads += 1
	mutex.release()

def tentaThread(l, h):
	global dicionariodeSemaforos
	global freethreads
	global mutex
	global semThread
	global runThread
	global waitParameters
	global gl
	global gh

	semaforo	= None	
	mutex.acquire()
	if(freethreads>0):
		freethreads -= 1
		
		# Prepara os argumentos 
		# para o thread
		gl = l
		gh = h

		# Libera um thread worker
		runThread.release()

		# Aguarda o worker informar 
		# qual o semáforo dele
		waitParameters.acquire()
		semaforo = semThread
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
	global semThread # Variáveis global de retorno do semaforo do thread
	global runThread
	global waitParameters
	global gl
	global gh
	global sai

	semaforos[semaforo].acquire()
	
	while(True):
		runThread.acquire() # Aguarda até o pai liberar a execução
		
		if(sai):	# Se eh para encerrar a execução
			return
		
		l = gl
		h = gh
		semThread = semaforo
		waitParameters.release()# Já copiou os argumentos e configurou
								# o valor de retorno

		mergeSort(vetor, l, h)
		
		semaforos[semaforo].release()


def mergeSort(vetor, p=None, u=None):
	if(p == None):
		p = 0

	if(u == None):
		u = len(vetor)-1

	if(p<u):
		meio = int((p+u)/2)
		
		(semaforos, semL) = tentaThread(p, meio)
		
		# Se não tinha um thread para fazer, então
		# esta linha de execução fará
		if(semL == None):
			mergeSort(vetor, p, meio)
		
		# O ramo direito executa nesta linha de execução mesmo
		mergeSort(vetor, meio+1, u)

		# Se ordenou o ramo esquerdo no thread, então espere ele terminar
		if(semL != None):
			esperaThread(semaforos, semL)
			
		merge(vetor, p, meio, u)

print("Informe um vetor de números : ", end='')

vetor = input().split()

vetor = list(map(int, vetor))

print(vetor)

runThread.acquire()	# Todos os threads vão bloquear nesse semáforo e só
					# são liberados quando tem trabalho a ser feito por 
					# eles


semPai = None
waitParameters.acquire()	# Quando tentar fazer o acquire novamente vai 
							# bloquear neste thread e esperar que um filho
							# libere. Note que o código em que ele espera
							# um filho está no mutex. Somente um filho vai
							# rodar e liberar o pai

MaxThreads = 4
freethreads = MaxThreads
listadeThreads = []
dicionariodeSemaforos = {}
for i in range(0, MaxThreads):
	dicionariodeSemaforos[i] = threading.Semaphore()
	novoWorker = threading.Thread(target=mergeSortWorker, kwargs={'vetor':vetor, 'semaforos':dicionariodeSemaforos, 'semaforo':i})
	listadeThreads.append(novoWorker)

for i in listadeThreads:
	i.start()

mergeSort(vetor)

sai = True
for i in range(0, len(listadeThreads)):
	runThread.release()

for i in listadeThreads:
	i.join()

print(vetor)
