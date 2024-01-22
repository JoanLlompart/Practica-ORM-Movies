

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




    const inputs = Array.from(document.getElementById(selectedOption + "Fields").querySelectorAll("input"));
    //console.log("Inputs:", inputs);
    operation = 'insert';
    // Construir el objeto requestData
    const data = {
        input1: inputs[0].value,
        input2: inputs[1] ? inputs[1].value : null,
    };


    const userConfirmed = window.confirm('Are you sure you want to add this new Country?');

    if (userConfirmed) {

        /* const data = {
            isoCode: isoCodeValue,
            name: nameValue,
        };
        */


        // Realiza la solicitud fetch
        fetch(apiUrl, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud: ' + response.statusText);
                }
                return response.text();
            })
            .then(responseData => {
                // Verificar si el servidor envió un mensaje y mostrarlo con un alert
                alert(responseData);
                console.log(responseData);
            })
            .catch(error => {
                console.error('Error al enviar datos:', error);
            });
    } else {
        // El usuario ha hecho clic en "Cancelar" en la confirmación
        console.log('Operación cancelada por el usuario.');
    }
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
        return baseUrl + 'countries';

    } //Acabar de implementar

    return baseUrl; // Devuelve la URL base si no coincide con ningún tipo de datos conocido
}