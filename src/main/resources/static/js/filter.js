function sendData() {
    var form = document.getElementById('formFilter');
    var formData = new FormData(form);

    fetch('/movieSearch', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        },
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