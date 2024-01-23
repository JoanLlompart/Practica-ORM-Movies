
const operationSelect = document.getElementById('operationSelect');
const selectedSection = document.getElementById('selectedSection');

operationSelect.addEventListener('change', (event) => {
    const selectedOption = event.target.value;

    if (selectedOption === 'add') {
        mostrarSeccion('añadir');
    } else if (selectedOption === 'update') {
        mostrarSeccion('actualizar');
    } else if (selectedOption === 'delete') {
        mostrarSeccion('borrar');
    }
});

function mostrarSeccion(seccion) {
    var secciones = document.querySelectorAll('.section');
    secciones.forEach(function (element) {
        element.classList.remove('active-section');
    });
    document.getElementById(seccion).classList.add('active-section');
}
//PART NOVA

const addButton = document.getElementById('addButton');
addButton.addEventListener('click', (event) => {
    sendData();
});


async function sendData() {
    // Obtén el tipo de datos (país, idioma, etc.) según el estado actual de la interfaz
    var dataType = determineDataType();

    // Obtén los datos del formulario según el tipo de datos actual
    var formData = getFormData(dataType);
    console.log("Form data " + formData.value1 + " VALUE2" + formData.value2);

    // Obtén la URL del servidor según el tipo de datos actual
    var apiUrl = getApiUrl(dataType);

    const userConfirmed = window.confirm('Are you sure you want to add this new Country?');

    if (userConfirmed) {

        // Realiza la solicitud fetch
        fetch(apiUrl, {
            method: 'POST',
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

var selectType =document.querySelector("#addSelect");
    let valueSelect = "country";
    selectType.addEventListener("change", () => {
        valueSelect=selectType.value;
    });
    
function determineDataType() {
    // Lógica para determinar el tipo de datos actual
    // Puedes usar clases, estilos, atributos, o cualquier otro indicador en tu interfaz para determinar el tipo de datos
    // Por ejemplo, si el div con id "countryInputs" está visible, el tipo de datos es "country", y así sucesivamente
    
    /*  if (document.getElementById('countryInputs').classList.contains('visible')) {
        console.log("Country ha agafat el tipus be")
        return 'country';
    } else if (document.getElementById('languageInputs').classList.contains('visible')) {
        return 'language';
    }
    */
    
    console.log("Ha entrat a determineDataType")
    return valueSelect;
}

//Versio personalizada per erronea
/*
function getFormData(dataType) {
    // Lógica para obtener los datos del formulario según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var formData = {};

    // Ejemplo: Obtén datos del formulario para el tipo de datos 'country'
    if (dataType === 'country') {
        formData.countryIsoCode = document.getElementById('countryInputs_countryIsoCode').value;
        formData.countryName = document.getElementById('countryInputs_countryName').value;
    } else if (dataType === 'language') {
        formData.languageCode = document.getElementById('languageInputs_languageCode').value;
        formData.languageName = document.getElementById('languageInputs_languageName').value;

    } else if (dataType === 'language_role') {
        formData.languageRole = document.getElementById('languageRoleInputs_languageRole').value;

    } else if (dataType === 'genre') {
        formData.genreName = document.getElementById('genreInputs_genreName').value;

    }  else if (dataType === 'keyword') {
        formData.keywordName = document.getElementById('keywordInputs_keywordName').value;

    } else if (dataType === 'movie_company') {
        formData.movieId = document.getElementById('movieCompanyInputs_movieId').value;
        formData.companyId = document.getElementById('movieCompanyInputs_companyId').value;
        
    } 

    return formData;
}
*/



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
        formData.value1 = document.getElementById('productionCompanyInputs_companyIdProduction').value;
        formData.value2 = document.getElementById('productionCompanyInputs_companyName').value;
        
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
function getApiUrl(dataType) {
    // Lógica para obtener la URL del servidor según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var baseUrl = '/adminArea/add';

    // Ejemplo: URL para el tipo de datos 'country'
    if (dataType === 'country') {
        return baseUrl + '/country';
    } else if (dataType === 'language') {
        return baseUrl + '/language';

    } //Acabar de implementar

    //console.log("Datetype : " + dataType)
    //console.log(baseUrl)
    return baseUrl; // Devuelve la URL base si no coincide con ningún tipo de datos conocido
}



//AQUI COMENSA LA PART ANTIGÜA
function showInputs() {
    // Oculta todos los div de inputs
    var allInputs = document.querySelectorAll('.dynamicInputs');
    allInputs.forEach(function(inputDiv) {
        inputDiv.classList.add('hidden');
    });

    // Muestra el div correspondiente al valor seleccionado en el select
    var selectedValue = document.getElementById('addSelect').value;
    var selectedInputsDiv = document.getElementById(selectedValue + 'Inputs');
    console.log("Valor a addSelect : " + selectedValue  + " , valor a el div : " + selectedInputsDiv );
    if (selectedInputsDiv) {
        selectedInputsDiv.classList.remove('hidden');
    }
}

// Llama a showInputs al cargar la página para visualizar por defecto "country"
window.onload = function() {
    showInputs();
};