

const addButton = document.getElementById('addButton');
addButton.addEventListener('click', (event) => {
    sendData();
});


function sendData() {
    // Obtén el tipo de datos (país, idioma, etc.) según el estado actual de la interfaz
    var dataType = determineDataType();

    // Obtén los datos del formulario según el tipo de datos actual
    var formData = getFormData(dataType);

    // Obtén la URL del servidor según el tipo de datos actual
    var apiUrl = getApiUrl(dataType);

    // Realiza la solicitud fetch
    fetch(apiUrl, {
        method: 'POST', // o 'GET' según tus necesidades
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => response.json())
    .then(data => {
        // Maneja la respuesta del servidor según tus necesidades
        console.log('Respuesta del servidor:', data);
    })
    .catch(error => {
        console.error('Error al enviar los datos:', error);
    });
}

function determineDataType() {
    // Lógica para determinar el tipo de datos actual
    // Puedes usar clases, estilos, atributos, o cualquier otro indicador en tu interfaz para determinar el tipo de datos
    // Por ejemplo, si el div con id "countryInputs" está visible, el tipo de datos es "country", y así sucesivamente
    if (document.getElementById('countryInputs').classList.contains('visible')) {
        return 'country';
    } else if (document.getElementById('languageInputs').classList.contains('visible')) {
        return 'language';
    }
    // Añade más casos según sea necesario
}

function getFormData(dataType) {
    // Lógica para obtener los datos del formulario según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var formData = {};

    // Ejemplo: Obtén datos del formulario para el tipo de datos 'country'
    if (dataType === 'country') {
        formData.countryIsoCode = document.getElementById('countryInputs_countryIsoCode').value;
        formData.countryName = document.getElementById('countryInputs_countryName').value;
    } else if (dataType === 'language') {
        formData.countryIsoCode = document.getElementById('languageInputs_languageCode').value;
        formData.countryName = document.getElementById('languageInputs_languageName').value;
    }

    return formData;
}

function getApiUrl(dataType) {
    // Lógica para obtener la URL del servidor según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var baseUrl = '/adminArea/add';

    // Ejemplo: URL para el tipo de datos 'country'
    if (dataType === 'country') {
        return baseUrl + 'countries';
    } else if (dataType === 'language') {
        // Implementa la lógica para otros tipos de datos
    }

    return baseUrl; // Devuelve la URL base si no coincide con ningún tipo de datos conocido
}