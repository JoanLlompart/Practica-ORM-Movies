const operationSelect = document.getElementById('operationSelect');
const selectedSection = document.getElementById('selectedSection');

operationSelect.addEventListener('change', (event) => {
    const selectedOption = event.target.value;
    mostrarSeccion(selectedOption);
});

function mostrarSeccion(operacion) {
    var seccion = operacion.toLowerCase(); // La operación y la sección tienen el mismo nombre
    var secciones = document.querySelectorAll('.section');
    secciones.forEach(function (element) {
        element.classList.remove('active-section');
    });
    document.getElementById(seccion).classList.add('active-section');
}

const actionButton = document.getElementById('actionButton');
actionButton.addEventListener('click', (event) => {
    sendData();
});

async function sendData() {
    // Obtén el tipo de datos (país, idioma, etc.) según el estado actual de la interfaz
    var dataType = determineDataType();

    // Obtén los datos del formulario según el tipo de datos actual
    var formData = getFormData(dataType);
    console.log("Form data " + formData.value1 + " VALUE2 " + formData.value2);

    // Obtén la URL del servidor según el tipo de datos actual y la operación seleccionada
    var apiUrl = getApiUrl(dataType, operationSelect.value);
    console.log("url " + apiUrl);

    const userConfirmed = window.confirm('Are you sure you want to ' + operationSelect.value + ' this ' + dataType + ' ?');

    if (userConfirmed) {
        // Realiza la solicitud fetch
        fetch(apiUrl, {
            method: 'POST', // Puedes ajustar el método según la operación (POST para agregar, PUT/PATCH para actualizar, DELETE para borrar)
            body: JSON.stringify(formData),
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

// Resto del código sin cambios
var selectType =document.querySelector("#addSelect");
    let valueSelect = "country";
    selectType.addEventListener("change", () => {
        valueSelect=selectType.value;
    });

    
function determineDataType() {
    // Lógica para determinar el tipo de datos actual
    // Puedes usar clases, estilos, atributos, o cualquier otro indicador en tu interfaz para determinar el tipo de datos
    // Per exemple, si el div con id "countryInputs" está visible, el tipo de datos es "country", y así sucesivamente

    console.log("Ha entrat a determineDataType")
    return valueSelect;
}


function getFormData(dataType) {
    // Lógica para obtener los datos del formulario según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var formData = {};
    // Ejemplo: Obtén datos del formulario para el tipo de datos 'country'
    if (dataType === 'country') {
        formData.value1 = document.getElementById('countryInputs_countryIsoCode').value;
        formData.value2 = document.getElementById('countryInputs_countryName').value;
    } else if (dataType === 'language') {
        formData.value1 = document.getElementById('languageInputs_languageCode').value;
        formData.value2 = document.getElementById('languageInputs_languageName').value;

    } else if (dataType === 'languageRole') {
        formData.value1 = document.getElementById('languageRoleInputs_languageRole').value;

    } else if (dataType === 'genre') {
        formData.value1 = document.getElementById('genreInputs_genreName').value;

    }  else if (dataType === 'keyword') {
        formData.value1 = document.getElementById('keywordInputs_keywordName').value;

    } else if (dataType === 'movieCompany') {
        formData.value1 = document.getElementById('movieCompanyInputs_movieId').value;
        formData.value2 = document.getElementById('movieCompanyInputs_companyId').value;
        
    } else if (dataType === 'productionCompany') {
        //formData.value1 = document.getElementById('productionCompanyInputs_companyIdProduction').value;
        formData.value1 = document.getElementById('productionCompanyInputs_companyName').value;
        
    }  else if (dataType === 'gender') {
        formData.value1 = document.getElementById('genderInputs_genderName').value;
        
    } else if (dataType === 'person') {
        formData.value1 = document.getElementById('personInputs_personName').value;
        
    } else if (dataType === 'department') {
        formData.value1 = document.getElementById('departmentInputs_departmentName').value;
        
    } else {
        console.log(dataType);
    }

    return formData;
}



// Función para obtener la URL del servidor según el tipo de datos y la operación
function getApiUrl(dataType, operation) {
    var baseUrl = '/adminArea'; // Cambia esto según la estructura de tu API

    if (operation === 'add') {
        return `${baseUrl}/add/${dataType}`;
    } else if (operation === 'update') {
        return `${baseUrl}/update/${dataType}`;
    } else if (operation === 'delete') {
        return `${baseUrl}/delete/${dataType}`;
    }

    // Devuelve la URL base si no coincide con ninguna operación conocida
    return baseUrl;
}
