avatar = input().upper()
estado_atual = input()

tabuleiro = ["-", "-", "-", "-", "-", "-","-", "-", "-"]
tabuleiro[0] = estado_atual[0]
tabuleiro[1] = estado_atual[1]
tabuleiro[2] = estado_atual[2]
tabuleiro[3] = estado_atual[3]
tabuleiro[4] = estado_atual[4]
tabuleiro[5] = estado_atual[5]
tabuleiro[6] = estado_atual[6]
tabuleiro[7] = estado_atual[7]
tabuleiro[8] = estado_atual[8]

def busca_grafo(estado_atual):
    borda = estado_atual
    conj_explorado = []

    if(len(borda) < 0):
        return "Falha"

    
    return None

