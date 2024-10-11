import socket

HOST = "192.168.152.211"
PORT = 5000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    print("Conectando al servidor...")
    s.connect((HOST, PORT))
    
    # Enviar un mensaje al servidor
    mensaje = "Hola Mundo"
    s.sendall(mensaje.encode('utf-8'))  # Enviar mensaje en formato UTF-8
    
    # Recibir la respuesta del servidor
    data = s.recv(1024).decode('utf-8')
    print("Recibiendo:", data)
