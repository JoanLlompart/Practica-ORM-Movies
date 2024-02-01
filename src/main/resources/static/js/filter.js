async function sendData() {
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
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch', {
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

        // Llamar a la función para actualizar la tabla con los datos recibidos
        updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}

/*
function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla
    const headers = Object.keys(data[0]);
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
            cell.textContent = item[header];
        });
    });
}
*/

let viewId = null;
function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla, incluyendo botones adicionales
    const headers = Object.keys(data[0]);
    headers.push("View", "Actors");  // Agregar columnas adicionales para los botones
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos, incluyendo botones en cada fila
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();

            if (header === "View") {
                // Crear el botón "View" y asignarle un evento
                const viewButton = document.createElement("button");
                viewButton.textContent = "View";
                viewButton.addEventListener("click", function() {
                    viewId = item.movieId;
                    viewInfoMovie(viewId);
                });
                cell.appendChild(viewButton);
            } else if (header === "Actors") {
                // Crear el botón "Actors" y asignarle un evento
                const actorsButton = document.createElement("button");
                actorsButton.textContent = "Actors";
                actorsButton.addEventListener("click", function() {
                    viewId = item.movieId;
                    viewActors(viewId);
                });
                cell.appendChild(actorsButton);
            } else {
                // Llenar las celdas con los datos del objeto 'item'
                cell.textContent = item[header];
            }
        });
    });
}
async function viewActors(viewId) {
    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        movieId: viewId

    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch/movieActor', {
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
        showActorsModal(data,viewId);
    } catch (error) {
        console.error('Error:', error);
    }
}


async function viewInfoMovie(viewId) {
    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        movieId:viewId
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch/infoMovie', {
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
        showMovieModal(data);
        console.log("Dades que aniran a el modal " + data);

    } catch (error) {
        console.error('Error:', error);
    }
}

function showMovieModal(movieData) {
    // Rellena el modal amb la informacio
    const modalBody = document.getElementById("movieModalBody");
    modalBody.innerHTML = "";

    for (const [key, value] of Object.entries(movieData)) {
        const row = document.createElement("div");

        if(key === "director") {
            const directorList = document.createElement("ul");

            // Iterar a través de cada director en la lista
            value.forEach(director => {
                const directorItem = document.createElement("li");
                row.innerHTML = `<strong>${key}:</strong>`;
                directorItem.textContent = `${director.personName}`;
                directorList.appendChild(directorItem);
            });

            row.appendChild(directorList);
        } else {
            row.innerHTML = `<strong>${key}:</strong> ${value}`;
        }
        modalBody.appendChild(row);
    }

    // Mostrar el modal
    $('#movieModal').modal('show');
}

function showActorsModal(actorsData) {
    // Rellena el modal con la información de los actores
    const modalBody = document.getElementById("movieModalBody");
    //const modalTitle = document.querySelector('.modal-title');

   // modalTitle.textContent = ``
    modalBody.innerHTML = "";

    // Crear una tabla para mostrar la información de los actores
    const table = document.createElement("table");
    table.classList.add("table");
    table.innerHTML = `
        <thead>
            <tr>
                <th>Person Name</th>
                <th>Gender</th>
                <th>Character Name</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    `;
    
    // Llenar la tabla con los datos de los actores
    const tbody = table.querySelector("tbody");
    actorsData.forEach(actor => {
        const row = tbody.insertRow();
        const cellPersonName = row.insertCell(0);
        const cellGender = row.insertCell(1);
        const cellCharacterName = row.insertCell(2);
        const cellAction = row.insertCell(3);
       // const cellId = row.insertCell(4);

        cellPersonName.textContent = actor.personName;
        cellGender.textContent = actor.gender;
        cellCharacterName.textContent = actor.characterName;
       // cellId.textContent = actor.personId;


        // Agregar el botón en la columna "Action"
        const actionButton = document.createElement("button");
        actionButton.textContent = "X";
        actionButton.addEventListener("click", function() {
            // Enviar la información al servidor al hacer clic en el botón
            sendDeleteRelation(viewId, actor.personId);
        });
        cellAction.appendChild(actionButton);

    });

    modalBody.appendChild(table);

    // Mostrar el modal
    $('#movieModal').modal('show');
}



// Función para enviar la selección de actor al servidor
async function sendDeleteRelation(movieId, actorId) {
    const requestBody = {
        movieId: movieId,
        actorId: actorId
    };
    const userConfirmed = window.confirm(`Are you sure you want to decast this actor in Movie by id :  ` + movieId + ' ?');
    if(userConfirmed) {
    try {
        const response = await fetch('/adminArea/decastActor', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        }) .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud: ' + response.statusText);
            }
            return response.text();
        })
        .then(responseData => {

        // Puedes manejar la respuesta del servidor si es necesario
        console.log('Actor selection successful');
        //recarrega la pagina
    
        $('#movieModal').modal('hide');
        viewActors(movieId);
        alert(responseData);
        })
    } catch (error) {
        console.error('Error:', error);
    }
} }




/*
async function applyFilter() {
    // Obtener el valor seleccionado del select
    const filterType = document.getElementById("filterType").value;

    // Realizar la acción deseada según el tipo de filtro seleccionado
    console.log("Selected Filter Type:", filterType);
    let url = filterType.substring(0, 1).toUpperCase() + filterType.substring(1);

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        filterType: filterType,
        movieId:viewId
        //pasar movieId
        
        // Agrega otros campos según sea necesario
    };

    // Realizar la solicitud Fetch al servidor
    fetch(`/movieSearch/movie${url}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        console.log(response);
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        
        // Visualizar la lista devuelta por el servidor
        const resultContainer = document.getElementById("filterResult");
        resultContainer.innerHTML = ""; // Limpiar el contenedor antes de actualizar

        // Crear lista o actualizar según el formato de los datos recibidos
        // Ejemplo: Crear una lista de elementos <li> para cada elemento recibido
        const list = document.createElement("ul");
        data.forEach(item => {
            const listItem = document.createElement("li");
            listItem.textContent = item;
            list.appendChild(listItem);
        });
        
        resultContainer.appendChild(list);
        
        
        // Mostrar los resultados filtrados en el modal
        updateFilterResult(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });


    // Cerrar el modal
    $('#filterModal').modal('hide');
}
*/


function updateFilterResult(data) {
    const resultContainer = document.getElementById("filterResult");
    resultContainer.innerHTML = ""; // Limpiar el contenedor antes de actualizar

    // Crear lista o actualizar según el formato de los datos recibidos
    // Ejemplo: Crear una lista de elementos <li> para cada elemento recibido
    const list = document.createElement("ul");
    data.forEach(item => {
        const listItem = document.createElement("li");
        listItem.textContent = item;
        list.appendChild(listItem);
    });

    resultContainer.appendChild(list);
}




const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input" , function() {
    //Envia les noves dades a sendData perque faci la peticio
    if(keywordInput.value.trim() !=='') {
        sendData();
    }
})

//proba 

// Event Listener for Page input
document.getElementById('page').addEventListener('change', function() {
    sendData();
});

// Event Listener for Size input
document.getElementById('size').addEventListener('change', function() {
    sendData();
});






// Añade un evento al botón "Add new person to cast" para abrir el modal de búsqueda de personas
const addPersonButton = document.getElementById('btnPersonSearch');
addPersonButton.addEventListener('click', function () {
    // Limpiar el campo de búsqueda y los resultados
    document.getElementById('personKeyword').value = '';
    document.getElementById('personResults').innerHTML = '';

    // Abrir el modal de búsqueda de personas
    $('#addPersonModal').modal('show');
});

// Función para realizar la búsqueda de personas
async function searchPersons() {
    const personKeyword = document.getElementById('personKeyword').value;
    const personPage = document.getElementById('personPage').value;
    const personSize = document.getElementById('personSize').value;

    // Realizar la solicitud Fetch al servidor para buscar personas
    const response = await fetch(`/searchPersons?keyword=${personKeyword}&page=${personPage}&size=${personSize}`);
    const data = await response.json();

    // Mostrar los resultados en el modal
    const personResults = document.getElementById('personResults');
    personResults.innerHTML = '';

    data.forEach(person => {
        const listItem = document.createElement('li');
        listItem.textContent = `${person.name} (${person.id})`;

        // Añade un botón para agregar la persona al elenco
        const addButton = document.createElement('button');
        addButton.textContent = 'Add';
        addButton.addEventListener('click', function () {
            addPersonToCast(person.id, person.name);
        });

        listItem.appendChild(addButton);
        personResults.appendChild(listItem);
    });
}

// Función para agregar la persona seleccionada al elenco de la película
function addPersonToCast(personId, personName) {
    // Realiza la lógica para agregar la persona al elenco de la película
    // Puedes hacer una solicitud Fetch al servidor para realizar esta acción
    // Recuerda cerrar el modal después de realizar la acción

    // Ejemplo de cómo cerrar el modal
    $('#addPersonModal').modal('hide');
}





/*

ORIGINAL I FUNCIONAL
function sendData() {
    var form = document.getElementById('formFilter');
    var filter = document.getElementById('filter').value;
    var keyword = document.getElementById('keyword').value;
    var formData = {
        filter: filter,
        keyword: keyword
        }
        fetch('/movieSearch', {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            // Borrar la taula actual de dades per mostrar la nova
            var tableBody = document.querySelector('.table tbody');
            tableBody.innerHTML = "";
            
             // Llenar la tabla con los nuevos datos
            data.moviesFind.forEach(movie => {
                var newRow = tableBody.insertRow(tableBody.rows.length);
                newRow.insertCell(0).textContent = movie.movieId;
                newRow.insertCell(1).textContent = movie.title;
                newRow.insertCell(2).textContent = movie.releaseDate;
                newRow.insertCell(3).textContent = movie.voteAverage;
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
*/


//Per ara no esta actiu
        // Funció que navega a una página específica
/*
function goToPage() {
    var pageNumberInput = document.getElementById('pageNumberInput').value;
    var totalPages = ${totalPages};

    // Validar que el valor sea un número entre 1 y totalPages
    if (/^[1-9]\d*$/.test(pageNumberInput)) {
        var page = parseInt(pageNumberInput, 10) - 1;

        // Verificar que la página esté dentro del rango permitido
        if (page >= 0 && page < totalPages) {
            window.location.href = '/movieSearch?page=' + page;
        }
    }
}
*/