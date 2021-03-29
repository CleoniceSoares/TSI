import socket
server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server.bind(('', 5000))
print("Servidor pronto para estabelecer comunicação!")
while True:
    msg_byte, ip_client = server.recvfrom(2048)
    resposta_server = msg_byte.decode()
    server.sendto(resposta_server.encode(), ip_client)
    print("Mensagem recebida do cliente: ", resposta_server) 
