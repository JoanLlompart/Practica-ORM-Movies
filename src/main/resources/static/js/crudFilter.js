
async function sendDataFilter() {
    // Obtener los valores del formulario
    const filter = document.getElementById("filter").value;
    const keyword = document.getElementById("keyword").value;
    const page = document.getElementById("page").value;
    const size = document.getElementById("size").value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        filter: filter,
        keyword: keyword,
        page: page,
        size: size
    };

    try {
        const response = await fetch('/adminArea/filter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        //resposta erronea
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }

        // Obtener los datos de la respuesta JSON
        const data = await response.json();
        console.log(data);
        // Llamar a la función para actualizar la tabla con los datos recibidos
        updateTable(data, filter);
    } catch (error) {
        console.error('Error:', error);
    }
}

let viewId = null;
function updateTable(data, filter) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla
    const headers = Object.keys(data[0]);

    // Afegim els botons de Modificar
    if (filter === "movie") {
        headers.push("Relations");
    }

    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();
            if (header === "Relations") {
                // Crear el botón "Relations" y asignarle un evento
                const realtionButton = document.createElement("button");
                realtionButton.textContent = "Relations";
                realtionButton.addEventListener("click", function () {
                    console.log("click a relations");
                    viewId = item.movieId;
                    //relationsMovie(viewId);
                });
                cell.appendChild(realtionButton);
            } else {
                // Establecer el contenido de la celda para otras columnas
                cell.textContent = item[header];
            }
        });
    });
}

async function relationsMovie(viewId) {
    var addToMovieSelect = document.getElementById("relationSelect").value;
    console.log("Valor seleccionat " + addToMovieSelect);

    let relationSelect = addToMovieSelect.substring(0, 1).toUpperCase() + addToMovieSelect.substring(1);
    const requestBody = {
        movieId: viewId
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch(`/adminArea/movie${relationSelect}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        // Verificar el estado de la respuesta
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }

        // Obtener los datos de la respuesta JSON
        const data = await response.json();
        //showActorsModal(data, viewId);

    } catch (error) {
        console.error('Error:', error);
    }
}

/*
function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla con clases de Bootstrap
    const headers = Object.keys(data[0]);
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        th.classList.add("table-dark"); // Clase de Bootstrap para encabezados oscuros
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos y agregar clases de Bootstrap
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();
            cell.textContent = item[header];
            cell.classList.add("table-light"); // Clase de Bootstrap para filas claras
        });
    });

    // Agregar la clase de Bootstrap para tablas
    table.classList.add("table", "table-bordered", "table-striped");
}
*/








const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input", function () {
    //Envia les noves dades a sendDataFilter perque faci la peticio
    if (keywordInput.value.trim() !== '') {
        sendDataFilter();
    }
})
document.getElementById('page').addEventListener('change', function () {
    sendDataFilter();
});

// Event Listener for Size input
document.getElementById('size').addEventListener('change', function () {
    sendDataFilter();
});



// Función para mostrar el modal
function openRelationsModal() {
    const modal = document.getElementById("relationsModal");
    modal.style.display = "block";
}

// Función para cerrar el modal
function closeRelationsModal() {
    const modal = document.getElementById("relationsModal");
    modal.style.display = "none";
}

// Evento de clic en el botón "Relations"
document.addEventListener("click", function(event) {
    if (event.target.textContent === "Relations") {
        openRelationsModal();
    }
});

// Evento de clic en el botón de cierre del modal
document.addEventListener("click", function(event) {
    if (event.target.classList.contains("close")) {
        closeRelationsModal();
    }
});



//PERSON SEARCH
const keywordRelationInput = document.getElementById("relationKeyword");
keywordRelationInput.addEventListener("input", function () {
    //Envia les noves dades a sendData perque faci la peticio
    if (keywordRelationInput.value.trim() !== '') {
        relationsMovie(viewId);
       // searchPersons();
    }
})


document.getElementById('relationPage').addEventListener('change', function () {
    relationsMovie(viewId);
    //searchPersons();
});


document.getElementById('relationSize').addEventListener('change', function () {
    relationsMovie(viewId);
    //searchPersons();
});