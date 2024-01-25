
const operationSelect = document.getElementById('operationSelect');
const selectedSection = document.getElementById('selectedSection');
let selectedOption = "add";
// Seccio que es troba (añadir,actualizar,delete)
//let seccion = 'añadir';
//mostrarSeccion(seccion);


operationSelect.addEventListener('change', (event) => {
    selectedOption = event.target.value;
    //Llamar a funcion
    checkOperation();
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

    const addButton = document.getElementById(`${seccion}Button`);
    addButton.addEventListener('click', (event) => {
        sendData();
    });

}
//PART NOVA
/*
const addButton = document.getElementById('addButton');
addButton.addEventListener('click', (event) => {
    sendData();
});*/


async function sendData() {
    // Obtén el tipo de datos (país, idioma, etc.) según el estado actual de la interfaz
    var dataType = determineDataType();

    // Obtén los datos del formulario según el tipo de datos actual
    var formData = getFormData(dataType);
    console.log("Form data " + formData.value1 + " VALUE2" + formData.value2);

    // Obtén la URL del servidor según el tipo de datos actual
    var apiUrl = getApiUrl(dataType);
    console.log("url " + apiUrl);

    const userConfirmed = window.confirm('Are you sure you want to add this new ' + dataType + ' ?');


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
let valueSelect = "country";
//Funcio per veura la operacio que estam realitzant
function checkOperation() {
    var selectType = document.querySelector(`#${selectedOption}Select`);
    console.log("selectedOption " + selectedOption)
    selectType.addEventListener("change", () => {
        valueSelect = selectType.value;
    });
}

function determineDataType() {
    // Lógica para determinar el tipo de datos actual
    // Puedes usar clases, estilos, atributos, o cualquier otro indicador en tu interfaz para determinar el tipo de datos
    // Per exemple, si el div con id "countryInputs" está visible, el tipo de datos es "country", y así sucesivamente

    console.log("Ha entrat a determineDataType")
    return valueSelect;
}

function getFormData(dataType) {

    let operation = selectedOption.substring(0, 1).toUpperCase() + selectedOption.substring(1);
    // Lógica para obtener los datos del formulario según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var formData = {};

    // Ejemplo: Obtén datos del formulario para el tipo de datos 'country'


    //Proba de update 
    console.log("operation a getFormData : " + operation);

    if (operation == 'Delete') {
        formData=formDataForDelete(dataType, formData);
    } else {

        if (dataType === 'country') {
            formData.value1 = document.getElementById(`countryInputs${operation}_countryIsoCode`).value;
            formData.value2 = document.getElementById(`countryInputs${operation}_countryName`).value;

            // Añade el valor adicional si operation es igual a "Update"
            if (operation === 'Update') {
                formData.value3 = document.getElementById(`countryInputs${operation}_countryId`).value;
            }

        } else if (dataType === 'language') {
            formData.value1 = document.getElementById(`languageInputs${operation}_languageCode`).value;
            formData.value2 = document.getElementById(`languageInputs${operation}_languageName`).value;


            if (operation === 'Update') {
                formData.value3 = document.getElementById(`languageInputs${operation}_languageId`).value;
            }

        } else if (dataType === 'languageRole') {
            formData.value1 = document.getElementById(`languageRoleInputs${operation}_languageRole`).value;

            if (operation === 'Update') {
                formData.value2 = document.getElementById(`languageRoleInputs${operation}_roleId`).value;
            }

        } else if (dataType === 'genre') {
            formData.value1 = document.getElementById(`genreInputs${operation}_genreName`).value;
            //Afegim el id si la operacio el requereix
            if (operation === 'Update') {
                formData.value2 = document.getElementById(`genreInputs${operation}_genreId`).value;
            }


        } else if (dataType === 'keyword') {
            formData.value1 = document.getElementById(`keywordInputs${operation}_keywordName`).value;

            //Afegim el id si la operacio el requereix
            if (operation === 'Update') {
                formData.value2 = document.getElementById(`keywordInputs${operation}_keywordId`).value;
            }


        } else if (dataType === 'movieCompany') {
            formData.value1 = document.getElementById(`movieCompanyInputs${operation}_movieId`).value;
            formData.value2 = document.getElementById(`movieCompanyInputs${operation}_companyId`).value;

        } else if (dataType === 'productionCompany') {
            //formData.value1 = document.getElementById('productionCompanyInputs_companyIdProduction').value;
            formData.value1 = document.getElementById(`productionCompanyInputs${operation}_companyName`).value;

            // Añade el valor adicional si operation es igual a "Update"
            if (operation === 'Update') {
                formData.value2 = document.getElementById(`productionCompanyInputs${operation}_companyIdProduction`).value;
            }

        } else if (dataType === 'gender') {
            formData.value1 = document.getElementById(`genderInputs${operation}_genderName`).value;

            //Afegim el id si la operacio el requereix
            if (operation === 'Update') {
                formData.value2 = document.getElementById(`genderInputs${operation}_genderId`).value;
            }

        } else if (dataType === 'person') {
            formData.value1 = document.getElementById(`personInputs${operation}_personName`).value;
            //Afegim el id si la operacio el requereix
            if (operation === 'Update') {
                formData.value2 = document.getElementById(`personInputs${operation}_personId`).value;
            }


        } else if (dataType === 'department') {
            formData.value1 = document.getElementById(`departmentInputs${operation}_departmentName`).value;

            if (operation === 'Update') {
                formData.value2 = document.getElementById(`departmentInputs${operation}_departmentId`).value;
            }

        } else if(dataType === 'movie') {
            formData.value1 = document.getElementById(`movieInputs${operation}_title`).value;
            formData.value2 = document.getElementById(`movieInputs${operation}_budget`).value;
            formData.value3 = document.getElementById(`movieInputs${operation}_homepage`).value;
            formData.value4 = document.getElementById(`movieInputs${operation}_overview`).value;
            formData.value5 = document.getElementById(`movieInputs${operation}_popularity`).value;
            formData.value6 = document.getElementById(`movieInputs${operation}_relaseDate`).value;
            formData.value7 = document.getElementById(`movieInputs${operation}_revenue`).value;
            formData.value8 = document.getElementById(`movieInputs${operation}_runtime`).value;
            formData.value9 = document.getElementById(`movieInputs${operation}_movieStatus`).value;
            formData.value10 = document.getElementById(`movieInputs${operation}_tagline`).value;
            formData.value11 = document.getElementById(`movieInputs${operation}_voteAvarage`).value;
            formData.value12 = document.getElementById(`movieInputs${operation}_voteCount`).value;

        } else {
            console.log("Data type: " + dataType);
        }

    }
    return formData;
}

function formDataForDelete(dataType,formData) {
//Funcio per agafar inputs de delete
    switch (dataType) {
        case 'country':
            console.log("La opción es country");
            formData.value1 = document.getElementById(`countryInputsDelete_countryId`).value; 
            return formData;
        case 'language':
            formData.value1 = document.getElementById(`languageInputsDelete_languageId`).value;
            return formData;
        case 'languageRole':
            formData.value1 = document.getElementById(`languageRoleInputsDelete_roleId`).value;
            return formData;
        case 'person':
            formData.value1 = document.getElementById(`languageRoleInputsDelete_personId`).value;
        return formData;
        default:
            return formData;
    }
}



function getApiUrl(dataType) {
    // Lógica para obtener la URL del servidor según el tipo de datos
    // Implementa esta función según tus necesidades específicas
    var baseUrl = `/adminArea/${selectedOption}`;

    // Ejemplo: URL para el tipo de datos 'country'
    if (dataType === 'country') {
        return baseUrl + '/country';

    } else if (dataType === 'language') {
        return baseUrl + '/language';

    } else if (dataType === 'languageRole') {
        return baseUrl + '/languageRole';

    } else if (dataType === 'genre') {
        return baseUrl + '/genre';

    } else if (dataType === 'keyword') {
        return baseUrl + '/keyword';

    } else if (dataType === 'movieCompany') {
        return baseUrl + '/movieCompany';

    } else if (dataType === 'productionCompany') {
        return baseUrl + '/productionCompany';

    } else if (dataType === 'gender') {
        return baseUrl + '/gender';

    } else if (dataType === 'person') {
        return baseUrl + '/person';

    } else if (dataType === 'department') {
        return baseUrl + '/department';

    } else if (dataType === 'movie') {
        return baseUrl + '/movie';
    }
    return baseUrl; // Devuelve la URL base si no coincide con ningún tipo de datos conocido
}



//AQUI COMENSA LA PART ANTIGÜA
function showInputs() {
    // Oculta todos los div de inputs
    var allInputs = document.querySelectorAll('.dynamicInputs');
    allInputs.forEach(function (inputDiv) {
        inputDiv.classList.add('hidden');
    });

    // Muestra el div correspondiente al valor seleccionado en el select
    var selectedValue = document.getElementById(`${selectedOption}Select`).value;
    var selectedInputsDiv = document.getElementById(selectedValue + 'Inputs');
    console.log("Valor a addSelect : " + selectedValue + " , valor a el div : " + selectedInputsDiv);
    if (selectedInputsDiv) {
        selectedInputsDiv.classList.remove('hidden');
    }
}


//UPDATE NOU
document.getElementById('updateSelect').addEventListener('change', function () {
    var selectedValue = this.value;

    // Oculta todos los conjuntos de inputs
    var allInputs = document.querySelectorAll('.dynamicInputs');
    allInputs.forEach(function (input) {
        input.classList.add('hidden');
    });

    // Muestra solo el conjunto de inputs correspondiente al valor seleccionado
    var selectedInputs = document.getElementById(selectedValue + 'InputsUpdate');
    if (selectedInputs) {
        selectedInputs.classList.remove('hidden');
    }
});

document.getElementById('deleteSelect').addEventListener('change', function () {
    var selectedValue = this.value;

    // Oculta todos los conjuntos de inputs
    var allInputs = document.querySelectorAll('.dynamicInputs');
    allInputs.forEach(function (input) {
        input.classList.add('hidden');
    });

    // Muestra solo el conjunto de inputs correspondiente al valor seleccionado
    var selectedInputs = document.getElementById(selectedValue + 'InputsDelete');
    if (selectedInputs) {
        selectedInputs.classList.remove('hidden');
    }
});


// Llama a showInputs al cargar la página para visualizar por defecto "country"
window.onload = function () {
    showInputs();
};




/*function showInputs() {
    // Oculta todos los div de inputs
    var allInputs = document.querySelectorAll('.dynamicInputs');
    allInputs.forEach(function (inputDiv) {
        inputDiv.classList.add('hidden');
    });

    // Muestra el div correspondiente al valor seleccionado en el select
    var selectedValue = document.getElementById('updateSelect').value;
    var selectedInputsDiv = document.getElementById(selectedValue + 'InputsUpdate');
    console.log("Valor a updateSelect : " + selectedValue + " , valor a el div : " + selectedInputsDiv);
    if (selectedInputsDiv) {
        selectedInputsDiv.classList.remove('hidden');
    }
}

document.getElementById('updateSelect').addEventListener('change', function () {
    // Llama a la función showInputs cuando cambia el valor del select
    showInputs();
});

// Llama a showInputs al cargar la página para visualizar por defecto "country"
window.onload = function() {
    showInputs();
};
*/