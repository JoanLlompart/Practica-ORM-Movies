function sendData() {
    var form = document.getElementById('myForm');
    var formData = new FormData(form);

    fetch('/movieSearch', {
        method: 'POST', // o 'GET' según tu necesidad
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        // Manejar la respuesta del servidor si es necesario
        console.log(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}