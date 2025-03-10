let authHeader = null;

// Función para autenticar al usuario
function autenticar() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Crear el encabezado de autenticación básica
    const credentials = btoa(`${username}:${password}`);
    authHeader = `Basic ${credentials}`;

    // Probar la autenticación
    fetch('http://localhost:8080/reservas', {
        headers: {
            'Authorization': authHeader
        }
    })
        .then(response => {
            if (response.ok) {
                document.getElementById('auth-result').innerText = 'Autenticación exitosa';
            } else {
                document.getElementById('auth-result').innerText = 'Error de autenticación';
            }
        })
        .catch(error => {
            console.error('Error al autenticar:', error);
            document.getElementById('auth-result').innerText = 'Error de autenticación';
        });
}

// Función para obtener hoteles
function obtenerHoteles() {
    fetch('http://localhost:8080/hoteles')
        .then(response => response.json())
        .then(data => {
            const resultDiv = document.getElementById('hoteles-result');
            resultDiv.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
        })
        .catch(error => {
            console.error('Error al obtener hoteles:', error);
            document.getElementById('hoteles-result').innerText = 'Error al obtener hoteles';
        });
}

// Función para obtener vuelos
function obtenerVuelos() {
    fetch('http://localhost:8080/vuelos')
        .then(response => response.json())
        .then(data => {
            const resultDiv = document.getElementById('vuelos-result');
            resultDiv.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
        })
        .catch(error => {
            console.error('Error al obtener vuelos:', error);
            document.getElementById('vuelos-result').innerText = 'Error al obtener vuelos';
        });
}

function crearReserva() {
    const hotelId = document.getElementById('hotelId').value;
    const vueloId = document.getElementById('vueloId').value;

    // Verificar que los campos no estén vacíos y sean números válidos
    if (!hotelId || !vueloId || isNaN(hotelId) || isNaN(vueloId)) {
        document.getElementById('reserva-result').innerText = 'Por favor, ingresa un ID de hotel y un ID de vuelo válidos.';
        return;
    }

    // Convertir los IDs a números
    const reservaData = {
        hotelId: parseInt(hotelId, 10),
        vueloId: parseInt(vueloId, 10)
    };

    // Enviar la solicitud POST
    fetch('http://localhost:8080/reservas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': authHeader
        },
        body: JSON.stringify(reservaData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(errorData.message || 'Error al crear la reserva');
                });
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('reserva-result').innerText = 'Reserva creada con éxito: ' + JSON.stringify(data, null, 2);
        })
        .catch(error => {
            console.error('Error al crear reserva:', error);
            document.getElementById('reserva-result').innerText = 'Error al crear reserva: ' + error.message;
        });
 }

// Función para obtener reservas
function obtenerReservas() {
    fetch('http://localhost:8080/reservas', {
        headers: {
            'Authorization': authHeader
        }
    })
        .then(response => response.json())
        .then(data => {
            const resultDiv = document.getElementById('reservas-result');
            resultDiv.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
        })
        .catch(error => {
            console.error('Error al obtener reservas:', error);
            document.getElementById('reservas-result').innerText = 'Error al obtener reservas';
        });
}