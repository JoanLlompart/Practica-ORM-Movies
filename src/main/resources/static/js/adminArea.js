
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



const addButton = document.getElementById('addButton');
addButton.addEventListener('click', (event) => {
    confirm()
    const isoCodeValue = document.getElementById('countryIsoCode').value;
    const nameValue = document.getElementById('countryName').value;

    if (isoCodeValue && nameValue) {
        // Mostrar confirmación antes de enviar la solicitud
        const userConfirmed = window.confirm('Are you sure you want to add this new Country?');

        if (userConfirmed) {
            const data = {
                isoCode: isoCodeValue,
                name: nameValue,
            };

            fetch('/adminArea/add', {
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
});



/*
function generateInputs() {
    var select = document.getElementById("addSelect");
    var selectedValue = select.options[select.selectedIndex].value;

    // Limpiar campos anteriores
    document.getElementById("dynamicInputs").innerHTML = '';

    // Generar nuevos campos según la opción seleccionada
    if (selectedValue === "country") {
        generateInput("text", "countryIsoCode", "countryIsoCode", "Country ISO Code");
        generateInput("text", "countryName", "countryName", "Country Name");
    } else if (selectedValue === "language") {
        generateInput("text", "languageCode", "languageCode", "Language Code");
        generateInput("text", "languageName", "languageName", "Language Name");
    } else if (selectedValue === "language_role") {
        generateInput("text", "languageRole", "languageRole", "Language Role");
    } else if (selectedValue === "genre") {
        generateInput("text", "genreName", "genreName", "Genre Name");
    } else if (selectedValue === "keyword") {
        generateInput("text", "keywordName", "keywordName", "Keyword Name");
    } else if (selectedValue === "movie_company") {
        generateInput("text", "movieId", "movieId", "Movie Id");
        generateInput("text", "companyId", "companyId", "Company Id");
    } else if (selectedValue === "production_company") {
        generateInput("text", "companyIdProduction", "companyIdProduction", "Company Id");
        generateInput("text", "companyName", "companyName", "Company Name");
    }
}

function generateInput(type, name, id, placeholder) {
    var input = document.createElement("input");
    input.type = type;
    input.name = name;
    input.id = id;
    input.placeholder = placeholder;

    document.getElementById("dynamicInputs").appendChild(input);
}
*/


/*
function showInputs() {
    var select = document.getElementById("addSelect");
    var selectedValue = select.options[select.selectedIndex].value;

    // Oculta todos los campos
    hideAllInputs();

    // Muestra los campos relevantes según la opción seleccionada
    document.querySelectorAll('.' + selectedValue + "Inputs").forEach(function(container) {
        container.classList.remove('hidden');
    });
}

function hideAllInputs() {
    document.querySelectorAll('.dynamicInputs').forEach(function(container) {
        container.classList.add('hidden');
    });
}
*/


function showInputs() {
    // Oculta todos los div de inputs
    var allInputs = document.querySelectorAll('.dynamicInputs');
    allInputs.forEach(function(inputDiv) {
        inputDiv.classList.add('hidden');
    });

    // Muestra el div correspondiente al valor seleccionado en el select
    var selectedValue = document.getElementById('addSelect').value;
    var selectedInputsDiv = document.getElementById(selectedValue + 'Inputs');
    if (selectedInputsDiv) {
        selectedInputsDiv.classList.remove('hidden');
    }
}