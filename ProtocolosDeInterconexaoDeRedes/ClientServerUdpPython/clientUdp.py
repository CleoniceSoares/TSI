import socket 
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
while True:
    msg_client = input("Digite uma mensagem para o servidor: ")
    client.sendto(msg_client.encode(), ("127.0.0.1", 5000))
    msg_byte, ip_client = client.recvfrom(2048)