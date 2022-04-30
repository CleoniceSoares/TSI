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

def mergeSort(vetor, p, u):
    if(p<u):
        meio = int((p+u)/2)
        mergeSort(vetor, p, meio)
        mergeSort(vetor, meio+1, u)
        merge(vetor, p, meio, u)

print("Informe um conjunto de numeros:", end='')
vetor = input().split()

mergeSort(vetor, 0, len(vetor)-1)

print(vetor)