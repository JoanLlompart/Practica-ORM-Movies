function sendData() {
    var form = document.getElementById('formFilter');
    var filter = document.getElementById('filter').value;
    var keyword = document.getElementById('keyword').value;

    var formData = new FormData();
    formData.append("filter",filter);
    formData.append("keyword",keyword);
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